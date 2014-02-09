package algorithm.crossover.binary;

import algorithm.crossover.population.BinaryCrossover;
import algorithm.crossover.population.PopulationCrossover;

public class CrossoverFactory {

    private final BinaryPadder binaryPadder;

    public static CrossoverFactory newInstance() {
        return new CrossoverFactory(new BinaryPadder());
    }

    CrossoverFactory(BinaryPadder binaryPadder) {
        this.binaryPadder = binaryPadder;
    }

    public PopulationCrossover uniform() {
        return new PopulationCrossover(new BinaryCrossover(BinaryUniformCrossover.newInstance(), binaryPadder));
    }

    public PopulationCrossover singlePoint() {
        BinarySinglePointCrossover crosser = new BinarySinglePointCrossover(CrossoverPosition.from(CrossoverPosition.Position.MID));
        return new PopulationCrossover(new BinaryCrossover(crosser, new BinaryPadder()));
    }

}
