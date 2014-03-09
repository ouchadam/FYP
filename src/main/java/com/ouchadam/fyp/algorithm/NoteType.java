package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;

public enum NoteType {

    NOTE, HOLD, REST;

    public Binary binary() {
        return new Binary(Integer.toBinaryString(ordinal()));
    }

    public static NoteType from(Binary binary) {
        return values()[binary.toDecimal()];
    }
}
