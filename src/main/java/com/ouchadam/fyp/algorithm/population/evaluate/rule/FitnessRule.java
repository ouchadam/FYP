package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;

public interface FitnessRule<T> {
    FitnessValue apply(T what);
}
