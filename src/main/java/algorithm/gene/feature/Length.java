package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;

public class Length extends FitnessFeature<Integer> {

    public Length(int value, FitnessEvaluator<Integer> evaluator) {
        super(value, evaluator);
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

}
