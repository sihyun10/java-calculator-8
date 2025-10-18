package calculator.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class StringSplitterTest {

    @Test
    void 기본_구분자로_문자열_분리() {
        String[] result = StringSplitter.split("3:10,4,5", ",|:");
        assertThat(result).containsExactly("3", "10", "4", "5");
    }
}
