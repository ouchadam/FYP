package com.ouchadam.fyp.algorithm;

public class AlgorithmParams {

    public final int initalPopulationSize;
    public final int maxPopulationSize;
    public final int acceptableFitnessValue;

    public AlgorithmParams(int initalPopulationSize, int maxPopulationSize, int acceptableFitnessValue) {
        this.initalPopulationSize = initalPopulationSize;
        this.maxPopulationSize = maxPopulationSize;
        this.acceptableFitnessValue = acceptableFitnessValue;
    }

    @Override public String toString() {
        return "AlgorithmParams{" +
                "initalPopulationSize=" + initalPopulationSize +
                ", maxPopulationSize=" + maxPopulationSize +
                ", acceptableFitnessValue=" + acceptableFitnessValue +
                '}';
    }
}
