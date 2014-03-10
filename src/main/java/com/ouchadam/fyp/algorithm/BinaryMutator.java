package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.crossover.binary.Bit;

import java.util.List;
import java.util.Random;

class BinaryMutator implements Mutator<Binary> {

    private final int mutationProbability;
    private final IndexManager indexManager;
    private final Random random;
    private final BinaryBuilder binaryBuilder;

    BinaryMutator(int mutationProbability, IndexManager indexManager, Random random, BinaryBuilder binaryBuilder) {
        this.mutationProbability = mutationProbability;
        this.indexManager = indexManager;
        this.random = random;
        this.binaryBuilder = binaryBuilder;
    }

    @Override
    public Binary mutate(Binary binary) {
        int mutations = getMutationCount(binary.wordLength());
        if (mutations > 0) {
            List<Integer> randomIndexes = indexManager.create(mutations, binary.wordLength());
            binaryBuilder.start(binary.wordLength());

            for (int index = 0; index < binary.wordLength(); index++) {
                Bit bit = binary.bitAt(index);
                if (indexManager.isIndex(index, randomIndexes)) {
                    bit = bit.invert();
                }
                binaryBuilder.addBit(bit);
            }
            return binaryBuilder.build();
        }
        return binary;
    }

    private int getMutationCount(int length) {
        if (mutationProbability == 0) {
            return random.nextInt(length);
        }
        return Percentage.of(length, mutationProbability);
    }

}
