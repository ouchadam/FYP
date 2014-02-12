package com.ouchadam.fyp.algorithm.crossover.population;

import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessValue;

public class Evaluation {

    private final FitnessValue fitnessValue;
    private final Population population;

    public Evaluation(FitnessValue fitnessValue, Population population) {
        this.fitnessValue = fitnessValue;
        this.population = population;
    }

    public FitnessValue fitnessValue() {
        return fitnessValue;
    }

    public Population population() {
        return population;
    }
}
