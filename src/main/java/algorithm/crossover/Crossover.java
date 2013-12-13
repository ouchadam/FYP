package algorithm.crossover;

public interface Crossover<T> {
    T crossover(T parentX, T parentY);
}
