package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

public interface FitnessEvaluator<T> {
    FitnessValue evaluate(T what, FitnessRule<T>... rules);
}
