package algorithm;

import algorithm.fitness.EvaluatorFactory;
import algorithm.fitness.MemberFitness;
import algorithm.population.*;

public class GeneticAlgorithm {

    private final PopulationCreator populationCreator;
    private final PopulationEvolver populationEvolver;
    private final FittestMemberFinder fittestFinder;

    public static GeneticAlgorithm newInstance() {
        FittestMemberFinder fittestFinder = new FittestMemberFinder(new MemberFitness(new EvaluatorFactory()));
        return new GeneticAlgorithm(BasicPopulationCreator.newInstance(), new PopulationEvolver(new PopulationMutator(), EntirePopulationCrosser.newInstance(), fittestFinder), fittestFinder);
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
        return fittestFinder.find(population, 1).get(0);
    }
}
