package algorithm.fitness;

public class LengthFitness extends FitnessWrapper<Integer> {

    @Override
    protected FitnessEvaluator<Integer> getEvaluator() {
        return fitnessEvaluator;
    }

    private final FitnessEvaluator<Integer> fitnessEvaluator = new FitnessEvaluator<Integer>() {
        @Override
        public FitnessValue evaluate(Integer what) {
            return FitnessValue.min();
        }
    };
}
