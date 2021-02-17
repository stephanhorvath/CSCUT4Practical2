import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import java.lang.Number;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {

    public static void main(String[] args) throws Exception {
        // Replace this with statements to set the file name (input) and file name (output).
        // Initially it will be easier to hardcode suitable file names.

        // Set up a new Scanner to read the input file.
        // Processing line by line would be sensible here.
        // Initially, echo the text to System.out to check you are reading correctly.
        // Then add code to modify the text to the output format.

        // Set up a new PrintWriter to write the output file.
        // Add suitable code into the above processing (because you need to do this line by line also.
        // That is, read a line, write a line, loop.

        // Finally, add code to read the filenames as arguments from the command line.

        // replace these with relevant locations
        String directory = "E:\\Documents\\Uni\\bachelor-2\\semester-2\\CSCU9T4\\CSCUT4Practical2\\";

        File inputFile;
        File outputFile;
        boolean upperCaseAll = false;
        String ioPattern = "([A-Za-z0-9]+\\.txt)";
        String inputFileName = "";
        String outputFileName = "";
        String iWantUpperCase;
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the input file name: ");
        boolean validateInput = false;
        while (!validateInput) {
            inputFileName = scan.nextLine();
            inputFile = new File(directory+inputFileName);
            boolean matches = inputFileName.matches(ioPattern);
            if (matches && inputFile.isFile()) {
                validateInput = true;
            } else {
                System.out.println("Invalid input file name, please try again: ");
            }
        }

        System.out.println("Please enter the desired output file name: ");
        boolean validateOutput = false;
        while (!validateOutput) {
            outputFileName = scan.nextLine();
            outputFile = new File(directory+outputFileName);
            boolean matches = outputFileName.matches(ioPattern);
            if (matches && !outputFile.isFile()) {
                validateOutput = true;
            } else {
                System.out.println("Invalid output file name, please try again: ");
            }
        }

        System.out.println("Enter -u if output is wanted all in uppercase. Leave empty and press enter if not: ");
        boolean validateUpper = false;
        while (!validateUpper) {
            iWantUpperCase = scan.nextLine();
            if (iWantUpperCase.equalsIgnoreCase("-u")) {
                upperCaseAll = true;
                validateUpper = true;
            } else if (iWantUpperCase.equals("")) {
                validateUpper = true;
            } else {
                System.out.println("Invalid input, please try again.");
                System.out.println("Enter -u if output is wanted all in uppercase. Leave empty and press enter if not: ");
            }
        }

        // create the printwriter that will create the output file (e.g. output.txt)
        PrintWriter writer = new PrintWriter(new File(outputFileName));

        File readInput = new File(directory+inputFileName);
        Scanner inputScan = new Scanner(readInput);
        inputScan.useDelimiter("\\n");
        String line;
        String firstName;
        String middleName;
        String surname;
        String dateOfBirth;
        String[] details;
        String formattedLine;

        try {
            while(inputScan.hasNextLine()) {
                line = inputScan.next().trim();
                details = lineSplitter(line);
                if (details.length > 3) {
                    firstName = capitaliser(details[0], upperCaseAll);
                    middleName = capitaliser(details[1], upperCaseAll);
                    surname = capitaliser(details[2], upperCaseAll);
                    dateOfBirth = dateFormatter(details[3]);
                    formattedLine = detailWriter(firstName, middleName, surname, dateOfBirth);
                    writer.write(formattedLine);
                } else {
                    firstName = capitaliser(details[0], upperCaseAll);
                    surname = capitaliser(details[1], upperCaseAll);
                    dateOfBirth = dateFormatter(details[2]);
                    formattedLine = detailWriter(firstName, surname, dateOfBirth);
                    writer.write(formattedLine);
                }
            }
        } catch (FileNotFoundException ex) {

        }

        writer.flush();
        writer.close();

    } // main

    public static String[] lineSplitter(String line) {
        String[] details;
        details = line.split("\\s");
        return details;
    }

    public static String dateFormatter(String date) throws Exception {
        DateFormat stringFormat = new SimpleDateFormat("ddmmyyyy");
        DateFormat neededFormat = new SimpleDateFormat("DD/MM/YYYY");
        Date newDate = stringFormat.parse(date);
        String formattedDate = neededFormat.format(newDate);
        return formattedDate;
    }

    public static String capitaliser(String name, boolean upperCaseAll) {
        String string;
        if (upperCaseAll) {
            string = name.toUpperCase();
        } else {
            string = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return string;
    }

    public static String detailWriter(String firstName, String surname, String dateOfBirth) {
        return (firstName + " " + surname + " " + dateOfBirth + "\n");
    }

    public static String detailWriter(String firstName, String middleName, String surname, String dateOfBirth) {
        return (firstName + " " + middleName + " " + surname + " " + dateOfBirth + "\n");
    }

} // FilesInOut
