# Image2Sound
A program for converting images to sound

This project was created by me (Stephen Hunter Barbella) and Matthew Kenigsberg.

## Use (in linux)
python ImageToSound.py -i ImageName -o AudioFileName

This python script calls a java program that converts the image into a text file describing its pixels.  
It then converts it to a text file that contains song information.  
Then it is converted to a DAT file containing more specific information about the sound and it adds guitar like sounds.
Lastly the DAT file is converted into a .wav file using the CLI program SOX. 

The result is an audio file based on the image.  
