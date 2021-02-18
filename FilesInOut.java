import org.w3c.dom.ls.LSOutput;

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

    public static void main(String[] args) {
        // replace string with relevant directory
        String directory = "E:\\Documents\\Uni\\bachelor-2\\semester-2\\CSCU9T4\\CSCUT4Practical2\\";
        boolean upperCaseAll = false;
        Validator validator = new Validator(directory);

        boolean validInput = validator.inputValidator();
        boolean validOutput = validator.outputValidator();
        upperCaseAll = validator.flagValidator();

        // create the printwriter that will create the output file (e.g. output.txt)
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(validator.getOutputFileName()));
        } catch (Exception e) {
            System.out.println(e);
        }

        // Create File to feed into the scanner
        File readInput = new File(validator.inputFileLocation());
        Scanner inputScan = null;
        try {
            inputScan = new Scanner(readInput);
        } catch (Exception e) {
            System.out.println(e);
        }

        // delimit each line by each newline character '\n'
        inputScan.useDelimiter("\\n");
        String line;
        String firstName;
        String middleName;
        String surname;
        String dateOfBirth;
        String[] details;
        String formattedLine;

        // begin reading from input file if it has lines to read from
        while(inputScan.hasNextLine()) {
            // trim each line in case there is whitespace
            line = inputScan.next().trim();

            // array of each split token from the line
            details = lineSplitter(line);

            // if there are more than 3 details, the person has a middle initial
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

        writer.flush();
        writer.close();

    } // main

    /**
     * Return an array where each index has a single token from the line.
     * @param line - a single line from the input file
     * @return a String array with size depending on each token from the line (3 or 4)
     */
    public static String[] lineSplitter(String line) {
        String[] details;
        details = line.split("\\s");
        return details;
    }

    /**
     * Convert the ddmmyyyy date from files into DD/MM/YYYY format
     * @param date - String with a date in ddmmyyyy format
     * @return String in DD/MM/YYYY format
     */
    public static String dateFormatter(String date) {
        try {
            DateFormat stringFormat = new SimpleDateFormat("ddmmyyyy");
            DateFormat neededFormat = new SimpleDateFormat("DD/MM/YYYY");
            Date newDate = stringFormat.parse(date);
            String formattedDate = neededFormat.format(newDate);
            return formattedDate;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Check log for errors - dateFormatter()";
    }

    /**
     * Capitalises the first letter of a String, or makes the entire word uppercase
     * @param name - Name to be proccessed
     * @param upperCaseAll - flag to determine if the whole word is uppercase
     * @return formatted String with first letter capitalised or the whole word
     */
    public static String capitaliser(String name, boolean upperCaseAll) {
        String string;
        if (upperCaseAll) {
            // convert to all uppercase
            string = name.toUpperCase();
        } else {
            // make first character uppercase, and concatenate the rest of the string
            string = name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return string;
    }

    /**
     * Return a formatted line to be printed to the output file
     * @param firstName - String
     * @param surname - String
     * @param dateOfBirth - String
     * @return formatted String with padding
     */
    public static String detailWriter(String firstName, String surname, String dateOfBirth) {
        // combine all names to make padding easier
        String fullName = firstName + " " + surname;
        return String.format("%-20s %10s\n", fullName, dateOfBirth);
    }

    /**
     * Return a formatted line to be printed to the output file
     * @param firstName - String
     * @param middleName - String
     * @param surname - String
     * @param dateOfBirth - String
     * @return formatted String with padding
     */
    public static String detailWriter(String firstName, String middleName, String surname, String dateOfBirth) {
        // combine all names to make padding easier
        String fullName = firstName + " " + middleName + ". " + surname;
        return String.format("%-20s %10s\n", fullName, dateOfBirth);
    }

} // FilesInOut

