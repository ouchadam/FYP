package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.crossover.PopulationCrossover;

public class PopulationCrosser implements PopulationCrossover {

    private final PopulationCrossover populationCrossover;
    private final int maxPopulationSize;

    public PopulationCrosser(PopulationCrossover populationCrossover, int maxPopulationSize) {
        this.populationCrossover = populationCrossover;
        this.maxPopulationSize = maxPopulationSize;
    }

    @Override
    public Population crossover(Population population) {
//        return crossoverPopulation(population.shuffle());
        Population shuffledPopulation = population.shuffle();
        Population newPopulation = population;
        do {
            newPopulation = Population.fromSubPopulation(newPopulation, crossoverPopulation(shuffledPopulation));
        } while (newPopulation.size() < maxPopulationSize);
        return newPopulation.getSubPopulation(0, maxPopulationSize);
    }

    private Population crossoverPopulation(Population population) {
        return populationCrossover.crossover(population);
    }

}
