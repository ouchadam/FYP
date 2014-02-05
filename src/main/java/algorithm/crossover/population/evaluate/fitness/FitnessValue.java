package algorithm.crossover.population.evaluate.fitness;

public class FitnessValue {

    private final static int MAX_VALUE = 100;
    private final static int MIN_VALUE = 0;

    private final static FitnessValue max = new FitnessValue(MAX_VALUE);
    private final static FitnessValue min = new FitnessValue(MIN_VALUE);

    private int value;

    public FitnessValue(int value) {
        this.value = value;
    }

    public static FitnessValue max() {
        return max;
    }

    public static FitnessValue min() {
        return min;
    }

    public int get() {
        return value;
    }
}
