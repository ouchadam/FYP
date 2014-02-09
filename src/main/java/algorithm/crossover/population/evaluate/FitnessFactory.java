package algorithm.crossover.population.evaluate;

import algorithm.Member;
import algorithm.crossover.population.evaluate.fitness.FitnessEvaluator;

public class FitnessFactory {

    public FitnessEvaluator<Member> member() {
        return new MemberEvaluator();
    }

}
