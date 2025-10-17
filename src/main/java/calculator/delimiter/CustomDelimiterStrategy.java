package calculator.delimiter;

import static java.util.regex.Pattern.quote;

import calculator.dto.DelimiterResult;
import calculator.util.DelimiterValidator;
import calculator.util.InputValidator;

public class CustomDelimiterStrategy implements DelimiterStrategy {

    private static final String CUSTOM_PREFIX = "//";
    private static final String NEWLINE = "\n";

    @Override
    public boolean judgment(String input) {
        return input.startsWith(CUSTOM_PREFIX);
    }

    @Override
    public DelimiterResult extract(String input) {
        InputValidator.customDelimiterSyntax(input);

        int nlIdx = input.indexOf(NEWLINE);
        String customDelimiter = input.substring(2, nlIdx);
        String numbersPart = input.substring(nlIdx + 1);

        DelimiterValidator.customDelimiterUsage(numbersPart, customDelimiter);

        return new DelimiterResult(quote(customDelimiter), numbersPart);
    }
}
