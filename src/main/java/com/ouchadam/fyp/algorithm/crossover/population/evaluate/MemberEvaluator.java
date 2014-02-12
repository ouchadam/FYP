package com.ouchadam.fyp.algorithm.crossover.population.evaluate;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessEvaluator;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessRule;
import com.ouchadam.fyp.algorithm.crossover.population.evaluate.fitness.FitnessValue;

class MemberEvaluator implements FitnessEvaluator<Member> {

    @Override
    public FitnessValue evaluate(Member member, FitnessRule<Member>... rules) {
        FitnessAccumulator accumulator = FitnessAccumulator.from(rules.length);
        for (FitnessRule<Member> rule : rules) {
            accumulator.add(rule.apply(member));
        }
        return accumulator.average();
    }

}
