package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

public interface FitnessRule<T> {
    FitnessValue apply(T what);
}
