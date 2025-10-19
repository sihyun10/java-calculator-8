package calculator.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.delimiter.DelimiterExtractor;
import calculator.exception.ErrorMessage;
import calculator.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService(new DelimiterExtractor());
    }

    @Test
    @DisplayName("기본 구분자(쉼표, 콜론)로 합계 계산")
    void 기본_구분자로_합계_계산() {
        assertCalculatedResult("3:4,5", 12);
    }

    @Test
    @DisplayName("커스텀 구분자 사용 시 합계 계산")
    void 커스텀_구분자로_합계_계산() {
        assertCalculatedResult("//#\n3#4#5", 12);
    }

    @Test
    @DisplayName("한 개의 숫자만 입력해도 정상 계산")
    void 한_개의_숫자만_입력() {
        assertCalculatedResult("7", 7);
    }

    @Test
    @DisplayName("빈 문자열 입력 시 결과는 0")
    void 빈_문자열_입력() {
        assertCalculatedResult("    ", 0);
    }

    @Test
    @DisplayName("음수가 포함되어 입력된 경우 예외 발생")
    void 음수_포함시_예외발생() {
        // given
        String input = "-3,4:5:6";

        // when & then
        assertThatThrownBy(() -> calculatorService.calculate(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining(ErrorMessage.NEGATIVE_NUMBER);
    }

    @Test
    @DisplayName("커스텀 구분자 두개 이상 입력된 경우 예외 발생")
    void 커스텀구분자_두개이상_입력_예외발생() {
        // given
        String input = "//#;\n3#2;4";

        // when & then
        assertThatThrownBy(() -> calculatorService.calculate(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.MULTIPLE_CHAR_DELIMITER);
    }

    private void assertCalculatedResult(String input, int expected) {
        int result = calculatorService.calculate(input);
        assertThat(result).isEqualTo(expected);
    }
}
