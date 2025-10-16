package calculator.util;

import static java.util.regex.Pattern.quote;

import calculator.dto.DelimiterResult;

public class DelimiterExtractor {
    private static final String CUSTOM_PREFIX = "//";
    private static final String NEWLINE = "\n";
    private static final String DEFAULT_DELIMITER_REGEX = ",|:";

    public static DelimiterResult extract(String input) {
        input = input.replace("\\n", "\n");
        InputValidator.validate(input);

        if (input.startsWith(CUSTOM_PREFIX)) {
            int nlIdx = input.indexOf(NEWLINE);
            String customDelimiter = input.substring(2, nlIdx);
            String numbersPart = input.substring(nlIdx + 1);
            return new DelimiterResult(quote(customDelimiter), numbersPart);
        }
        return new DelimiterResult(DEFAULT_DELIMITER_REGEX, input);
    }
}
