package calculator.dto;

public class DelimiterResult {

    private final String delimiter;
    private final String numbersPart;

    public DelimiterResult(String delimiter, String numbersPart) {
        this.delimiter = delimiter;
        this.numbersPart = numbersPart;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getNumbersPart() {
        return numbersPart;
    }
}
