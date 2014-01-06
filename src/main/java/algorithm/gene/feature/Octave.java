package algorithm.gene.feature;

import algorithm.fitness.EvaluatorType;

public class Octave extends BaseFeature<Integer> {

    public Octave(int value) {
        super(value, EvaluatorType.OCTAVE);
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

}
