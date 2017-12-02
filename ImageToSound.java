import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

public class ImageToSound {

	private static BufferedImage pic;
	private static Pixel[][] pixels;
	// Matt's stuff
	private static int rgbNotes[][] = { { 97, 0, 97 }, { 106, 0, 255 }, { 0, 123, 255 }, { 0, 255, 146 },
			{ 129, 255, 0 }, { 255, 255, 0 }, { 255, 119, 0 }, { 255, 0, 0 } };
	private static int notes[] = { 3, 5, 7, 8, 10, 12, 14, 15 };
	private final static int NUMNOTES = 8;

	public static void main(String args[]) {

		String imageFileName = args[0];

		try {
			pic = ImageIO.read(new File(imageFileName));
		} catch (IOException e) {
			System.out.println("Not valid image");
			System.exit(0);
		}
		pixels = new Pixel[pic.getHeight()][pic.getWidth()];
		for (int x = 0; x < pic.getWidth(); x++) {
			for (int y = 0; y < pic.getHeight(); y++) {
				int rgb = pic.getRGB(x, y);
				int red = (rgb >> 16) & 0xFF;
				int green = (rgb >> 8) & 0xFF;
				int blue = (rgb) & 0xFF;
				pixels[y][x] = new Pixel(red, green, blue);
			}
		}

		/**
		 * for (int x = 0; x < pic.getHeight(); x++) { for (int y = 0; y <
		 * pic.getWidth(); y++) { System.out.print(pixels[x][y]); }
		 * System.out.println(); }
		 **/
		// makeOutputFile(pixels);
		makeOutputFile(averagePixels(pixels));
	}

	public static void makeOutputFile(Pixel[][] pix) {
		final double NOTELENGTH = .25;
		PrintWriter writer;
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
			for (int x = 0; x < pix.length; x++) {
				for (int y = 0; y < pix[0].length; y++) {
					writer.println("" + NOTELENGTH * (pix[0].length * x + y) + " " + getNote(pix[x][y]));
				}
			}
			writer.println("" + (NOTELENGTH * pix.length*pix[0].length+NOTELENGTH*2) + " -1");
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception thrown");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported encoding exception thrown");
		}

	}

	public static Pixel[][] averagePixels(Pixel[][] pixels) {
		int reduction = (int) Math.sqrt((double) pixels.length * pixels[0].length / 720);
		if (reduction >= 2) {

			Pixel[][] averaged = new Pixel[pixels.length / reduction][pixels[0].length / reduction];

			int r;
			int g;
			int b;

			for (int i = 0; i < pixels.length / reduction; i++) {
				for (int j = 0; j < pixels[0].length / reduction; j++) {
					r = pixels[i*reduction][j*reduction].getRed();
					g = pixels[i*reduction][j*reduction].getGreen();
					b = pixels[i*reduction][j*reduction].getBlue();
					averaged[i][j] = new Pixel(r,g,b);
				}
			}
			return averaged;
		} else return pixels;
	}

	public static int getNote(Pixel p) {
		int r = p.getRed();
		int g = p.getGreen();
		int b = p.getBlue();
		int scores[] = new int[8];
		for (int i = 0; i < NUMNOTES; i++) {
			scores[i] = Math.abs(r - rgbNotes[i][0]) + Math.abs(g - rgbNotes[i][1]) + Math.abs(b - rgbNotes[i][2]);
		}
		int min = scores[0];
		int index = 0;
		for (int i = 1; i < NUMNOTES; i++) {
			if (scores[i] < min) {
				min = scores[i];
				index = i;
			}
		}
		return notes[index];
	}
}

class Pixel {
	private int red, green, blue;

	public Pixel(int r, int g, int b) {
		red = r;
		green = g;
		blue = b;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public String toString() {
		return "[" + this.getRed() + "," + this.getGreen() + "," + this.getBlue() + "]";
	}
}