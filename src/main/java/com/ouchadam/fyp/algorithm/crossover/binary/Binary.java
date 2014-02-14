package com.ouchadam.fyp.algorithm.crossover.binary;

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

    public Bit bitAt(int index) {
        return new Bit(value.substring(index, index + 1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Binary binary = (Binary) o;
        if (value != null ? !value.equals(binary.value) : binary.value != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

}
