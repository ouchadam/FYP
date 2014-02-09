package algorithm.crossover.population;

import algorithm.crossover.Crossover;
import algorithm.crossover.binary.Binary;
import algorithm.crossover.binary.BinaryPadder;

public abstract class BinaryCrossover implements Crossover<Binary> {

    private final BinaryPadder binaryPadder;

    public BinaryCrossover(BinaryPadder binaryPadder) {
        this.binaryPadder = binaryPadder;
    }

    @Override
    public Binary crossover(Binary parentX, Binary parentY) {
        BinaryPadder.MatchedLengthBinary matchedBinaries = binaryPadder.pad(parentX, parentY);
        return matchedBinaryCrossover(matchedBinaries.binaryX, matchedBinaries.binaryY);
    }

    protected abstract Binary matchedBinaryCrossover(Binary x, Binary y);
}
