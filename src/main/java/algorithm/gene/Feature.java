package algorithm.gene;

import algorithm.fitness.FitnessValue;

public interface Feature<V> {
    V getValue();
    FitnessValue getFitness();
}
