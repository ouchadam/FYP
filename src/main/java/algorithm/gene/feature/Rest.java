package algorithm.gene.feature;

import algorithm.fitness.EvaluatorType;

public class Rest extends BaseFeature<Rest.Value> {

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

    public Rest(Value value) {
        super(value, EvaluatorType.REST);
    }

    @Override
    public String toString() {
        return getValue().asValue();
    }

}
