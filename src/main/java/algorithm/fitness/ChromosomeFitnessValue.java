package algorithm.fitness;

import algorithm.gene.GeneFitnessValue;

import java.util.List;

public class ChromosomeFitnessValue {

    private List<GeneFitnessValue> geneFitnessValues;

    public ChromosomeFitnessValue(List<GeneFitnessValue> geneFitnessValues) {
        this.geneFitnessValues = geneFitnessValues;
    }

    public FitnessValue get() {
        int sum = 0;
        int outOf = FitnessValue.MAX_VALUE * geneFitnessValues.size();
        for (GeneFitnessValue geneFitnessValue : geneFitnessValues) {
            sum += geneFitnessValue.get().value();
        }
        return new FitnessValue(outOf / sum);
    }
}
