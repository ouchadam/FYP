package algorithm.gene.feature;

import algorithm.fitness.EvaluatorType;
import algorithm.gene.Feature;

abstract class BaseFeature<V> implements Feature<V> {

    private final V value;
    private final EvaluatorType evaluator;

    protected BaseFeature(V value, EvaluatorType evaluator) {
        this.value = value;
        this.evaluator = evaluator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseFeature that = (BaseFeature) o;
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

    @Override
    public EvaluatorType getEvaluatorType() {
        return evaluator;
    }
}
