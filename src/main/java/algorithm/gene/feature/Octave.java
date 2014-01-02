package algorithm.gene.feature;

import algorithm.gene.Feature;

public class Octave implements Feature<Integer> {

    private final int value;

    public Octave(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Octave note = (Octave) o;
        if (value != note.value) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
