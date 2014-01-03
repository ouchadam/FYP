package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;
import algorithm.fitness.FitnessValue;
import algorithm.gene.Feature;

abstract class FitnessFeature<V> implements Feature<V> {

    private final FitnessEvaluator<V> evaluator;
    private final V value;

    protected FitnessFeature(V value, FitnessEvaluator<V> evaluator) {
        this.value = value;
        this.evaluator = evaluator;
    }

    @Override
    public FitnessValue getFitness() {
        return evaluator.evaluate(getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FitnessFeature that = (FitnessFeature) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public V getValue() {
        return value;
    }

}
