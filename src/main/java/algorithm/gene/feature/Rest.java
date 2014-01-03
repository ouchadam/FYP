package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;

public class Rest extends FitnessFeature<Rest.Value> {

    public enum Value {
        REST {
            @Override
            String asValue() {
                return "1";
            }
        },
        HOLD {
            @Override
            String asValue() {
                return "0";
            }
        };

        abstract String asValue();

    }

    public Rest(Value value, FitnessEvaluator<Value> evaluator) {
        super(value, evaluator);
    }

    @Override
    public String toString() {
        return getValue().asValue();
    }

}
