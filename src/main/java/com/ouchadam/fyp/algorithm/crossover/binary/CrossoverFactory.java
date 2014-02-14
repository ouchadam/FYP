package com.ouchadam.fyp.algorithm.crossover.binary;

import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.crossover.population.NoteCrossover;

public class CrossoverFactory {

    public static CrossoverFactory newInstance() {
        return new CrossoverFactory();
    }

    public Uniform uniform() {
        return new Uniform();
    }

    public SinglePoint singlePoint() {
        return new SinglePoint();
    }

    public static class Uniform {
        private Uniform() {}

        public Crossover<Note> note() {
            return new NoteCrossover(BinaryUniformCrossover.newInstance());
        }
    }

    public static class SinglePoint {
        private SinglePoint() {}

        public Crossover<Note> note() {
            return new NoteCrossover(BinarySinglePointCrossover.newInstance(CrossoverPosition.Position.MID));
        }
    }

}
