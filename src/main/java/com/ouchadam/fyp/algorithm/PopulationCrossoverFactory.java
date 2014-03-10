package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.PopulationCrossover;
import com.ouchadam.fyp.algorithm.crossover.binary.CrossoverFactory;
import com.ouchadam.fyp.algorithm.population.PopulationCrosser;
import com.ouchadam.fyp.algorithm.population.RandomPopulationCrossover;

import java.util.Random;

class PopulationCrossoverFactory {

    private final Random random;
    private final CrossoverFactory crossoverFactory;
    private final IndexManager indexManager;
    private final Member.Controller memberController;

    public PopulationCrossoverFactory(Random random, CrossoverFactory crossoverFactory, IndexManager indexManager, Member.Controller memberController) {
        this.random = random;
        this.crossoverFactory = crossoverFactory;
        this.indexManager = indexManager;
        this.memberController = memberController;
    }


    public PopulationCrossover uniform(int crossoverPercentage, int maxPopulationSize) {
        return new PopulationCrosser(createUniform(crossoverPercentage), maxPopulationSize);
    }

    public PopulationCrossover createUniform(int crossoverPercentage) {
        return new RandomPopulationCrossover(random, crossoverFactory.uniform().noteValue(crossoverPercentage), crossoverFactory.uniform().noteType(crossoverPercentage), indexManager, memberController);
    }

    public PopulationCrossover singlePoint(int maxPopulationSize) {
        return new PopulationCrosser(createSinglePoint(), maxPopulationSize);
    }

    private PopulationCrossover createSinglePoint() {
        return new RandomPopulationCrossover(random, crossoverFactory.singlePoint().noteValue(), crossoverFactory.singlePoint().noteType(), indexManager, memberController);
    }

}
