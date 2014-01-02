package algorithm.population;

public class PopulationEvolver {

    private final PopulationMutator populationMutator;

    public PopulationEvolver(PopulationMutator populationMutator) {
        this.populationMutator = populationMutator;
    }

    public Population evolve(Population initialPopulation) {
        return populationMutator.mutate(initialPopulation);
    }
}
