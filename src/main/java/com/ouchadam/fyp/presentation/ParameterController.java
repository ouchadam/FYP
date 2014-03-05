package com.ouchadam.fyp.presentation;

public interface ParameterController {
    int initialPopulation();
    int maxPopulation();
    int acceptableFitness();
    int mutationPercent();
    int crossoverPercent();
    void enable();
    void disable();
}
