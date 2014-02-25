package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;

public class Note {

    public final static int NOTE_MAX = 127;
    public final static int NOTE_MIN = 0;
    private final static int wordLength = 7;

    private final Binary value;

    public static Note newInstance(int value) {
        return new Note(Binary.newInstance(Integer.toBinaryString(value), wordLength));
    }

    public Note(Binary value) {
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
        Note note = (Note) o;
        if (value != null ? !value.equals(note.value) : note.value != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}