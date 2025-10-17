package calculator.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.exception.ErrorMessage;
import calculator.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DelimiterExtractorTest {

    @Test
    @DisplayName("입력값이 null이면 예외 발생")
    void 입력_null_예외() {
        assertThatThrownBy(() -> DelimiterExtractor.extract(null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.EMPTY_INPUT);
    }

    @Test
    @DisplayName("//로 시작했지만 줄바꿈이 없으면 예외 발생")
    void 줄바꿈_없음_예외() {
        assertThatThrownBy(() -> DelimiterExtractor.extract("//;1;2;3"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.NO_NEWLINE_AFTER_CUSTOM);
    }

    @Test
    @DisplayName("커스텀 구분자가 비어있으면 예외 발생")
    void 커스텀구분자_비어있음_예외() {
        assertThatThrownBy(() -> DelimiterExtractor.extract("//\n1;2;3"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.EMPTY_CUSTOM_DELIMITER);
    }

    @Test
    @DisplayName("구분자 지정 후 숫자가 입력되지 않으면 예외 발생")
    void 지정후_입력안됨_예외() {
        assertThatThrownBy(() -> DelimiterExtractor.extract("//;\n"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.NO_NUMBER_AFTER_CUSTOM);
    }

    @Test
    @DisplayName("커스텀 구분자가 2글자 이상 입력되면 예외 발생")
    void 커스텀구분자_두글자_이상_예외() {
        assertThatThrownBy(() -> DelimiterExtractor.extract("//:'\n2:3'5"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.MULTIPLE_CHAR_DELIMITER);
    }
}
