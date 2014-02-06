package algorithm.crossover.population.evaluate;

import java.util.ArrayList;
import java.util.List;

import algorithm.crossover.population.evaluate.fitness.FitnessValue;

public class FitnessAccumulator {

    private final List<FitnessValue> valueList;

    public static FitnessAccumulator from(int size) {
        return new FitnessAccumulator(new ArrayList<FitnessValue>(size));
    }

    FitnessAccumulator(List<FitnessValue> valueList) {
        this.valueList = valueList;
    }

    public void add(FitnessValue value) {
        valueList.add(value);
    }

    public FitnessValue average() {
        int total = 0;
        for (FitnessValue fitnessValue : valueList) {
            total += fitnessValue.get();
        }
        return new FitnessValue(total / valueList.size());
    }

}
