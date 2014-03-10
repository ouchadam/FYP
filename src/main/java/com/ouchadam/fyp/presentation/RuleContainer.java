package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.evaluate.rule.FitnessRule;

public class RuleContainer<T> {

    public final FitnessRule<T> fitnessRule;
    public final RuleManager.RuleName ruleName;

    public RuleContainer(FitnessRule<T> fitnessRule, RuleManager.RuleName ruleName) {
        this.fitnessRule = fitnessRule;
        this.ruleName = ruleName;
    }
}
