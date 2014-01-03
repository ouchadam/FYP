package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;

public class Note extends FitnessFeature<Integer> {

    Note(int value, FitnessEvaluator<Integer> evaluator) {
        super(value, evaluator);
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    public Note from(Note note, int value) {
        return new Note(value, note.getEvaluator());
    }

}
