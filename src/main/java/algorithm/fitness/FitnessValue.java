package algorithm.fitness;

public class FitnessValue {

    public static final int MAX_VALUE = 100;
    private static final int MIN = 0;

    private final int value;

    public static FitnessValue max() {
        return new FitnessValue(MAX_VALUE);
    }

    public static FitnessValue min() {
        return new FitnessValue(MIN);
    }

    public FitnessValue(int value) {
        this.value = value;
    }

    public boolean isMax() {
        return value == MAX_VALUE;
    }


    public boolean isMin() {
        return value == MIN;
    }

    public int value() {
        return value;
    }
}
