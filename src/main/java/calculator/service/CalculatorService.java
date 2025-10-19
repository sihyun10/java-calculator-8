package calculator.service;

import calculator.delimiter.DelimiterExtractor;
import calculator.dto.DelimiterResult;
import calculator.model.Numbers;
import calculator.util.StringSplitter;
import java.util.Arrays;

public class CalculatorService {

    private final DelimiterExtractor delimiterExtractor;

    public CalculatorService(DelimiterExtractor delimiterExtractor) {
        this.delimiterExtractor = delimiterExtractor;
    }

    public int calculate(String input) {
        DelimiterResult delimiterResult = delimiterExtractor.extract(input);

        String[] split = StringSplitter.split(
                delimiterResult.getNumbersPart(),
                delimiterResult.getDelimiter());

        Numbers numbers = new Numbers(Arrays.asList(split));
        return numbers.sum();
    }
}
