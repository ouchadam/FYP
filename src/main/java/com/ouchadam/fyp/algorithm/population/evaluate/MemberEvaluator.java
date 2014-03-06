package com.ouchadam.fyp.algorithm.population.evaluate;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessEvaluator;
import com.ouchadam.fyp.algorithm.population.evaluate.rule.FitnessRule;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;

import java.util.List;

class MemberEvaluator implements FitnessEvaluator<Member> {

    @Override
    public FitnessValue evaluate(Member member, List<FitnessRule<Member>> rules) {
        FitnessAccumulator accumulator = FitnessAccumulator.from(rules.size());
        for (FitnessRule<Member> rule : rules) {
            accumulator.add(rule.apply(member));
        }
        return accumulator.average();
    }

}
