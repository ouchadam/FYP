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

}
