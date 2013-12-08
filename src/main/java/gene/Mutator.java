package gene;

public interface Mutator<T> {
    T mutate(T feature);
}
