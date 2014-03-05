package com.ouchadam.fyp.algorithm.crossover.binary;

import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.population.NoteCrossover;

import java.util.Random;

public class CrossoverFactory {

    private final IndexManager indexManager;
    private final Random random;

    public CrossoverFactory(IndexManager indexManager, Random random) {
        this.indexManager = indexManager;
        this.random = random;
    }

    public Uniform uniform() {
        return new Uniform();
    }

    public SinglePoint singlePoint() {
        return new SinglePoint();
    }

    public class Uniform {
        private Uniform() {}

        public Crossover<Note> note(int crossoverPercent) {
            return new NoteCrossover(BinaryUniformCrossover.newInstance(indexManager, crossoverPercent, random));
        }
    }

    public class SinglePoint {
        private SinglePoint() {}

        public Crossover<Note> note() {
            return new NoteCrossover(BinarySinglePointCrossover.newInstance(CrossoverPosition.Position.MID));
        }
    }

}
