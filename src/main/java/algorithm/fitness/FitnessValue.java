package algorithm.fitness;

public class FitnessValue {

    private static final int MAX = 100;
    private static final int MIN = 0;

    private final int value;

    public static FitnessValue max() {
        return new FitnessValue(MAX);
    }

    public static FitnessValue min() {
        return new FitnessValue(MIN);
    }

    public FitnessValue(int value) {
        this.value = value;
    }

    public boolean isMax() {
        return value == MAX;
    }


    public boolean isMin() {
        return value == MIN;
    }
}
