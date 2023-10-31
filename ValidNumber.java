import java.util.List;
//https://leetcode.com/problems/valid-number/
public class ValidNumber {

    public static void main(String[] args) {
        List<String> validNumbers = List.of("2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789");
        List<String> invalidNumbers = List.of("abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53", "Infinity", "959440.94f");
        validNumbers.stream().map(ValidNumber::isNumber).forEach(System.out::println);
        System.out.println();
        invalidNumbers.stream().map(ValidNumber::isNumber).forEach(System.out::println);
    }

    public static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNumber2(String s) {
        if(s.matches("[+-]?((\\d+)|((\\d+\\.)|(\\d+\\.\\d+)|(\\.\\d+)))([eE][+-]?\\d+)?")) {
            return true;
        } else {
            return false;
        }
    }
}
