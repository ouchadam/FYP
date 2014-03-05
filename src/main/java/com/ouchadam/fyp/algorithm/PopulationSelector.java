package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.population.Population;

class PopulationSelector {

    private final Type type;

    public enum Type {
        ELITISM, TOURNAMENT
    }

    PopulationSelector(Type type) {
        this.type = type;
    }

    public Population selectFrom(Population population) {
        switch (type) {
            case ELITISM:
                return getTop20Percent(population);

            case TOURNAMENT:
                return null;
        }
        return null;
    }

    private Population getTop20Percent(Population population) {
        Population top20Percent = getBest(population);
        return Population.fromSubPopulation(top20Percent, removeDuplicates(population.getSubPopulation(top20Percent.size(), population.size())));
    }

    private Population getBest(Population generation) {
        return generation.getSubPopulation(0, get20PercentOfSize(generation));
    }

    private int get20PercentOfSize(Population generation) {
        return (int) Math.floor((float) generation.size() * 0.2);
    }

    private Population removeDuplicates(Population population) {
        Population sub100 = population.getSubPopulation(get20PercentOfSize(population) + 1, population.size());
        return sub100.removeDuplicates();
    }
}
