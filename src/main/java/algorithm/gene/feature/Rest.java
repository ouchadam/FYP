package algorithm.gene.feature;

import algorithm.fitness.FitnessEvaluator;
import algorithm.fitness.FitnessValue;
import algorithm.gene.Feature;

public class Rest implements Feature<Rest.Value> {

    private final Value value;
    private final FitnessEvaluator<Value> evaluator;

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
        this.value = value;
        this.evaluator = evaluator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rest rest = (Rest) o;
        if (value != rest.value) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value.asValue();
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public FitnessValue getFitness() {
        return evaluator.evaluate(getValue());
    }

}
