package algorithm.crossover.population;

import algorithm.crossover.Crossover;
import algorithm.crossover.binary.Binary;
import algorithm.crossover.binary.BinaryPadder;

public class BinaryCrossover implements Crossover<Binary> {

    private final Crossover<Binary> crosser;
    private final BinaryPadder binaryPadder;

    public BinaryCrossover(Crossover<Binary> crosser, BinaryPadder binaryPadder) {
        this.crosser = crosser;
        this.binaryPadder = binaryPadder;
    }

    @Override
    public Binary crossover(Binary parentX, Binary parentY) {
        BinaryPadder.MatchedLengthBinary matchedBinaries = binaryPadder.pad(parentX, parentY);
        return crosser.crossover(matchedBinaries.binaryX, matchedBinaries.binaryY);
    }
}
