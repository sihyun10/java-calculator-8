package calculator.util;

import static java.util.regex.Pattern.quote;

import calculator.dto.DelimiterResult;
import calculator.exception.ErrorMessage;
import calculator.exception.InvalidInputException;

public class DelimiterExtractor {
    private static final String CUSTOM_PREFIX = "//";
    private static final String NEWLINE = "\n";
    private static final String DEFAULT_DELIMITER_REGEX = ",|:";

    public static DelimiterResult extract(String input) {
        String normalizedInput = normalizeInput(input);

        if (isCustomDelimiter(normalizedInput)) {
            InputValidator.customDelimiterSyntax(normalizedInput);
            return extractCustomDelimiter(normalizedInput);
        }

        return extractDefaultDelimiter(normalizedInput);
    }

    private static String normalizeInput(String input) {
        if (input == null) {
            throw new InvalidInputException(ErrorMessage.EMPTY_INPUT);
        }
        return input.replace("\\n", "\n");
    }

    private static boolean isCustomDelimiter(String input) {
        return input.startsWith(CUSTOM_PREFIX);
    }

    private static DelimiterResult extractCustomDelimiter(String input) {
        int nlIdx = input.indexOf(NEWLINE);
        String customDelimiter = input.substring(2, nlIdx);
        String numbersPart = input.substring(nlIdx + 1);
        return new DelimiterResult(quote(customDelimiter), numbersPart);
    }

    private static DelimiterResult extractDefaultDelimiter(String input) {
        return new DelimiterResult(DEFAULT_DELIMITER_REGEX, input);
    }
}
