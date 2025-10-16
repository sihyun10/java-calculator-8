package calculator.controller;

import calculator.dto.DelimiterResult;
import calculator.model.Numbers;
import calculator.util.DelimiterExtractor;
import calculator.util.StringSplitter;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.Arrays;

public class CalculatorController {

    public void start() {
        String input = InputView.readInput();

        DelimiterResult delimiterResult = DelimiterExtractor.extract(input);
        String[] split = StringSplitter.split(
                delimiterResult.getNumbersPart(),
                delimiterResult.getDelimiter());

        Numbers numbers = new Numbers(Arrays.asList(split));
        int result = numbers.sum();

        OutputView.printResult(result);
    }
}
