package com.ouchadam.fyp.algorithm.evaluate.fitness;

import com.ouchadam.fyp.presentation.tab.rule.RuleContainer;

import java.util.List;

public interface FitnessEvaluator<T> {
    FitnessValue evaluate(T what, List<RuleContainer<T>> rules);
}
