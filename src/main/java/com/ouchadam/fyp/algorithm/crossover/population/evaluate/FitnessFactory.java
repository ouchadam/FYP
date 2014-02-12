package com.ouchadam.fyp.algorithm.crossover.population.evaluate;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessEvaluator;

public class FitnessFactory {

    public FitnessEvaluator<Member> member() {
        return new MemberEvaluator();
    }

}
