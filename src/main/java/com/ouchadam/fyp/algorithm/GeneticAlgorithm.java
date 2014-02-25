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

    private final static int INITIAL_POPULATION_SIZE = 400;
    private final static int MAX_POPULATION_SIZE = 4000;
    private final static int GENERATION_LIMIT = 20000;
    final static int ACCEPTABLE_FITNESS_VALUE = 100;

    private final PopulationMutator mutator;
    private final PopulationCrossover crossover;
    private final Evaluator<Population> evaluator;
    private final PopulationPruner pruner;
    private final GenerationCallback generationCallback;
    private final GenerationHalter halter;
    private final PopulationCreator populationCreator;

    public static GeneticAlgorithm newInstance(GenerationCallback generationCallback, GenerationHalter halter) {
        CrossoverFactory crossoverFactory = CrossoverFactory.newInstance();
        return new GeneticAlgorithm(
                new PopulationCreator(new PopulationCreator.MemberCreator(), new PopulationCrossover(new IndexManager(new RandomIndexCreator()), crossoverFactory.singlePoint().note())),
                new PopulationMutator(new IndexManager(new RandomIndexCreator())),
                new PopulationCrossover(new IndexManager(new RandomIndexCreator()), crossoverFactory.uniform().note()),
                new PopulationEvaluator(new FitnessFactory()),
                new PopulationPruner(MAX_POPULATION_SIZE),
                generationCallback,
                halter);
    }

    GeneticAlgorithm(PopulationCreator creator, PopulationMutator mutator, PopulationCrossover crossover, Evaluator<Population> evaluator, PopulationPruner pruner, GenerationCallback generationCallback, GenerationHalter halter) {
        populationCreator = creator;
        this.mutator = mutator;
        this.crossover = crossover;
        this.evaluator = evaluator;
        this.pruner = pruner;
        this.generationCallback = generationCallback;
        this.halter = halter;
    }

    public Evaluation work() {
        // TODO create initial population
        // TODO loop mutation > select best > crossover
        halter.setHalted(false);
        return loop(createInitialPopulation(), halter);
    }

    private Population createInitialPopulation() {
        return populationCreator.createPopulation(INITIAL_POPULATION_SIZE);
    }

    private Evaluation loop(Population population, GenerationHalter halter) {
        Evaluation evaluation = null;
        Population generation = population;
        int index = 0;
        do {
            evaluation = evaluator.evaluate(Population.fromSubPopulation(pruner.prune(generation), mutator.mutate(crossover.crossover(pruner.getBest(generation)))));
            callback(evaluation, index);
            generation = evaluation.population();
            if (index >= GENERATION_LIMIT || halter.isHalted(evaluation, index)) {
                System.out.println("Limit reached or halted, breaking out");
                break;
            }
            index++;
        } while (evaluation.fitnessValue(0).get() < ACCEPTABLE_FITNESS_VALUE);
        return evaluation;
    }

    private void callback(Evaluation evaluation, int generationIndex) {
        if (generationCallback != null) {
            generationCallback.onGeneration(evaluation, generationIndex);
        }
    }

    public interface GenerationHalter {
        boolean isHalted(Evaluation evaluation, int index);
        void setHalted(boolean halted);
    }

}
