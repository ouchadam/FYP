package algorithm;

import algorithm.crossover.population.*;
import algorithm.crossover.population.evaluate.Evaluator;
import algorithm.crossover.population.evaluate.FitnessFactory;
import algorithm.crossover.population.evaluate.PopulationEvaluator;

public class GeneticAlgorithm {

    private final static int INITIAL_POPULATION_SIZE = 2;
    private final static int MAX_POPULATION_SIZE = 10;
    private final static int ACCEPTABLE_FITNESS_VALUE = 10;

    private final PopulationMutator mutator;
    private final PopulationCrossover crossover;
    private final Evaluator<Population> evaluator;
    private final PopulationPruner pruner;
    private final PopulationCreator populationCreator;

    public static GeneticAlgorithm newInstance() {
        return new GeneticAlgorithm(new PopulationCreator(new PopulationCreator.MemberCreator()),
                new PopulationMutator(),
                new PopulationCrossover(),
                new PopulationEvaluator(new FitnessFactory()),
                new PopulationPruner(MAX_POPULATION_SIZE));
    }

    GeneticAlgorithm(PopulationCreator creator, PopulationMutator mutator, PopulationCrossover crossover, Evaluator<Population> evaluator, PopulationPruner pruner) {
        populationCreator = creator;
        this.mutator = mutator;
        this.crossover = crossover;
        this.evaluator = evaluator;
        this.pruner = pruner;
    }

    public Population work() {
        // TODO create initial population
        // TODO loop mutation > select best > crossover
        return generation(createInitalPopulation());
    }

    private Population createInitalPopulation() {
        return populationCreator.createPopulation(INITIAL_POPULATION_SIZE);
    }

    private Population generation(Population population) {
        // TODO create new population generation via breeding
        Evaluation evaluation = evaluator.evaluate(mutator.mutate(crossover.crossover(pruner.prune(population))));
        return evaluation.fitnessValue().get() >= ACCEPTABLE_FITNESS_VALUE ? evaluation.population() : generation(evaluation.population());
    }

}
