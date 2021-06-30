import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    public static int A_COMMAND = 0;
    public static int C_COMMAND = 1;
    public static int L_COMMAND = 2;

    private File asmFile;
    private Scanner reader;
    
    private String currentLine;

    public Parser(String filePath) {

        // Open Hack Assembly file
        this.asmFile = new File(filePath);

        if (!this.asmFile.exists())
            throw new IllegalArgumentException("Invalid .asm file.");

        // Create file reader
        try {
            this.reader = new Scanner(this.asmFile);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Invalid .asm file.");
        }
    }

    public boolean hasMoreCommands() {
        return this.reader.hasNextLine();
    }

    private boolean isValidCommand(String command) {

        if (command == null || command.length() == 0)
            return false;

        if (command.equals("") || command.charAt(0) == '/')
            return false;
        else
            return true;
    }

    public void advance() {

        while (this.hasMoreCommands() && !this.isValidCommand(this.currentLine))
            this.currentLine = this.reader.nextLine().trim();
    }

    public int commandType() {

        if (this.currentLine.charAt(0) == '@')
            return A_COMMAND;
        else if (this.currentLine.charAt(0) == '(')
            return L_COMMAND;
        else
            return C_COMMAND;
    }

    public String symbol() {

        StringBuilder result = new StringBuilder();

        for (int i = 1; i < this.currentLine.length(); i++) {
            char currentChar = this.currentLine.charAt(i);

            if (currentChar == ')')
                break;

            result.append(currentChar);
        }

        return result.toString();
    }

    public String dest() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < this.currentLine.length(); i++) {
            char currentChar = this.currentLine.charAt(i);

            if (currentChar == '=')
                break;

            result.append(currentChar);
        }

        return result.toString();
    }

    public String comp() {

        StringBuilder result = new StringBuilder();

        int start = this.currentLine.indexOf("=") + 1;

        for (int i = start; i < this.currentLine.length(); i++) {
            char currentChar = this.currentLine.charAt(i);

            if (currentChar == ';')
                break;

            result.append(currentChar);
        }

        return result.toString();
    }

    public String jump() {

        StringBuilder result = new StringBuilder();

        int start = this.currentLine.indexOf(";") + 1;

        for (int i = start; i < this.currentLine.length(); i++) {
            result.append(this.currentLine.charAt(i));
        }

        return result.toString();
    }

}
