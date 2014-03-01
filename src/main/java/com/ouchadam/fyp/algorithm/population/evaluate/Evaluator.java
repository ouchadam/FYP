package com.ouchadam.fyp.algorithm.population.evaluate;

import com.ouchadam.fyp.algorithm.population.Evaluation;

public interface Evaluator<T> {
    Evaluation evaluate(T what);
}
