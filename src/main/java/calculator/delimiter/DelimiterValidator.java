package calculator.delimiter;

import static java.util.regex.Pattern.quote;

import calculator.exception.ErrorMessage;
import calculator.exception.InvalidInputException;
import java.util.regex.Pattern;

public class DelimiterValidator {

    public static void defaultDelimiterUsage(String input) {
        if (!Pattern.matches("^[0-9,:]*$", input)) {
            throw new InvalidInputException(ErrorMessage.INVALID_DELIMITER_USED);
        }
    }

    public static void customDelimiterUsage(String numbersPart, String customDelimiter) {
        String allowedPattern = "^[0-9" + quote(customDelimiter) + "]*$";
        if (!Pattern.matches(allowedPattern, numbersPart)) {
            throw new InvalidInputException(ErrorMessage.INVALID_DELIMITER_USED);
        }
    }
}
