package com.ouchadam.fyp.algorithm.crossover.binary;

import java.util.List;
import java.util.Random;

import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.algorithm.RandomIndexCreator;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.crossover.BinaryCrossover;

public class BinaryUniformCrossover extends BinaryCrossover implements Crossover<Binary> {

    private final IndexManager randomIndexCreator;
    private final int crossoverPercent;
    private final Random random;

    public static BinaryUniformCrossover newInstance(IndexManager indexManager, int crossoverPercent, Random random) {
        return new BinaryUniformCrossover(new BinaryPadder(), indexManager, crossoverPercent, random);
    }

    BinaryUniformCrossover(BinaryPadder binaryPadder, IndexManager randomIndexCreator, int crossoverPercent, Random random) {
        super(binaryPadder);
        this.randomIndexCreator = randomIndexCreator;
        this.crossoverPercent = crossoverPercent;
        this.random = random;
    }

    @Override
    protected Binary matchedBinaryCrossover(Binary parentX, Binary parentY) {
        int length = parentX.wordLength();
        List<Integer> crossovers = randomIndexCreator.create(getCrossoverCount(length), length);

        String y = parentY.getValue();
        for (int index = 0; index < length; index++) {
            if (randomIndexCreator.isIndex(index, crossovers)) {
                y = replaceCharAt(y, index, parentX.getValue().charAt(index));
            }
        }
        return new Binary(y);
    }

    private int getCrossoverCount(int length) {
        if (crossoverPercent == 0) {
            return random.nextInt(length);
        }
        return Percentage.of(length, crossoverPercent);
    }

    public String replaceCharAt(String s, int position, char c) {
        return s.substring(0, position) + c + s.substring(position + 1);
    }
}
