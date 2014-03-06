package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.crossover.binary.Bit;

import java.util.List;
import java.util.Random;

class BinaryMutator {

    private final int mutationProbability;
    private final IndexManager indexManager;
    private final Random random;

    BinaryMutator(int mutationProbability, IndexManager indexManager, Random random) {
        this.mutationProbability = mutationProbability;
        this.indexManager = indexManager;
        this.random = random;
    }

    public Binary mutate(Binary binary) {
        int mutations = getMutationCount(binary.wordLength());
        if (mutations > 0) {
            List<Integer> randomIndexes = indexManager.create(mutations, binary.wordLength());
            BinaryBuilder binaryBuilder = new BinaryBuilder();
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

    private static class BinaryBuilder {

        private Bit[] binaryPieces;
        private int index;

        public BinaryBuilder start(int size) {
            this.index = 0;
            this.binaryPieces = new Bit[size];
            return this;
        }

        public BinaryBuilder addBit(Bit bit) {
            binaryPieces[index++] = bit;
            return this;
        }

        public Binary build() {
            String output = "";
            for (Bit binaryPiece : binaryPieces) {
                output += binaryPiece.value();
            }
            return new Binary(output);
        }

    }

}
