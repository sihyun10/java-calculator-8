package calculator;

import static java.util.regex.Pattern.*;

import calculator.exception.InvalidInputException;
import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        System.out.println(Arrays.toString(extractAndSplit(input)));
    }

    private static final String CUSTOM_PREFIX = "//";
    private static final String NEWLINE = "\n";
    private static final String DEFAULT_DELIMITER_REGEX = ",|:";

    public static String[] extractAndSplit(String input) {
        if (input == null || input.isBlank()) {
            throw new InvalidInputException("[ERROR] 입력값이 비어있습니다.");
        }

        input = input.replace("\\n", "\n");

        String delimiter;
        String numbersPart;

        if (input.startsWith(CUSTOM_PREFIX)) {
            int nlIdx = input.indexOf(NEWLINE);
            if (nlIdx == -1) {
                throw new InvalidInputException("[ERROR] 커스텀 구분자 선언 후 줄바꿈 문자가 없습니다.");
            }

            String customDelimiter = input.substring(2, nlIdx);
            if (customDelimiter.isEmpty()) {
                throw new InvalidInputException("[ERROR] 커스텀 구분자가 비어 있습니다.");
            }

            // 커스텀 구분자는 1글자만 허용한다
            if (customDelimiter.length() > 1) {
                throw new InvalidInputException("[ERROR] 커스텀 구분자는 하나의 문자만 입력할 수 있다.");
            }

            numbersPart = input.substring(nlIdx + 1);
            if (numbersPart.isEmpty()) {
                throw new InvalidInputException("[ERROR] 커스텀 구분자 지정 후 숫자가 입력되지 않았습니다.");
            }

            // 특수문자 처리
            delimiter = quote(customDelimiter);
        } else {
            delimiter = DEFAULT_DELIMITER_REGEX;
            numbersPart = input;
        }

        return numbersPart.split(delimiter);
    }
}
