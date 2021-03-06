package com.ouchadam.fyp.algorithm;

public class Percentage {

    private static final int PERCENTAGE_COEFF = 100;

    public static int of(int value, int percent) {
        float normalisedValue = (float) value / PERCENTAGE_COEFF;
        return Math.round(normalisedValue * (float) percent);
    }

    public static int from(int value, int percentOf) {
        return (int) ((value / (float) percentOf) * PERCENTAGE_COEFF);
    }

}
