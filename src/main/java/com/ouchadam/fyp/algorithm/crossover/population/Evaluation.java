package com.ouchadam.fyp.algorithm.crossover.population;

import com.ouchadam.fyp.algorithm.crossover.population.evaluate.OrderedPopulation;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessValue;

public class Evaluation {

    private OrderedPopulation orderedPopulation;

    public Evaluation(OrderedPopulation orderedPopulation) {
        this.orderedPopulation = orderedPopulation;
    }

    public FitnessValue fitnessValue(int index) {
        return orderedPopulation.getFitness(index);
    }

    public Population population() {
        return orderedPopulation.asPopulation();
    }
}
