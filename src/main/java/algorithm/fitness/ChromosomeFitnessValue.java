package algorithm.fitness;

import algorithm.gene.GeneFitnessValue;

import java.util.List;

public class ChromosomeFitnessValue {

    private List<GeneFitnessValue> geneFitnessValues;

    public ChromosomeFitnessValue(List<GeneFitnessValue> geneFitnessValues) {
        this.geneFitnessValues = geneFitnessValues;
    }
}
