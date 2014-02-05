package algorithm;

import algorithm.crossover.population.Population;

class PopulationPruner {

    private final int maxPopulationSize;

    PopulationPruner(int maxPopulationSize) {
        this.maxPopulationSize = maxPopulationSize;
    }

    public Population prune(Population population) {
        if (population.size() > maxPopulationSize) {
            int delta = population.size() - maxPopulationSize;
            // TODO remove worst - should be the last positions as the evaluation process should reorder the members
            return population.prune(delta);
        }
        return population;
    }
}
