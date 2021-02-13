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
        boolean upperCaseAll = false;
        String ioPattern = "([A-Za-z]+\\.txt)";
        String inputFileName = "";
        String outputFileName = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the input file name: ");
        boolean validate = false;
        while (!validate) {
            inputFileName = scan.next();
            boolean matches = inputFileName.matches(ioPattern);
            if (matches) {
                validate = true;
            } else {
                System.out.println("Invalid input, please try again: ");
            }
        }

        System.out.println("Please enter the output file name: ");
        while (scan.hasNextLine()) {
            if (scan.next().matches(ioPattern)) {
                outputFileName = scan.next().trim();
            } else {
                System.out.println("Invalid input, please try again: ");
                scan.next();
            }
        }

        String inputTxtLocation = "E:\\Documents\\Uni\\bachelor-2\\semester-2\\CSCU9T4\\CSCUT4Practical2\\" + inputFileName;
        String outputFileLocation = "E:\\Documents\\Uni\\bachelor-2\\semester-2\\CSCU9T4\\CSCUT4Practical2\\" + outputFileName;

        File inputText = new File(inputTxtLocation);
        PrintWriter writer = new PrintWriter(new File(outputFileLocation));
        Scanner inputScan = new Scanner(inputText);
        inputScan.useDelimiter("\\n");
        String line;
        String firstname;
        String surname;
        String dateOfBirth;
        String[] details;

        try {
            while(scan.hasNextLine()) {
                line = scan.next().trim();
                details = lineSplitter(line);
                firstname = capitaliser(details[0], upperCaseAll);
                surname = capitaliser(details[1], upperCaseAll);
                dateOfBirth = dateFormatter(details[2]);
                writer.write(firstname + " " + surname + " " + dateOfBirth + "\n");
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


} // FilesInOut
