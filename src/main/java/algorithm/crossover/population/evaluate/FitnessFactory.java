package algorithm.crossover.population.evaluate;

import algorithm.crossover.population.evaluate.fitness.FitnessEvaluator;

public class FitnessFactory {

    public FitnessEvaluator member() {
        return new MemberEvaluator();
    }

}
