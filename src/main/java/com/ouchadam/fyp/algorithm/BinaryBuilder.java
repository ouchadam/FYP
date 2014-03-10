package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;
import com.ouchadam.fyp.algorithm.crossover.binary.Bit;

class BinaryBuilder {

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
