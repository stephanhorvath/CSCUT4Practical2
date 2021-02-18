import java.io.File;
import java.util.Scanner;

public class Validator {
    private String directory;
    private File inputFile;
    private File outputFile;
    private String ioPattern = "([A-Za-z0-9]+\\.txt)";
    private String inputFileName = "";
    private String outputFileName = "";
    private String iWantUpperCase;
    private Scanner scan = new Scanner(System.in);

    public Validator(String dir) {
        directory = dir;
    }

    public String getDirectory() { return directory; }

    public File getInputFile() { return inputFile; }
    public void setInputFile(String location) {
        inputFile = new File(location);
    }
    public String inputFileLocation() {
        return directory+getInputFileName();
    }

    public File getOutputFile() { return outputFile; }
    public void setOutputFile(String location) {
        outputFile = new File(location);
    }

    public String getIoPattern() { return ioPattern; }

    public String getInputFileName() { return inputFileName; }
    public void setInputFileName(String fileName) {
        inputFileName = fileName;
    }
    public String getOutputFileName() { return outputFileName; }
    public void setOutputFileName(String fileName) {
        outputFileName = fileName;
    }

    public boolean inputValidator() {
        System.out.println("Please enter the input file name: ");
        boolean validInput = false;
        while(!validInput) {
            setInputFileName(scan.nextLine());
            setInputFile(getDirectory()+getInputFileName());
            boolean matches = getInputFileName().matches(getIoPattern());
            if (matches && getInputFile().isFile()) {
                validInput = true;
            } else {
                System.out.println("Invalid input file name. Please try again.");
            }
        }
        return validInput;
    }

    public boolean outputValidator() {
        System.out.println("Please enter the desired output file name: ");
        boolean validOutput = false;
        while(!validOutput) {
            setOutputFileName(scan.nextLine());
            setOutputFile(getDirectory()+getOutputFileName());
            boolean matches = getOutputFileName().matches(getIoPattern());
            if (matches && !getOutputFile().isFile()) {
                validOutput = true;
            } else {
                System.out.println("Invalid output file name or file already exists. Please try again: ");
            }
        }
        return validOutput;
    }

    public boolean flagValidator() {
        System.out.println("Enter -u if output is wanted all in uppercase. Leave empty and press enter if not: ");
        boolean upperCaseAll = false;
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
        return upperCaseAll;
    }
}
