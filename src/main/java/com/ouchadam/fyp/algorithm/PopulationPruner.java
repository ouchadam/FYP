package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.population.Population;

class PopulationPruner {

    private final int maxPopulationSize;

    PopulationPruner(int maxPopulationSize) {
        this.maxPopulationSize = maxPopulationSize;
    }

    public Population prune(Population population) {
        if (population.size() > maxPopulationSize) {
            Population top10Percent = population.getSubPopulation(0, population.size() / 10);
            Population population1 = Population.fromSubPopulation(top10Percent, removeDuplicates(population)).shuffle();

            if (population1.size() > maxPopulationSize) {
                return population1.getSubPopulation(0, maxPopulationSize);
            }
            return population1;
        }
        return population;
    }

    private Population removeDuplicates(Population population) {
        Population sub100 = population.getSubPopulation((population.size() / 10) + 1, population.size());
        return sub100.removeDuplicates();
    }
}
