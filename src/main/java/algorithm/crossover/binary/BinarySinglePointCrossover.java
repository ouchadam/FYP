package algorithm.crossover.binary;

import algorithm.crossover.Crossover;

public class BinarySinglePointCrossover implements Crossover<Binary> {

    private final CrossoverPosition position;

    public BinarySinglePointCrossover(CrossoverPosition position) {
        this.position = position;
    }

    @Override
    public Binary crossover(Binary parentX, Binary parentY) {
        int crossoverPosition = position.get(parentY.wordLength());
        String xSplit = parentX.getValue().substring(0, crossoverPosition);
        String ySplit = parentY.getValue().substring(crossoverPosition, parentY.getValue().length());
        return new Binary(xSplit + ySplit);
    }

}
