package algorithm.population;

import java.util.List;

public class PopulationEvolver {

    private final PopulationMutator populationMutator;
    private final PopulationCrosser populationCrosser;

    public PopulationEvolver(PopulationMutator populationMutator, PopulationCrosser populationCrosser) {
        this.populationMutator = populationMutator;
        this.populationCrosser = populationCrosser;
    }

    public Population evolve(Population initialPopulation) {

        // TODO population should be small, contain the best of the previous generation?
        // TODO this should then seed a new population (possibly add to previous?)  via crossover
        // TODO perform mutation
        // TODO select best individuals are start again  - breeding technique

        Population population = populationCrosser.crossover(initialPopulation);
        return populationMutator.mutate(population);
    }
}
