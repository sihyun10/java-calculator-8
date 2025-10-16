package calculator.util;

import calculator.exception.ErrorMessage;
import calculator.exception.InvalidInputException;

public class InputValidator {

    private static final String NEWLINE = "\n";

    public static void customDelimiterSyntax(String input) {
        int nlIdx = input.indexOf(NEWLINE);

        if (nlIdx == -1) {
            throw new InvalidInputException(ErrorMessage.NO_NEWLINE_AFTER_CUSTOM);
        }

        String customDelimiter = input.substring(2, nlIdx);
        if (customDelimiter.isEmpty()) {
            throw new InvalidInputException(ErrorMessage.EMPTY_CUSTOM_DELIMITER);
        }

        if (customDelimiter.length() > 1) {
            throw new InvalidInputException(ErrorMessage.MULTIPLE_CHAR_DELIMITER);
        }

        String numbersPart = input.substring(nlIdx + 1);
        if (numbersPart.isEmpty()) {
            throw new InvalidInputException(ErrorMessage.NO_NUMBER_AFTER_CUSTOM);
        }
    }
}
