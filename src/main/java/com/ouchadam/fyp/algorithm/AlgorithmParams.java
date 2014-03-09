package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.presentation.ParameterController;
import com.ouchadam.fyp.presentation.RuleContainer;
import com.ouchadam.fyp.presentation.RuleController;

import java.util.List;

public class AlgorithmParams {

    public final int initalPopulationSize;
    public final int maxPopulationSize;
    public final int acceptableFitnessValue;
    public final int mutationPercent;
    public final int crossoverPercent;
    public final List<RuleContainer<Member>> rules;

    public static AlgorithmParams from(ParameterController pc, RuleController rc) {
        return new AlgorithmParams(pc.initialPopulation(), pc.maxPopulation(), pc.acceptableFitness(), pc.mutationPercent(), pc.crossoverPercent(), rc.get());
    }

    public AlgorithmParams(int initalPopulationSize, int maxPopulationSize, int acceptableFitnessValue, int mutationPercent, int crossoverPercent, List<RuleContainer<Member>> rules) {
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
