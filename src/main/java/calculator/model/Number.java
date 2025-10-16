package calculator.model;

import calculator.exception.InvalidInputException;

public class Number {

    private final int value;

    public Number(String input) {
        this.value = parse(input);
        validate(value);
    }

    private int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("[ERROR] 숫자 이외의 값이 포함되어 있습니다: " + input);
        }
    }

    private void validate(int value) {
        if (value < 0) {
            throw new InvalidInputException("[ERROR] 음수는 입력할 수 없습니다: " + value);
        }
    }

    public int getValue() {
        return value;
    }
}
