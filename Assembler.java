public class Assembler {

    public static void main(String[] args) {

        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "A single command line argument is required.");
        }

        Parser parser = new Parser(args[0]);
    }

}
