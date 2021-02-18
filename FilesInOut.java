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
//
//        File inputFile;
//        File outputFile;
        boolean upperCaseAll = false;
//        String ioPattern = "([A-Za-z0-9]+\\.txt)";
//        String inputFileName = "";
//        String outputFileName = "";
//        String iWantUpperCase;
//        Scanner scan = new Scanner(System.in);
//
//        System.out.println("Please enter the input file name: ");
//        boolean validateInput = false;
//        while (!validateInput) {
//            inputFileName = scan.nextLine();
//            inputFile = new File(directory+inputFileName);
//            boolean matches = inputFileName.matches(ioPattern);
//            if (matches && inputFile.isFile()) {
//                validateInput = true;
//            } else {
//                System.out.println("Invalid input file name, please try again: ");
//            }
//        }
        Validator validator = new Validator(directory);

        /**
         * test for inputvalidator as a separate method
         */
        boolean validInput = validator.inputValidator();
        boolean validOutput = validator.outputValidator();
        upperCaseAll = validator.flagValidator();

//        System.out.println("Please enter the desired output file name: ");
//        boolean validateOutput = false;
//        while (!validateOutput) {
//            outputFileName = scan.nextLine();
//            outputFile = new File(directory+outputFileName);
//            boolean matches = outputFileName.matches(ioPattern);
//            if (matches && !outputFile.isFile()) {
//                validateOutput = true;
//            } else {
//                System.out.println("Invalid output file name, please try again: ");
//            }
//        }
//
//        System.out.println("Enter -u if output is wanted all in uppercase. Leave empty and press enter if not: ");
//        boolean validateUpper = false;
//        while (!validateUpper) {
//            iWantUpperCase = scan.nextLine();
//            if (iWantUpperCase.equalsIgnoreCase("-u")) {
//                upperCaseAll = true;
//                validateUpper = true;
//            } else if (iWantUpperCase.equals("")) {
//                validateUpper = true;
//            } else {
//                System.out.println("Invalid input, please try again.");
//                System.out.println("Enter -u if output is wanted all in uppercase. Leave empty and press enter if not: ");
//            }
//        }
//
        // create the printwriter that will create the output file (e.g. output.txt)
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(validator.getOutputFileName()));
        } catch (Exception e) {
            System.out.println(e);
        }

        File readInput = new File(validator.inputFileLocation());
        Scanner inputScan = null;
        try {
            inputScan = new Scanner(readInput);
        } catch (Exception e) {
            System.out.println(e);
        }

        inputScan.useDelimiter("\\n");
        String line;
        String firstName;
        String middleName;
        String surname;
        String dateOfBirth;
        String[] details;
        String formattedLine;

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

        writer.flush();
        writer.close();

    } // main

//    public static boolean inputValidator() {
//        File inputFile;
//        String ioPattern = "([A-Za-z0-9]+\\.txt)";
//        String inputFileName = "";
//        Scanner scan = new Scanner(System.in);
//
//        System.out.println("Please enter the input file name: ");
//        boolean validateInput = false;
//        while (!validateInput) {
//            inputFileName = scan.nextLine();
//            inputFile = new File(directory+inputFileName);
//            boolean matches = inputFileName.matches(ioPattern);
//            if (matches && inputFile.isFile()) {
//                validateInput = true;
//            } else {
//                System.out.println("Invalid input file name, please try again: ");
//            }
//        }
//    }

    public static String[] lineSplitter(String line) {
        String[] details;
        details = line.split("\\s");
        return details;
    }

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
        return "Check log for errors.";
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
        // return (firstName + " " + surname + " " + dateOfBirth + "\n");
        String fullName = firstName + " " + surname;
        return String.format("%-20s %10s\n", fullName, dateOfBirth);
    }

    public static String detailWriter(String firstName, String middleName, String surname, String dateOfBirth) {
        // return (firstName + " " + middleName + ". " + surname + " " + dateOfBirth + "\n");
        String fullName = firstName + " " + middleName + ". " + surname;
        return String.format("%-20s %10s\n", fullName, dateOfBirth);
    }

} // FilesInOut

