package algorithm.crossover.population.evaluate.fitness;

public interface FitnessRule<T> {
    FitnessValue apply(T what);
}
