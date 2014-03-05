package com.ouchadam.fyp.algorithm;

public class AlgorithmParams {

    public final int initalPopulationSize;
    public final int maxPopulationSize;
    public final int acceptableFitnessValue;
    public final int mutationPercent;
    public final int crossoverPercent;

    public AlgorithmParams(int initalPopulationSize, int maxPopulationSize, int acceptableFitnessValue, int mutationPercent, int crossoverPercent) {
        this.initalPopulationSize = initalPopulationSize;
        this.maxPopulationSize = maxPopulationSize;
        this.acceptableFitnessValue = acceptableFitnessValue;
        this.mutationPercent = mutationPercent;
        this.crossoverPercent = crossoverPercent;
    }

    @Override
    public String toString() {
        return "AlgorithmParams{" +
                "initalPopulationSize=" + initalPopulationSize +
                ", maxPopulationSize=" + maxPopulationSize +
                ", acceptableFitnessValue=" + acceptableFitnessValue +
                ", mutationPercent=" + mutationPercent +
                ", crossoverPercent=" + crossoverPercent +
                '}';
    }
}
