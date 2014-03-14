package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.presentation.ParameterController;
import com.ouchadam.fyp.presentation.tab.rule.RuleContainer;
import com.ouchadam.fyp.presentation.RuleController;

import java.util.List;

public class AlgorithmParams {

    private final int initalPopulationSize;
    private final int acceptableFitnessValue;
    private final int mutationPercent;
    private final int crossoverPercent;
    private final int maxPopulationSize;
    private final List<RuleContainer<Member>> rules;

    public AlgorithmParams(int initalPopulationSize, int maxPopulationSize, int acceptableFitnessValue, int mutationPercent, int crossoverPercent, List<RuleContainer<Member>> rules) {
        this.initalPopulationSize = initalPopulationSize;
        this.maxPopulationSize = maxPopulationSize;
        this.acceptableFitnessValue = acceptableFitnessValue;
        this.mutationPercent = mutationPercent;
        this.crossoverPercent = crossoverPercent;
        this.rules = rules;
    }

    public static AlgorithmParams from(ParameterController pc, RuleController rc) {
        return new AlgorithmParams(pc.initialPopulation(), pc.maxPopulation(), pc.acceptableFitness(), pc.mutationPercent(), pc.crossoverPercent(), rc.get());
    }

    public int getInitalPopulationSize() {
        return initalPopulationSize;
    }

    public int getMaxPopulationSize() {
        return maxPopulationSize;
    }

    public int getAcceptableFitnessValue() {
        return acceptableFitnessValue;
    }

    public int getMutationPercent() {
        return mutationPercent;
    }

    public int getCrossoverPercent() {
        return crossoverPercent;
    }

    public List<RuleContainer<Member>> getRules() {
        return rules;
    }

}
