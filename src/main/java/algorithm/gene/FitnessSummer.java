package algorithm.gene;

import algorithm.fitness.FitnessValue;

import java.util.List;

class FitnessSummer {

    public FitnessValue sum(List<FitnessValue> fitnessValues) {
        int sum = 0;
        int outOf = FitnessValue.MAX_VALUE * fitnessValues.size();
        for (FitnessValue fitnessValue : fitnessValues) {
            sum += fitnessValue.value();
        }
        return new FitnessValue(outOf / sum);
    }

}
