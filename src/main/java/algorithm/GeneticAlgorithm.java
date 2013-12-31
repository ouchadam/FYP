package algorithm;

import algorithm.population.*;

public class GeneticAlgorithm {

    private final PopulationCreator populationCreator;
    private final PopulationEvolver populationEvolver;
    private final FittestMemberFinder fittestFinder;

    public static GeneticAlgorithm newInstance() {
        return new GeneticAlgorithm(new BasicPopulationCreator(), new PopulationEvolver(), new FittestMemberFinder());
    }

    public GeneticAlgorithm(PopulationCreator populationCreator, PopulationEvolver populationEvolver, FittestMemberFinder fittestFinder) {
        this.populationCreator = populationCreator;
        this.populationEvolver = populationEvolver;
        this.fittestFinder = fittestFinder;
    }

    public Member generate() {
        Population initialPopulation = createInitialPopulation();
        Population evolvedPopulation = evolvePopulation(initialPopulation);
        return extractFittestMemberFrom(evolvedPopulation);
    }

    private Population createInitialPopulation() {
        return populationCreator.create();
    }

    private Population evolvePopulation(Population initialPopulation) {
        return populationEvolver.evolve(initialPopulation);
    }

    private Member extractFittestMemberFrom(Population population) {
        return fittestFinder.find(population);
    }
}
