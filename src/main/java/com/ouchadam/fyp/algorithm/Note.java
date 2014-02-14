package com.ouchadam.fyp.algorithm;

import com.ouchadam.fyp.algorithm.crossover.binary.Binary;

public class Note {

    private final static int NOTE_MAX = 127;
    private final static int NOTE_MIN = 0;

    private final Binary value;

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
