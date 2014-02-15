package com.ouchadam.fyp.algorithm.crossover.population.evaluate;

import java.util.ArrayList;
import java.util.List;

import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessValue;

public class FitnessAccumulator {

    private final List<FitnessValue> valueList;

    public static FitnessAccumulator from(int size) {
        return new FitnessAccumulator(new ArrayList<FitnessValue>(size));
    }

    FitnessAccumulator(List<FitnessValue> valueList) {
        this.valueList = valueList;
    }

    public void add(FitnessValue value) {
        valueList.add(value);
    }

    public FitnessValue average() {
        return bias();
//        int total = 0;
//        for (FitnessValue fitnessValue : valueList) {
//            total += fitnessValue.fitnessValue();
//        }
//        return new FitnessValue(Math.round(total / valueList.size()));
    }

    public FitnessValue bias() {
        int total = 0;
        int penalty = 0;
        for (FitnessValue fitnessValue : valueList) {
            if (!fitnessValue.equals(FitnessValue.max())) {
                penalty += 3;
            }
            total += fitnessValue.get();
        }
        return new FitnessValue(Math.round((total - penalty) / valueList.size()));
    }

}
