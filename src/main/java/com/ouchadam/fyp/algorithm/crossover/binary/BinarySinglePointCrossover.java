package com.ouchadam.fyp.algorithm.crossover.binary;

import com.ouchadam.fyp.algorithm.crossover.Crossover;

public class BinarySinglePointCrossover implements Crossover<Binary> {

    private static final int INITAL_INDEX = 0;
    private final CrossoverPosition position;

    public BinarySinglePointCrossover(CrossoverPosition position) {
        this.position = position;
    }

    @Override
    public Binary crossover(Binary parentX, Binary parentY) {
        int crossoverPosition = position.get(parentY.wordLength());
        String xSplit = parentX.getValue().substring(INITAL_INDEX, crossoverPosition);
        String ySplit = parentY.getValue().substring(crossoverPosition, parentY.getValue().length());
        return new Binary(xSplit + ySplit);
    }

}
