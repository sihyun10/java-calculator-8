package calculator.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.exception.ErrorMessage;
import calculator.exception.InvalidInputException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumbersTest {

    @Test
    @DisplayName("올바른 숫자 리스트 입력 시 더하기 기능 정상 작동")
    void 올바른_숫자리스트_입력시_더하기기능_정상() {
        Numbers numbers = new Numbers(List.of("1", "3", "6"));
        assertThat(numbers.sum()).isEqualTo(10);
    }

    @Test
    @DisplayName("음수가 포함된 숫자 리스트 입력 시 예외 발생")
    void 음수_포함_숫자리스트_입력시_예외() {
        assertThatThrownBy(() -> new Numbers(List.of("1", "-2", "3")))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.NEGATIVE_NUMBER + "-2");
    }

    @Test
    @DisplayName("문자열이 포함된 리스트 입력 시 예외 발생")
    void 문자열_포함_리스트_입력시_예외() {
        assertThatThrownBy(() -> new Numbers(List.of("a", "2", "3")))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(ErrorMessage.NON_NUMBER + "a");
    }

    @Test
    @DisplayName("공백은 무시되고 더하기에서 제외")
    void 공백_무시_더하기기능_정상() {
        Numbers numbers = new Numbers(List.of("1", "", "2", "     "));
        assertThat(numbers.sum()).isEqualTo(3);
    }
}
