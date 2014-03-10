package com.ouchadam.fyp.algorithm.crossover.binary;

public class Bit {

    private static final int OFF_BIT = 0;
    private static final int ON_BIT = 1;
    private final int value;

    public static Bit on() {
        return new Bit(ON_BIT);
    }

    public static Bit off() {
        return new Bit(OFF_BIT);
    }

    Bit(String value) {
        this(Integer.parseInt(value));
    }

    Bit(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value != OFF_BIT && value != ON_BIT) {
            throw new RuntimeException("Tried to create a bit with : " + value);
        }
    }

    public Bit invert() {
        return new Bit(value == ON_BIT ? OFF_BIT : ON_BIT);
    }

    public int value() {
        return value;
    }
}
