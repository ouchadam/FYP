package com.ouchadam.fyp.algorithm.crossover.binary;

import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.domain.NoteType;
import com.ouchadam.fyp.algorithm.domain.NoteValue;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.population.NoteTypeCrossover;
import com.ouchadam.fyp.algorithm.population.NoteValueCrossover;

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

        public Crossover<NoteValue> noteValue(int crossoverPercent) {
            return new NoteValueCrossover(BinaryUniformCrossover.newInstance(indexManager, crossoverPercent, random));
        }


        public Crossover<NoteType> noteType(int crossoverPercent) {
            return new NoteTypeCrossover(BinaryUniformCrossover.newInstance(indexManager, crossoverPercent, random));
        }
    }

    public class SinglePoint {
        private SinglePoint() {}

        public Crossover<NoteValue> noteValue() {
            return new NoteValueCrossover(BinarySinglePointCrossover.newInstance(CrossoverPosition.Position.MID));
        }

        public Crossover<NoteType> noteType() {
            return new NoteTypeCrossover(BinarySinglePointCrossover.newInstance(CrossoverPosition.Position.MID));
        }
    }

}
