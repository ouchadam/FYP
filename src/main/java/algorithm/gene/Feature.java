package algorithm.gene;

import algorithm.fitness.EvaluatorType;

public interface Feature<V> {
    V getValue();
    EvaluatorType getEvaluatorType();
}
