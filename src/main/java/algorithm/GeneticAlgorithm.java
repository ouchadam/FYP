package algorithm;

import algorithm.population.*;

public class GeneticAlgorithm {

    private final PopulationCreator populationCreator;
    private final PopulationMutator populationMutator;
    private final FittestMemberFinder fittestFinder;

    public static GeneticAlgorithm newInstance() {
        return new GeneticAlgorithm(new BasicPopulationCreator(new MemberFactory()), new PopulationMutator(), new FittestMemberFinder());
    }

    public GeneticAlgorithm(PopulationCreator populationCreator, PopulationMutator populationMutator, FittestMemberFinder fittestFinder) {
        this.populationCreator = populationCreator;
        this.populationMutator = populationMutator;
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
        return populationMutator.evolve(initialPopulation);
    }

    private Member extractFittestMemberFrom(Population population) {
        return fittestFinder.find(population);
    }
}
