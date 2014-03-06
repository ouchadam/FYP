package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.population.evaluate.rule.FitnessRule;

import java.util.List;

public class AlgorithmParams {

    public final int initalPopulationSize;
    public final int maxPopulationSize;
    public final int acceptableFitnessValue;
    public final int mutationPercent;
    public final int crossoverPercent;
    public final List<FitnessRule<Member>> rules;

    public AlgorithmParams(int initalPopulationSize, int maxPopulationSize, int acceptableFitnessValue, int mutationPercent, int crossoverPercent, List<FitnessRule<Member>> rules) {
        this.initalPopulationSize = initalPopulationSize;
        this.maxPopulationSize = maxPopulationSize;
        this.acceptableFitnessValue = acceptableFitnessValue;
        this.mutationPercent = mutationPercent;
        this.crossoverPercent = crossoverPercent;
        this.rules = rules;
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
