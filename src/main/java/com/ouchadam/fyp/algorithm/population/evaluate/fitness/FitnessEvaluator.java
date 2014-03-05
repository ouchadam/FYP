package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

import java.util.List;

public interface FitnessEvaluator<T> {
    FitnessValue evaluate(T what, List<FitnessRule<T>> rules);
}
