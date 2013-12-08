package gene;

public class Gene<T extends Feature> implements Mutatable<T> {

    private final T feature;
    private final Mutator<T> mutator;

    public Gene(T feature, Mutator<T> mutator) {
        this.feature = feature;
        this.mutator = mutator;
    }

    @Override
    public T mutate() {
        return mutator.mutate(feature);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gene gene = (Gene) o;

        if (feature != null ? !feature.equals(gene.feature) : gene.feature != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return feature != null ? feature.hashCode() : 0;
    }
}
