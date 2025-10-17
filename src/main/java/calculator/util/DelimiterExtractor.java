package calculator.util;

import calculator.delimiter.CustomDelimiterStrategy;
import calculator.delimiter.DefaultDelimiterStrategy;
import calculator.delimiter.DelimiterStrategy;
import calculator.dto.DelimiterResult;
import calculator.exception.ErrorMessage;
import calculator.exception.InvalidInputException;
import java.util.List;

public class DelimiterExtractor {

    private final List<DelimiterStrategy> strategies;

    public DelimiterExtractor() {
        this.strategies = List.of(new DefaultDelimiterStrategy(), new CustomDelimiterStrategy());
    }

    public DelimiterResult extract(String input) {
        String normalizedInput = normalizeInput(input);

        return strategies.stream()
                .filter(strategy -> strategy.judgment(normalizedInput))
                .findFirst()
                .map(strategy -> strategy.extract(normalizedInput))
                .orElseThrow(() -> new InvalidInputException(ErrorMessage.INVALID_INPUT_FORMAT));
    }

    private static String normalizeInput(String input) {
        if (input == null) {
            throw new InvalidInputException(ErrorMessage.EMPTY_INPUT);
        }
        return input.replace("\\n", "\n");
    }
}
