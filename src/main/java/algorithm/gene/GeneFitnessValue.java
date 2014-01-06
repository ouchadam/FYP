package algorithm.gene;

import algorithm.fitness.FitnessValue;

import java.util.List;

public class GeneFitnessValue {

    private List<FitnessValue> geneFitnessValues;

    public GeneFitnessValue(List<FitnessValue> geneFitnessValues) {
        this.geneFitnessValues = geneFitnessValues;
    }

    public FitnessValue get() {
        int sum = 0;
        int outOf = FitnessValue.MAX_VALUE * geneFitnessValues.size();
        for (FitnessValue geneFitnessValue : geneFitnessValues) {
            sum += geneFitnessValue.value();
        }
        return new FitnessValue(outOf / sum);
    }

}
