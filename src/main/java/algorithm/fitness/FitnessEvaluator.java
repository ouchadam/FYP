package algorithm.fitness;

public interface FitnessEvaluator<T> {
    FitnessValue evaluate(T what);
}
