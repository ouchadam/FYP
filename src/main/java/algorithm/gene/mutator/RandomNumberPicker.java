package algorithm.gene.mutator;

import java.util.Random;

public class RandomNumberPicker {

    private static final int NEGATIVE_MULTIPLIER = 2;
    private final Random random;

    public RandomNumberPicker(Random random) {
        this.random = random;
    }

    int getNumberFromRange(int range, int offset) {
        return random.nextInt(range * NEGATIVE_MULTIPLIER - offset) + offset - range;
    }
}
