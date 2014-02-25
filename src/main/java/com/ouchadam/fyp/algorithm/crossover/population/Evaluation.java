package com.ouchadam.fyp.algorithm.crossover.population;

import com.ouchadam.fyp.algorithm.crossover.population.evaluate.OrderedPopulation;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.presentation.OnFinish;

public class Evaluation {

    public enum ResultType {
        PASS, FAIL;
    }

    private OrderedPopulation orderedPopulation;
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
