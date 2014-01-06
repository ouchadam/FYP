package algorithm.gene.feature;

import algorithm.fitness.EvaluatorType;

public class Length extends BaseFeature<Integer> {

    public Length(int value) {
        super(value, EvaluatorType.LENGTH);
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

}
