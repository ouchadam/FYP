package com.ouchadam.fyp.algorithm.population.evaluate;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessEvaluator;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;
import com.ouchadam.fyp.presentation.RuleContainer;

import java.util.List;

class MemberEvaluator implements FitnessEvaluator<Member> {

    @Override
    public FitnessValue evaluate(Member member, List<RuleContainer<Member>> rules) {
        FitnessAccumulator accumulator = FitnessAccumulator.from(rules.size());
        for (RuleContainer<Member> rule : rules) {
            accumulator.add(rule.fitnessRule.apply(member));
        }
        return accumulator.average();
    }

}
