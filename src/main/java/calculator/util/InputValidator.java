package calculator.util;

import calculator.exception.InvalidInputException;

public class InputValidator {

    private static final String CUSTOM_PREFIX = "//";
    private static final String NEWLINE = "\n";

    public static void validate(String input) {
        if (input == null || input.isBlank()) {
            throw new InvalidInputException("[ERROR] 입력값이 비어있습니다.");
        }
        if (input.startsWith(CUSTOM_PREFIX)) {
            validateCustomDelimiterSyntax(input);
        }
    }

    private static void validateCustomDelimiterSyntax(String input) {
        int nlIdx = input.indexOf(NEWLINE);

        if (nlIdx == -1) {
            throw new InvalidInputException("[ERROR] 커스텀 구분자 선언 후 줄바꿈 문자가 없습니다.");
        }

        String customDelimiter = input.substring(2, nlIdx);
        if (customDelimiter.isEmpty()) {
            throw new InvalidInputException("[ERROR] 커스텀 구분자가 비어 있습니다.");
        }

        if (customDelimiter.length() > 1) {
            throw new InvalidInputException("[ERROR] 커스텀 구분자는 하나의 문자만 입력할 수 있다.");
        }

        String numbersPart = input.substring(nlIdx + 1);
        if (numbersPart.isEmpty()) {
            throw new InvalidInputException("[ERROR] 커스텀 구분자 지정 후 숫자가 입력되지 않았습니다.");
        }
    }
}
