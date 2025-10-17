package calculator.model;

import calculator.exception.ErrorMessage;
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
            if (input.matches(".*[0-9].*") && input.matches(".*[^0-9].*")) {
                throw new InvalidInputException(ErrorMessage.INVALID_DELIMITER_USED);
            }
            throw new InvalidInputException(ErrorMessage.NON_NUMBER + input);
        }
    }

    private void validate(int value) {
        if (value < 0) {
            throw new InvalidInputException(ErrorMessage.NEGATIVE_NUMBER + value);
        }
    }

    public int getValue() {
        return value;
    }
}
