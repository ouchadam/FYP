package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;

public class Octave extends FitnessFeature<Integer> {

    public Octave(int value, FitnessEvaluator<Integer> evaluator) {
        super(value, evaluator);
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

}
