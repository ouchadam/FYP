package algorithm.crossover.population.evaluate;

import algorithm.Member;
import algorithm.crossover.population.evaluate.fitness.FitnessEvaluator;
import algorithm.crossover.population.evaluate.fitness.FitnessRule;
import algorithm.crossover.population.evaluate.fitness.FitnessValue;

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
