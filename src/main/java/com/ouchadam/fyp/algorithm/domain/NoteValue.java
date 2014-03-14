package com.ouchadam.fyp.algorithm.domain;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;

public class NoteValue {

    public final static int NOTE_MAX = 127;
    public final static int NOTE_MIN = 0;
    private final static int WORD_LENGTH = 7;

    private final Binary value;

    public static NoteValue newInstance(int value) {
        return new NoteValue(Binary.newInstance(Integer.toBinaryString(value), WORD_LENGTH));
    }

    public NoteValue(Binary value) {
        this.value = value;
    }

    public int decimal() {
        return value.toDecimal();
    }

    public Binary binary() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteValue noteValue = (NoteValue) o;
        if (value != null ? !value.equals(noteValue.value) : noteValue.value != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
