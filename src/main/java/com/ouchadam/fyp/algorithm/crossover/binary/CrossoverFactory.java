package com.ouchadam.fyp.algorithm.crossover.binary;

import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.population.NoteCrossover;

public class CrossoverFactory {

    private final IndexManager indexManager;

    public static CrossoverFactory newInstance(IndexManager indexManager) {
        return new CrossoverFactory(indexManager);
    }

    public CrossoverFactory(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    public Uniform uniform() {
        return new Uniform();
    }

    public SinglePoint singlePoint() {
        return new SinglePoint();
    }

    public class Uniform {
        private Uniform() {}

        public Crossover<Note> note() {
            return new NoteCrossover(BinaryUniformCrossover.newInstance(indexManager));
        }
    }

    public class SinglePoint {
        private SinglePoint() {}

        public Crossover<Note> note() {
            return new NoteCrossover(BinarySinglePointCrossover.newInstance(CrossoverPosition.Position.MID));
        }
    }

}
