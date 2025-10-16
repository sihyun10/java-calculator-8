package calculator.model;

import java.util.List;

public class Numbers {

    private final List<Number> numbers;

    public Numbers(List<String> inputs) {
        this.numbers = inputs.stream()
                .filter(input -> !input.isBlank())
                .map(Number::new)
                .toList();
    }

    public int sum() {
        return numbers.stream()
                .mapToInt(Number::getValue)
                .sum();
    }

    public List<Number> getNumbers() {
        return List.copyOf(numbers);
    }
}
