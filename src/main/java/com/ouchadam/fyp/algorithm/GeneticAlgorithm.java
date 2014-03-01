package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.CrossoverFactory;
import com.ouchadam.fyp.algorithm.population.*;
import com.ouchadam.fyp.algorithm.population.evaluate.Evaluator;
import com.ouchadam.fyp.algorithm.population.evaluate.FitnessFactory;
import com.ouchadam.fyp.algorithm.population.evaluate.PopulationEvaluator;

public class GeneticAlgorithm {

    private final Mutator<Population> mutator;
    private final PopulationCrossover crossover;
    private final Evaluator<Population> evaluator;
    private final PopulationPruner pruner;
    private final GenerationCallback generationCallback;
    private final GenerationHalter halter;
    private final AlgorithmParams algorithmParams;
    private final Creator<Population> populationCreator;

    public static GeneticAlgorithm newInstance(GenerationCallback generationCallback, AlgorithmParams algorithmParams, GenerationHalter halter) {
        CrossoverFactory crossoverFactory = CrossoverFactory.newInstance();
        return new GeneticAlgorithm(
                new PopulationCreator(new PopulationCreator.MemberCreator(), new PopulationCrossover(new IndexManager(new RandomIndexCreator()), crossoverFactory.singlePoint().note())),
                new PopulationMutator(new IndexManager(new RandomIndexCreator())),
                new PopulationCrossover(new IndexManager(new RandomIndexCreator()), crossoverFactory.uniform().note()),
                new PopulationEvaluator(new FitnessFactory()),
                new PopulationPruner(algorithmParams.maxPopulationSize),
                generationCallback,
                halter,
                algorithmParams);
    }

    GeneticAlgorithm(Creator<Population> creator, Mutator<Population> mutator, PopulationCrossover crossover, Evaluator<Population> evaluator,
                     PopulationPruner pruner, GenerationCallback generationCallback, GenerationHalter halter, AlgorithmParams algorithmParams) {
        this.populationCreator = creator;
        this.mutator = mutator;
        this.crossover = crossover;
        this.evaluator = evaluator;
        this.pruner = pruner;
        this.generationCallback = generationCallback;
        this.halter = halter;
        this.algorithmParams = algorithmParams;
    }

    public Evaluation work() {
        // TODO create initial population
        // TODO loop mutation > select best > crossover
        halter.setHalted(false);
        return loop(createInitialPopulation(), halter);
    }

    private Population createInitialPopulation() {
        return populationCreator.create(algorithmParams.initalPopulationSize);
    }

    private Evaluation loop(Population population, GenerationHalter halter) {
        Evaluation evaluation = null;
        Population generation = population;
        int index = 0;
        do {
            evaluation = evaluator.evaluate(Population.fromSubPopulation(pruner.prune(generation), mutator.mutate(crossover.crossover(pruner.getBest(generation)))));
            callback(evaluation, index);
            generation = evaluation.population();
            if (halter.isHalted(evaluation, index)) {
                System.out.println("Limit reached or halted, breaking out");
                evaluation.setFail();
                return evaluation;
            }
            index++;
        } while (evaluation.fitnessValue(0).get() < algorithmParams.acceptableFitnessValue);
        evaluation.setPass();
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
