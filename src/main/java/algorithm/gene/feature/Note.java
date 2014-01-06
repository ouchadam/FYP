package algorithm.gene.feature;

import algorithm.fitness.EvaluatorType;

public class Note extends BaseFeature<Integer> {

    Note(int value) {
        super(value, EvaluatorType.NOTE);
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

}
