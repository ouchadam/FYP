package algorithm.fitness;

import algorithm.gene.feature.Length;


public class LengthFitness implements FitnessEvaluator<Integer> {
    @Override
    public FitnessValue evaluate(Integer what) {
        return FitnessValue.min();
    }
}
