package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

import com.ouchadam.fyp.presentation.RuleContainer;

import java.util.List;

public interface FitnessEvaluator<T> {
    FitnessValue evaluate(T what, List<RuleContainer<T>> rules);
}
