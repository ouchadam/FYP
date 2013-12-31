package algorithm.gene.feature;

import algorithm.gene.Feature;

public class Rest implements Feature {

    private final Value value;

    public enum Value {
        REST {
            @Override
            String asValue() {
                return "1";
            }
        },
        HOLD {
            @Override
            String asValue() {
                return "0";
            }
        };

        abstract String asValue();
    }

    public Rest(Value value) {
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
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value.asValue();
    }

}
