package com.ouchadam.fyp.algorithm.population;

import com.ouchadam.fyp.algorithm.population.evaluate.OrderedPopulation;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;

public class Evaluation {

    public enum ResultType {
        PASS, FAIL;
    }

    private final OrderedPopulation orderedPopulation;
    private ResultType resultType;

    public Evaluation(OrderedPopulation orderedPopulation) {
        this.orderedPopulation = orderedPopulation;
    }

    public FitnessValue fitnessValue(int index) {
        return orderedPopulation.getFitness(index);
    }

    public Population population() {
        return orderedPopulation.asPopulation();
    }

    public void setFail() {
        setResultType(ResultType.FAIL);
    }

    public void setPass() {
        setResultType(ResultType.PASS);
    }

    private void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public ResultType getResultType() {
        if (resultType == null) {
            throw new IllegalAccessError("Result type was never set, use setFail or set");
        }
        return resultType;
    }
}
