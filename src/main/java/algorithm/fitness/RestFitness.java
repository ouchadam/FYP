package algorithm.fitness;

import algorithm.gene.feature.Rest;

public class RestFitness extends FitnessWrapper<Rest.Value> {

    @Override
    protected FitnessEvaluator<Rest.Value> getEvaluator() {
        return fitnessEvaluator;
    }

    private final FitnessEvaluator<Rest.Value> fitnessEvaluator = new FitnessEvaluator<Rest.Value>() {
        @Override
        public FitnessValue evaluate(Rest.Value what) {
            return FitnessValue.min();
        }
    };

}
