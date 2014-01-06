package algorithm.fitness;

import algorithm.gene.Feature;
import algorithm.gene.Gene;

abstract class FitnessWrapper<T> implements FitnessEvaluator<Gene<? extends Feature<?>, ?>> {

    @Override
    public FitnessValue evaluate(Gene<? extends Feature<?>, ?> what) {
        return getEvaluator().evaluate((T) what.getValue());
    }

    protected abstract FitnessEvaluator<T> getEvaluator();
}
