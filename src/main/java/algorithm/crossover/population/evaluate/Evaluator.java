package algorithm.crossover.population.evaluate;

import algorithm.crossover.population.Evaluation;

public interface Evaluator<T> {
    Evaluation evaluate(T what);
}
