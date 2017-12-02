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

## Example
The example image is a picture I took in Corsica.  Github would not allow a file over 25MB so I cut the song down from 3 minutes to 1.5 minutes using Audacity.  
Notice the repetition at the start.  This is caused by the blue sky causing the same note to be played.
