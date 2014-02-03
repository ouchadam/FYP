package algorithm.crossover.binary;

public class Binary {

    private final String value;

    public Binary(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int wordLength() {
        return value.length();
    }

    public boolean hasLargerWordLengthThan(Binary binary) {
        return this.value.length() > binary.value.length();
    }

    public int toDecimal() {
        return Integer.parseInt(value, 2);
    }
}
