package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.CrossoverFactory;
import com.ouchadam.fyp.algorithm.population.*;
import com.ouchadam.fyp.algorithm.population.evaluate.Evaluator;
import com.ouchadam.fyp.algorithm.population.evaluate.FitnessFactory;
import com.ouchadam.fyp.algorithm.population.evaluate.PopulationEvaluator;

import java.util.Random;

public class GeneticAlgorithm {

    private final Mutator<Population> mutator;
    private final PopulationCrosser crossover;
    private final Evaluator<Population> evaluator;
    private final PopulationPruner pruner;
    private final GenerationCallback generationCallback;
    private final GenerationHalter halter;
    private final AlgorithmParams algorithmParams;
    private final Creator<Population> populationCreator;

    public static GeneticAlgorithm newInstance(GenerationCallback generationCallback, AlgorithmParams algorithmParams, GenerationHalter halter) {
        Random random = new Random();
        IndexManager indexManager = new IndexManager(new RandomIndexCreator(random));
        CrossoverFactory crossoverFactory = CrossoverFactory.newInstance(indexManager);
        return new GeneticAlgorithm(
                new PopulationCreator(new MemberCreator(), new PopulationCrosser(new RandomPopulationCrossover(random, crossoverFactory.singlePoint().note(), indexManager), algorithmParams.maxPopulationSize)),
                new PopulationMutator(indexManager, random),
                new PopulationCrosser(new RandomPopulationCrossover(random, crossoverFactory.uniform().note(), indexManager), algorithmParams.maxPopulationSize),
                new PopulationEvaluator(new FitnessFactory()),
                new PopulationPruner(algorithmParams.maxPopulationSize),
                generationCallback,
                halter,
                algorithmParams);
    }

    GeneticAlgorithm(Creator<Population> creator, Mutator<Population> mutator, PopulationCrosser crossover, Evaluator<Population> evaluator,
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
            evaluation = evaluate(Population.fromSubPopulation(getBestMember(generation), evolve(generation)));
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

    private Population evolve(Population generation) {
        return mutate(crossover(generation));
    }

    private Population crossover(Population generation) {
        return crossover.crossover(generation);
    }

    private Population mutate(Population generation) {
        return mutator.mutate(generation);
    }

    private Evaluation evaluate(Population generation) {
        return evaluator.evaluate(generation);
    }

    private Population getBestMember(Population generation) {
        return generation.getSubPopulation(0, 1);
    }

    private void callback(Evaluation evaluation, int generationIndex) {
        if (generationCallback != null) {
            generationCallback.onGeneration(evaluation, generationIndex);
        }
    }

}
