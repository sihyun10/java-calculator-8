package calculator.delimiter;

import calculator.dto.DelimiterResult;

public class DefaultDelimiterStrategy implements DelimiterStrategy {

    private static final String CUSTOM_PREFIX = "//";
    private static final String DEFAULT_DELIMITER_REGEX = ",|:";

    @Override
    public boolean judgment(String input) {
        return !input.startsWith(CUSTOM_PREFIX);
    }

    @Override
    public DelimiterResult extract(String input) {
        DelimiterValidator.defaultDelimiterUsage(input);
        return new DelimiterResult(DEFAULT_DELIMITER_REGEX, input);
    }
}
