package algorithm.fitness;

import algorithm.gene.feature.Rest;

public class RestFitness implements FitnessEvaluator<Rest.Value> {
    @Override
    public FitnessValue evaluate(Rest.Value what) {
        return FitnessValue.min();
    }
}
