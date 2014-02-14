package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.crossover.binary.Bit;

class BinaryMutator {

    private final int mutationProbability;
    private final RandomIndexCreator randomIndexCreator;

    BinaryMutator(int mutationProbability, RandomIndexCreator randomIndexCreator) {
        this.mutationProbability = mutationProbability;
        this.randomIndexCreator = randomIndexCreator;
    }

    public Binary mutate(Binary binary) {
        int mutations = 2; // TODO work out the amount from the word length and probability
        int[] randomIndexes = randomIndexCreator.create(mutations, binary.wordLength());
        BinaryBuilder binaryBuilder = new BinaryBuilder();
        binaryBuilder.start(binary.wordLength());

        for (int index = 0; index < binary.wordLength(); index++) {
            Bit bit = binary.bitAt(index);
            if (isMutationIndex(index, randomIndexes)) {
                bit = bit.invert();
            }
            binaryBuilder.addBit(bit);
        }
        return binaryBuilder.build();
    }

    private boolean isMutationIndex(int currentIndex, int[] randomIndexes) {
        for (int randomIndex : randomIndexes) {
            if (randomIndex == currentIndex) {
                return true;
            }
        }
        return false;
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
