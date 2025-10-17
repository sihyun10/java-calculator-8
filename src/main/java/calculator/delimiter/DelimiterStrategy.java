package calculator.delimiter;

import calculator.dto.DelimiterResult;

public interface DelimiterStrategy {

    DelimiterResult extract(String input);

    boolean judgment(String input);
}
