package com.ouchadam.fyp.algorithm.crossover;

public interface Crossover<T> {
    T crossover(T parentX, T parentY);
}
