package com.ouchadam.fyp.algorithm.crossover.population.evaluate;

import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;

public interface Evaluator<T> {
    Evaluation evaluate(T what);
}
