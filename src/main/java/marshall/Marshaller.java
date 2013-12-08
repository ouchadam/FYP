package marshall;

public interface Marshaller<F, T> {
    T marshall(F from);
}
