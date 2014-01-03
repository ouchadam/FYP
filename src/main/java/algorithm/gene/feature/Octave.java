package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;
import algorithm.fitness.FitnessValue;
import algorithm.gene.Feature;

public class Octave implements Feature<Integer> {

    private final int value;
    private final FitnessEvaluator<Octave> evaluator;

    public Octave(int value, FitnessEvaluator<Octave> evaluator) {
        this.value = value;
        this.evaluator = evaluator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Octave note = (Octave) o;
        if (value != note.value) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public FitnessValue getFitness() {
        return evaluator.evaluate(this);
    }
}
