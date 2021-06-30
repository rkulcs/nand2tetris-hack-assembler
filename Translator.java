import java.util.Stack;

public class Translator {

    public static String decimalToBinary(int decimalValue) {

        StringBuilder result = new StringBuilder();
        Stack<String> digits = new Stack<String>();

        while (decimalValue != 0) {
            int remainder = decimalValue % 2;
            decimalValue /= 2;

            digits.push(Integer.toString(remainder));
        }

        while (digits.size() != 15)
            digits.push("0");

        while (!digits.isEmpty())
            result.append(digits.pop());

        return result.toString();
    }
    
    public static String translateACommand(String symbol) {
        return "0" + decimalToBinary(Integer.parseInt(symbol));
    }

    public static String translateCCommand(String d, String c, String j) {
        return "111" + Code.comp(c) + Code.dest(d) + Code.jump(j);
    }

}
