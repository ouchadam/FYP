package algorithm.fitness;

public class NoteFitness implements FitnessEvaluator<Integer> {

    @Override
    public FitnessValue evaluate(Integer what) {
        if (what == 10) {
            return FitnessValue.max();
        }
        return FitnessValue.min();
    }

}
