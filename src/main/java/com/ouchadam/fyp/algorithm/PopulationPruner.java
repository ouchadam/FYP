package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.population.Population;

class PopulationPruner {

    private final int maxPopulationSize;

    PopulationPruner(int maxPopulationSize) {
        this.maxPopulationSize = maxPopulationSize;
    }

    public Population prune(Population population) {
        if (population.size() > maxPopulationSize) {
            Population top10Percent = getBest(population);
            Population worstDuplicateFreePopulation = Population.fromSubPopulation(top10Percent, removeDuplicates(population));

            if (worstDuplicateFreePopulation.size() > maxPopulationSize) {
                return worstDuplicateFreePopulation.getSubPopulation(0, maxPopulationSize);
            }
            return worstDuplicateFreePopulation;
        }
        return population;
    }

    private int getTopTenPercent(Population generation) {
        return (int) Math.floor((float) generation.size() / 10f);
    }

    private Population removeDuplicates(Population population) {
        Population sub100 = population.getSubPopulation(getTopTenPercent(population) + 1, population.size());
        return sub100.removeDuplicates();
    }

    public Population getBest(Population generation) {
        return generation.getSubPopulation(0, getTopTenPercent(generation));
    }
}
