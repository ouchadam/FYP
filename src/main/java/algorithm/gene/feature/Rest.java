package algorithm.gene.feature;

import algorithm.gene.Feature;

public class Rest implements Feature {

    private final boolean value;

    public Rest(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rest rest = (Rest) o;
        if (value != rest.value) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
