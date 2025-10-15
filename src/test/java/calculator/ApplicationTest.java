package calculator;

import calculator.exception.InvalidInputException;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

    @Test
    @DisplayName("기본 구분자(쉼표, 콜론)로 분리된다")
    void 기본_구분자_분리() {
        String[] result = Application.extractAndSplit("1,2:3");
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("커스텀 구분자로 분리된다")
    void 커스텀_구분자_분리() {
        String[] result = Application.extractAndSplit("//'\n1'2'3");
        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("입력값이 null이면 예외 발생")
    void 입력_null_예외() {
        Assertions.assertThrows(
            InvalidInputException.class,
            () -> Application.extractAndSplit(null), "[ERROR] 입력값이 비어있습니다.");
    }

    @Test
    @DisplayName("입력값이 공백이면 예외 발생")
    void 입력_공백_예외() {
        Assertions.assertThrows(
            InvalidInputException.class,
            () -> Application.extractAndSplit("      "), "[ERROR] 입력값이 비어있습니다.");
    }

    @Test
    @DisplayName("//로 시작했지만 줄바꿈이 없으면 예외 발생")
    void 줄바꿈_없음_예외() {
        Assertions.assertThrows(
            InvalidInputException.class,
            () -> Application.extractAndSplit("//;1;2;3"), "[ERROR] 커스텀 구분자 선언 후 줄바꿈 문자가 없습니다."
        );
    }

    @Test
    @DisplayName("커스텀 구분자가 비어있으면 예외 발생")
    void 커스텀구분자_비어있음_예외() {
        Assertions.assertThrows(
            InvalidInputException.class,
            () -> Application.extractAndSplit("//\n1;2;3"), "[ERROR] 커스텀 구분자가 비어 있습니다."
        );
    }

    @Test
    @DisplayName("구분자 지정 후 숫자가 입력되지 않으면 예외 발생")
    void 지정후_입력안됨_예외() {
        Assertions.assertThrows(
            InvalidInputException.class,
            () -> Application.extractAndSplit("//;\n"), "[ERROR] 커스텀 구분자 지정 후 숫자가 입력되지 않았습니다."
        );
    }
}
