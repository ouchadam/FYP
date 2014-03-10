package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.Log;
import com.ouchadam.fyp.algorithm.evaluate.Evaluator;
import com.ouchadam.fyp.algorithm.population.Creator;
import com.ouchadam.fyp.algorithm.population.Evaluation;
import com.ouchadam.fyp.algorithm.population.Population;
import com.ouchadam.fyp.algorithm.population.PopulationCrosser;

public class GeneticAlgorithm {

    private static final int DUPLICATE_PRUNING_RATE = 50;

    private final Mutator<Population> mutator;
    private final PopulationCrosser crossover;
    private final Evaluator<Population> evaluator;
    private final PopulationSelector populationSelector;
    private final GenerationCallback generationCallback;
    private final GenerationHalter halter;
    private final AlgorithmParams algorithmParams;
    private final Creator<Population> populationCreator;

    private int lastDuplicatePrunedIndex = 0;

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
        return populationCreator.create(algorithmParams.getInitalPopulationSize());
    }

    private Evaluation loop(Population population, GenerationHalter halter) {
        Evaluation evaluation = evaluate(population);
        Population generation = evaluation.population();
        int index = 0;
        do {
            Population guaranteedMembers = populationSelector.getBest(generation);
            Population seeds = populationSelector.selectSeeds(removeDuplicates(index, generation));
            evaluation = work(seeds, guaranteedMembers);

            callback(evaluation, index);
            generation = pruneToMaxSize(removeDuplicates(index, evaluation.population()));
            if (halter.isHalted(evaluation, index)) {
                Log.i("Limit reached or halted, breaking out");
                evaluation.setFail();
                return evaluation;
            }
            index++;
        } while (!evaluation.meetsWantedFitness(algorithmParams.getAcceptableFitnessValue()));
        evaluation.setPass();
        return evaluation;
    }

    private Population removeDuplicates(int index, Population population) {
        if (index > lastDuplicatePrunedIndex + DUPLICATE_PRUNING_RATE) {
            lastDuplicatePrunedIndex = index;
            return population.removeDuplicates();
        }
        return population;
    }

    private Evaluation work(Population seeds, Population guaranteed) {
        return evaluate(Population.fromSubPopulation(guaranteed, evolve(seeds)));
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

    private Population pruneToMaxSize(Population population) {
        return population.size() > algorithmParams.getMaxPopulationSize() ? population.getSubPopulation(0, algorithmParams.getMaxPopulationSize()) : population;
    }

    private void callback(Evaluation evaluation, int generationIndex) {
        if (generationCallback != null) {
            generationCallback.onGeneration(evaluation, generationIndex);
        }
    }

}
