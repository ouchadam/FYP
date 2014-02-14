package com.ouchadam.fyp.algorithm.crossover.binary;

public class Bit {

    private final int value;

    Bit(String value) {
        this(Integer.parseInt(value));
    }

    Bit(int value) {
        this.value = value;
    }

    public Bit invert() {
        return new Bit(value == 1 ? 0 : 1);
    }

    public int value() {
        return value;
    }
}
