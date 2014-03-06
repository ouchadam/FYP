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
    private final PopulationSelector populationSelector;
    private final GenerationCallback generationCallback;
    private final GenerationHalter halter;
    private final AlgorithmParams algorithmParams;
    private final Creator<Population> populationCreator;

    public static GeneticAlgorithm newInstance(GenerationCallback generationCallback, AlgorithmParams algorithmParams, GenerationHalter halter) {
        Random random = new Random();
        IndexManager indexManager = new IndexManager(new RandomIndexCreator(random));
        Member.Controller memberController = new Member.Controller();
        CrossoverFactory crossoverFactory = new CrossoverFactory(indexManager, random);
        return new GeneticAlgorithm(
                new PopulationCreator(new MemberCreator(memberController, random), new PopulationCrosser(new RandomPopulationCrossover(random, crossoverFactory.singlePoint().note(), indexManager, memberController), algorithmParams.maxPopulationSize)),
                new PopulationMutator(indexManager, random, new MemberMutator(indexManager, random, algorithmParams.mutationPercent, memberController)),
                new PopulationCrosser(new RandomPopulationCrossover(random, crossoverFactory.uniform().note(algorithmParams.crossoverPercent), indexManager, memberController), algorithmParams.maxPopulationSize),
                new PopulationEvaluator(new FitnessFactory(), algorithmParams.rules),
                new PopulationSelector(PopulationSelector.Type.ELITISM, random),
                generationCallback,
                halter,
                algorithmParams);
    }

    GeneticAlgorithm(Creator<Population> creator, Mutator<Population> mutator, PopulationCrosser crossover, Evaluator<Population> evaluator,
                     PopulationSelector populationSelector, GenerationCallback generationCallback, GenerationHalter halter, AlgorithmParams algorithmParams) {
        this.populationCreator = creator;
        this.mutator = mutator;
        this.crossover = crossover;
        this.evaluator = evaluator;
        this.populationSelector = populationSelector;
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
        Evaluation evaluation = evaluate(population);
        Population generation = evaluation.population();
        int index = 0;
        do {
            Population seeds = populationSelector.selectSeeds(generation);
            Population guaranteedMembers = populationSelector.getBest(generation);
            evaluation = work(seeds, generation, guaranteedMembers);

            callback(evaluation, index);
            generation = pruneToMaxSize(evaluation.population());
            if (halter.isHalted(evaluation, index)) {
                System.out.println("Limit reached or halted, breaking out");
                evaluation.setFail();
                return evaluation;
            }
            index++;
        } while (!evaluation.meetsWantedFitness(algorithmParams.acceptableFitnessValue));
        evaluation.setPass();
        return evaluation;
    }

    private Evaluation work(Population seeds, Population population, Population guaranteed) {
        return evaluate(Population.fromSubPopulation(getBestMember(population), guaranteed, evolve(seeds)));
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

    private Population pruneToMaxSize(Population population) {
        return population.size() > algorithmParams.maxPopulationSize ? population.getSubPopulation(0, algorithmParams.maxPopulationSize) : population;
    }

    private void callback(Evaluation evaluation, int generationIndex) {
        if (generationCallback != null) {
            generationCallback.onGeneration(evaluation, generationIndex);
        }
    }

}
