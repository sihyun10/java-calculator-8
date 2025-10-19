package calculator.exception;

public final class ErrorMessage {

    private ErrorMessage() {
    }

    public static final String EMPTY_CUSTOM_DELIMITER = "[ERROR] 커스텀 구분자가 비어 있습니다.";
    public static final String NO_NEWLINE_AFTER_CUSTOM = "[ERROR] 커스텀 구분자 선언 후 줄바꿈 문자가 없습니다.";
    public static final String INVALID_CUSTOM_DELIMITER_FORMAT = "[ERROR] 커스텀 구분자는 공백이 아닌 하나의 문자여야 합니다.";
    public static final String NO_NUMBER_AFTER_CUSTOM = "[ERROR] 커스텀 구분자 지정 후 숫자가 입력되지 않았습니다.";
    public static final String EMPTY_INPUT = "[ERROR] 입력값이 비어있습니다.";
    public static final String NON_NUMBER = "[ERROR] 숫자 이외의 값이 포함되어 있습니다: ";
    public static final String NEGATIVE_NUMBER = "[ERROR] 음수는 입력할 수 없습니다: ";
    public static final String INVALID_DELIMITER_USED = "[ERROR] 지정한 구분자가 아닌 다른 구분자가 입력되었습니다.";
    public static final String INVALID_INPUT_FORMAT = "[ERROR] 입력된 문자열이 구분자 형식에 맞지 않습니다.";
}
