package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.CrossoverFactory;
import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;
import com.ouchadam.fyp.algorithm.crossover.population.Population;
import com.ouchadam.fyp.algorithm.crossover.population.PopulationCreator;
import com.ouchadam.fyp.algorithm.crossover.population.PopulationCrossover;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.Evaluator;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.FitnessFactory;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.PopulationEvaluator;

public class GeneticAlgorithm {

    private final static int INITIAL_POPULATION_SIZE = 2;
    private final static int MAX_POPULATION_SIZE = 10;
    private final static int GENERATION_LIMIT = 1000;
    final static int ACCEPTABLE_FITNESS_VALUE = 80;

    private final PopulationMutator mutator;
    private final PopulationCrossover crossover;
    private final Evaluator<Population> evaluator;
    private final PopulationPruner pruner;
    private final PopulationCreator populationCreator;

    public static GeneticAlgorithm newInstance() {
        CrossoverFactory crossoverFactory = CrossoverFactory.newInstance();
        return new GeneticAlgorithm(
                new PopulationCreator(new PopulationCreator.MemberCreator(), new PopulationCrossover(crossoverFactory.singlePoint().note())),
                new PopulationMutator(),
                new PopulationCrossover(crossoverFactory.uniform().note()),
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

    public Evaluation work() {
        // TODO create initial population
        // TODO loop mutation > select best > crossover
        return loop(createInitialPopulation());
    }

    private Population createInitialPopulation() {
        return populationCreator.createPopulation(INITIAL_POPULATION_SIZE);
    }

    private Evaluation loop(Population population) {
        Evaluation evaluation = null;
        Population result = population;
        int index = 0;
        do {
            evaluation = evaluator.evaluate(mutator.mutate(crossover.crossover(pruner.prune(result))));
            result = evaluation.population();
            System.out.println("fitness value : " + evaluation.fitnessValue().get());
            if (index >= GENERATION_LIMIT) {
                System.out.println("Limit reached, breaking out");
                break;
            }
            index ++;
        } while (evaluation.fitnessValue().get() < ACCEPTABLE_FITNESS_VALUE);
        return evaluation;
    }

}
