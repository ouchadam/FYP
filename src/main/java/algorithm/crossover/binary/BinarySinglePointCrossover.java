package algorithm.crossover.binary;

import algorithm.crossover.Crossover;

public class BinarySinglePointCrossover implements Crossover<Binary> {

    private final int position;

    public BinarySinglePointCrossover(int position) {
        this.position = position;
    }

    @Override
    public Binary crossover(Binary parentX, Binary parentY) {
        String xSplit = parentX.getValue().substring(0, position);
        String ySplit = parentY.getValue().substring(position, parentY.getValue().length());
        return new Binary(xSplit + ySplit);
    }
}
