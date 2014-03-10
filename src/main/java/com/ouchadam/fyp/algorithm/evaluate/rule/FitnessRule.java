package com.ouchadam.fyp.algorithm.evaluate.rule;

import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;

public interface FitnessRule<T> {
    FitnessValue apply(T what);
}
