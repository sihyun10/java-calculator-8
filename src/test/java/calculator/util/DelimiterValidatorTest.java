package calculator.util;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.exception.ErrorMessage;
import calculator.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DelimiterValidatorTest {

    @Test
    @DisplayName("기본 구분자(, :)만 입력했을 경우 정상 통과")
    void 기본구분자만_입력() {
        assertThatCode(() -> DelimiterValidator.defaultDelimiterUsage("1,2:3,4"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("기본 구분자(, :) 이외의 문자를 사용했을 경우 예외 발생")
    void 기본구분자_이외의_문자_사용_예외() {
        assertThatThrownBy(() -> DelimiterValidator.defaultDelimiterUsage("1;2,3"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.INVALID_DELIMITER_USED);
    }

    @Test
    @DisplayName("지정된 커스텀 구분자만 입력했을 경우 정상 통과")
    void 지정된_커스텀구분자만_입력() {
        assertThatCode(() -> DelimiterValidator.customDelimiterUsage("1#2#4#5", "#"))
                .doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("지정된 커스텀 구분자 이외의 문자를 사용했을 경우 예외 발생")
    void 지정된_커스텀구분자_이외의_문자_사용_예외() {
        assertThatThrownBy(() -> DelimiterValidator.customDelimiterUsage("1#2;4#5", "#"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.INVALID_DELIMITER_USED);
    }
}
