package calculator.exception;

public final class ErrorMessage {

    private ErrorMessage() {
    }

    public static final String EMPTY_CUSTOM_DELIMITER = "[ERROR] 커스텀 구분자가 비어 있습니다.";
    public static final String NO_NEWLINE_AFTER_CUSTOM = "[ERROR] 커스텀 구분자 선언 후 줄바꿈 문자가 없습니다.";
    public static final String MULTIPLE_CHAR_DELIMITER = "[ERROR] 커스텀 구분자는 하나의 문자만 입력할 수 있다.";
    public static final String NO_NUMBER_AFTER_CUSTOM = "[ERROR] 커스텀 구분자 지정 후 숫자가 입력되지 않았습니다.";
    public static final String EMPTY_INPUT = "[ERROR] 입력값이 비어있습니다.";
}
