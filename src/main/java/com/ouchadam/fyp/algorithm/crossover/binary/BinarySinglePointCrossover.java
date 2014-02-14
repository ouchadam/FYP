package com.ouchadam.fyp.algorithm.crossover.binary;

import com.ouchadam.fyp.algorithm.crossover.population.BinaryCrossover;

public class BinarySinglePointCrossover extends BinaryCrossover {

    private static final int INITAL_INDEX = 0;
    private final CrossoverPosition position;

    public static BinarySinglePointCrossover newInstance(CrossoverPosition.Position position) {
        return new BinarySinglePointCrossover(new BinaryPadder(), CrossoverPosition.from(position));
    }

    public BinarySinglePointCrossover(BinaryPadder binaryPadder, CrossoverPosition position) {
        super(binaryPadder);
        this.position = position;
    }

    @Override
    protected Binary matchedBinaryCrossover(Binary x, Binary y) {
        int crossoverPosition = position.get(y.wordLength());
        String xSplit = x.getValue().substring(INITAL_INDEX, crossoverPosition);
        String ySplit = y.getValue().substring(crossoverPosition, y.getValue().length());
        return new Binary(xSplit + ySplit);
    }

}
