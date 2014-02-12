package com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness;

public interface FitnessEvaluator<T> {
    FitnessValue evaluate(T what, FitnessRule<T>... rules);
}
