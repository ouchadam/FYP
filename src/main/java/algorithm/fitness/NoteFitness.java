package algorithm.fitness;

public class NoteFitness extends FitnessWrapper<Integer> {

    @Override
    protected FitnessEvaluator<Integer> getEvaluator() {
        return fitnessEvaluator;
    }

    private final FitnessEvaluator<Integer> fitnessEvaluator = new FitnessEvaluator<Integer>() {
        @Override
        public FitnessValue evaluate(Integer what) {
            return getFitnessValue(what);
        }
    };

    private FitnessValue getFitnessValue(Integer what) {
        if (what == 10) {
            return FitnessValue.max();
        }
        return FitnessValue.min();
    }
}
