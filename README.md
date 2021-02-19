# CSCUT4Practical2

My project consists of a single Java program that can format both input files with, and without middle initials.

The formatted output will depend on the inputted file. 

When the program is run, it will ask for an input file name. If 'input.txt' is entered, it will process the
normal input file. If inputm.txt is entered, the input file with middle initials will be processed.

Then the program will ask for an output file name. Any name with alphanumerical characters followed by '.txt'
will be valid. If the desired name already exists, it will return an error message and ask for a new name again,
until a valid name is entered.

Afterwards, the program will ask for a flag to determine if the output will be just capitalised or all uppercase.
If left empty, it will continue with just capitalising the first letter of each name string.

The program will then output the file with the desired name.