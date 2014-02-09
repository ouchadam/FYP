package algorithm.crossover.binary;

import algorithm.Note;
import algorithm.crossover.Crossover;
import algorithm.crossover.population.BinaryCrossover;
import algorithm.crossover.population.NoteCrossover;
import algorithm.crossover.population.PopulationCrossover;

public class CrossoverFactory {

    private final BinaryPadder binaryPadder;

    public static CrossoverFactory newInstance() {
        return new CrossoverFactory(new BinaryPadder());
    }

    CrossoverFactory(BinaryPadder binaryPadder) {
        this.binaryPadder = binaryPadder;
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
            return new NoteCrossover(new BinarySinglePointCrossover(CrossoverPosition.from(CrossoverPosition.Position.MID)));
        }
    }

}
