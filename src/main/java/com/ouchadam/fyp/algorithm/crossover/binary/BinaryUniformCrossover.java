package com.ouchadam.fyp.algorithm.crossover.binary;

import java.util.List;
import java.util.Random;

import com.ouchadam.fyp.algorithm.IndexManager;
import com.ouchadam.fyp.algorithm.RandomIndexCreator;
import com.ouchadam.fyp.algorithm.crossover.Crossover;
import com.ouchadam.fyp.algorithm.crossover.BinaryCrossover;

public class BinaryUniformCrossover extends BinaryCrossover implements Crossover<Binary> {

    private final IndexManager randomIndexCreator;
    private Random random;

    public static BinaryUniformCrossover newInstance() {
        return new BinaryUniformCrossover(new BinaryPadder(), new IndexManager(new RandomIndexCreator()));
    }

    BinaryUniformCrossover(BinaryPadder binaryPadder, IndexManager randomIndexCreator) {
        super(binaryPadder);
        this.randomIndexCreator = randomIndexCreator;
    }

    @Override
    protected Binary matchedBinaryCrossover(Binary parentX, Binary parentY) {
        int length = parentX.wordLength();
        random = new Random();
        List<Integer> crossovers = randomIndexCreator.create(random.nextInt(length), length);

        String y = parentY.getValue();
        for (int index = 0; index < length; index++) {
            if (randomIndexCreator.isIndex(index, crossovers)) {
                y = replaceCharAt(y, index, parentX.getValue().charAt(index));
            }
        }
        return new Binary(y);
    }

    public String replaceCharAt(String s, int position, char c) {
        return s.substring(0, position) + c + s.substring(position + 1);
    }
}
