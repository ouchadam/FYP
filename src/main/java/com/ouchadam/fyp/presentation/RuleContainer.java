package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.evaluate.rule.FitnessRule;

public class RuleContainer<T> {

    public final FitnessRule<T> fitnessRule;
    public final RuleName ruleName;
    public final int weight;

    public RuleContainer(FitnessRule<T> fitnessRule, RuleName ruleName, int weight) {
        this.fitnessRule = fitnessRule;
        this.ruleName = ruleName;
        this.weight = weight;
    }
}
