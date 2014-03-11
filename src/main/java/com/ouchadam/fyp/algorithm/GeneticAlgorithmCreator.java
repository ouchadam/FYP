package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.crossover.binary.CrossoverFactory;
import com.ouchadam.fyp.algorithm.evaluate.FitnessFactory;
import com.ouchadam.fyp.algorithm.evaluate.PopulationEvaluator;
import com.ouchadam.fyp.algorithm.population.MemberCreator;
import com.ouchadam.fyp.algorithm.population.Population;
import com.ouchadam.fyp.algorithm.population.PopulationCreator;
import com.ouchadam.fyp.algorithm.population.PopulationCrosser;
import com.ouchadam.fyp.presentation.NoteOnFilter;

import java.util.Random;

public class GeneticAlgorithmCreator {

    private final Random random;
    private final IndexManager indexManager;
    private final Member.Controller memberController;
    private final PopulationCrossoverFactory populationCrossoverFactory;

    GeneticAlgorithmCreator(Random random, IndexManager indexManager, Member.Controller memberController, PopulationCrossoverFactory populationCrossoverFactory) {
        this.random = random;
        this.indexManager = indexManager;
        this.memberController = memberController;
        this.populationCrossoverFactory = populationCrossoverFactory;
    }

    public static GeneticAlgorithmCreator newInstance() {
        Random random = new Random();
        IndexManager indexManager = new IndexManager(new RandomIndexCreator(random));
        CrossoverFactory crossoverFactory = new CrossoverFactory(indexManager, random);
        Member.Controller memberController = new Member.Controller(new NoteOnFilter());
        PopulationCrossoverFactory populationCrossoverFactory = new PopulationCrossoverFactory(random, crossoverFactory, indexManager, memberController);
        return new GeneticAlgorithmCreator(random, indexManager, memberController, populationCrossoverFactory);
    }

    public GeneticAlgorithm create(GenerationCallback generationCallback, AlgorithmParams algorithmParams, GenerationHalter halter) {
        return new GeneticAlgorithm(
                createPopulationCreator(algorithmParams),
                createPopulationMutator(algorithmParams),
                createUniformPopulationCrosser(algorithmParams),
                createEvaluator(algorithmParams),
                createPopulationSelector(),
                generationCallback,
                halter,
                algorithmParams);
    }

    private PopulationCreator createPopulationCreator(AlgorithmParams algorithmParams) {
        return new PopulationCreator(getMemberCreator(), createSinglePointPopulationCrosser(algorithmParams));
    }

    private MemberCreator getMemberCreator() {
        return new MemberCreator(memberController, random);
    }

    private PopulationCrosser createSinglePointPopulationCrosser(AlgorithmParams algorithmParams) {
        int maxPopulationSize = algorithmParams.getMaxPopulationSize();
        return new PopulationCrosser(populationCrossoverFactory.singlePoint(maxPopulationSize), maxPopulationSize);
    }

    private Mutator<Population> createPopulationMutator(AlgorithmParams algorithmParams) {
        return getPopulationMutator(getBinaryMutator(algorithmParams));
    }

    private BinaryMutator getBinaryMutator(AlgorithmParams algorithmParams) {
        return new BinaryMutator(algorithmParams.getMutationPercent(), indexManager, random, new BinaryBuilder());
    }

    private Mutator<Population> getPopulationMutator(Mutator<Binary> binaryMutator) {
        return new PopulationMutator(indexManager, random, new MemberMutator(indexManager, random, binaryMutator, memberController));
    }

    private PopulationCrosser createUniformPopulationCrosser(AlgorithmParams algorithmParams) {
        int maxPopulationSize = algorithmParams.getMaxPopulationSize();
        int crossoverPercent = algorithmParams.getCrossoverPercent();
        return new PopulationCrosser(populationCrossoverFactory.uniform(crossoverPercent, maxPopulationSize), maxPopulationSize);
    }

    private PopulationEvaluator createEvaluator(AlgorithmParams algorithmParams) {
        return new PopulationEvaluator(new FitnessFactory(), algorithmParams.getRules());
    }

    private PopulationSelector createPopulationSelector() {
        return new PopulationSelector(random, new Tournament());
    }

}
