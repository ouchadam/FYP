package com.ouchadam.fyp.algorithm.evaluate.fitness;

public class FitnessValue {

    private final static int MAX_VALUE = 100;
    private final static int MIN_VALUE = 0;

    private final static FitnessValue MAX = new FitnessValue(MAX_VALUE);
    private final static FitnessValue MIN = new FitnessValue(MIN_VALUE);

    private int value;

    public FitnessValue(int value) {
        this.value = value;
    }

    public static FitnessValue max() {
        return MAX;
    }

    public static FitnessValue min() {
        return MIN;
    }

    public int get() {
        return value;
    }

    public FitnessValue weight(float weight) {
        int weightDelta = (int) (100 - (100 * weight));
        if (value + weightDelta > 100) {
            return FitnessValue.max();
        }
        return new FitnessValue(value + weightDelta);
    }

    // 0.5  85
    //

    // 0    100  50   25
    //      100  100  100


    // 1    100 50    25
    //      0   0     0

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FitnessValue that = (FitnessValue) o;
        if (value != that.value) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
