package calculator.controller;

import calculator.dto.DelimiterResult;
import calculator.util.DelimiterExtractor;
import calculator.util.StringSplitter;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    public void start() {
        String input = InputView.readInput();

        DelimiterResult delimiterResult = DelimiterExtractor.extract(input);

        String[] result = StringSplitter.split(
                delimiterResult.getNumbersPart(),
                delimiterResult.getDelimiter());

        OutputView.printResult(result);
    }
}
