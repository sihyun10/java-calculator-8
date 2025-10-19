package calculator.delimiter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.dto.DelimiterResult;
import calculator.exception.ErrorMessage;
import calculator.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DelimiterExtractorTest {

    private final DelimiterExtractor delimiterExtractor;

    public DelimiterExtractorTest() {
        this.delimiterExtractor = new DelimiterExtractor();
    }

    @Test
    @DisplayName("기본 구분자로 구분자 추출 정상 작동")
    void 기본_구분자로_구분자_추출() {
        DelimiterResult result = delimiterExtractor.extract("1,2:3,4");

        assertThat(result.getDelimiter()).isEqualTo(",|:");
        assertThat(result.getNumbersPart()).isEqualTo("1,2:3,4");
    }

    @Test
    @DisplayName("커스텀 구분자로 구분자 추출 정상 작동")
    void 커스텀_구분자로_구분자_추출() {
        DelimiterResult result = delimiterExtractor.extract("//#\n1#2#3");

        assertThat(result.getDelimiter()).isEqualTo("\\Q#\\E");
        assertThat(result.getNumbersPart()).isEqualTo("1#2#3");
    }

    @Test
    @DisplayName("입력값이 null이면 예외 발생")
    void 입력_null_예외() {
        assertThatThrownBy(() -> delimiterExtractor.extract(null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.EMPTY_INPUT);
    }

    @Test
    @DisplayName("//로 시작했지만 줄바꿈이 없으면 예외 발생")
    void 줄바꿈_없음_예외() {
        assertThatThrownBy(() -> delimiterExtractor.extract("//;1;2;3"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.NO_NEWLINE_AFTER_CUSTOM);
    }

    @Test
    @DisplayName("커스텀 구분자가 비어있으면 예외 발생")
    void 커스텀구분자_비어있음_예외() {
        assertThatThrownBy(() -> delimiterExtractor.extract("//\n1;2;3"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.EMPTY_CUSTOM_DELIMITER);
    }

    @Test
    @DisplayName("구분자 지정 후 숫자가 입력되지 않으면 예외 발생")
    void 지정후_입력안됨_예외() {
        assertThatThrownBy(() -> delimiterExtractor.extract("//;\n"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.NO_NUMBER_AFTER_CUSTOM);
    }

    @Test
    @DisplayName("커스텀 구분자가 2글자 이상 입력되면 예외 발생")
    void 커스텀구분자_두글자_이상_예외() {
        assertThatThrownBy(() -> delimiterExtractor.extract("//:'\n2:3'5"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.INVALID_CUSTOM_DELIMITER_FORMAT);
    }

    @Test
    @DisplayName("커스텀 구분자로 기본 구분자를 입력할 경우 예외 발생")
    void 커스텀구분자_기본구분자로_입력_예외() {
        assertThatThrownBy(() -> delimiterExtractor.extract("//:\n2:3:4"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.DUPLICATE_DEFAULT_DELIMITER);
    }
}
