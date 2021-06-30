import java.io.FileWriter;
import java.io.IOException;

public class Assembler {

    public static void main(String[] args) {

        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "A single command line argument is required.");
        }

        Parser parser = new Parser(args[0]);
        FileWriter writer;

        StringBuilder fileName = new StringBuilder();

        for (int i = 0; i < args[0].length(); i++) {
            char currentChar = args[0].charAt(i);

            if (currentChar == '.')
                break;

            fileName.append(currentChar);
        }

        try {
            writer = new FileWriter(fileName.toString() + ".hack");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        
        while (parser.hasMoreCommands()) {
            parser.advance();

            // Translate line and add it to .hack file
            String translation;

            if (parser.commandType() == Parser.A_COMMAND) {
                translation = Translator.translateACommand(parser.symbol());
            } else {
                translation = Translator.translateCCommand(
                    parser.dest(), parser.comp(), parser.jump());
            }

            try {
                writer.append(translation);
                writer.append(System.lineSeparator());
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return;
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
    }

}
