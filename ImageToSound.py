#!/usr/bin/python

import argparse
import subprocess
import sys

def callBashCommand(myCommand):
	commandsList = myCommand.split()
	subprocess.call(commandsList)

def main():
	parser = argparse.ArgumentParser(description="Converts an image to sound")
	parser.add_argument('-i', dest='myInput', required=True)
	parser.add_argument('-o', dest='output', required=True)
	args = parser.parse_args()
	if args.myInput:
		myInput = str(args.myInput)
	else:
		print("Args missing")
		sys.exit()
	
	if args.output:
		output = args.output
	else:
		print("Args missing")
		sys.exit()
	print("Converting from image to text")
	callBashCommand("java -jar Image2txt.jar " + myInput)
	print("Converting from text to dat")
	callBashCommand("./txt2dat output.txt output.dat")
	print("Converting from dat to wav")
	callBashCommand("sox output.dat " + output)
	print("Cleaning up")
	callBashCommand("rm output.txt")
	callBashCommand("rm output.dat")
	print("Complete!")

if __name__ == '__main__':
	main()
