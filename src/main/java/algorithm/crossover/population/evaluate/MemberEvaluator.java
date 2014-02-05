package algorithm.crossover.population.evaluate;

import algorithm.ForEach;
import algorithm.Member;
import algorithm.crossover.binary.Binary;
import algorithm.crossover.population.evaluate.fitness.FitnessRule;
import algorithm.crossover.population.evaluate.fitness.FitnessValue;

import java.util.ArrayList;
import java.util.List;

class MemberEvaluator {

    private final FitnessRule<Binary> fixedNoteRule;
    private final Member member;

    private final List<FitnessValue> valueList;

    MemberEvaluator(FitnessRule<Binary> fixedNoteRule, Member member) {
        this.fixedNoteRule = fixedNoteRule;
        this.member = member;
        this.valueList = new ArrayList<FitnessValue>(member.size());
    }

    public FitnessValue evaluate() {
        member.forEachNote(evaluate);
        return average(valueList);
    }

    private final ForEach<Binary> evaluate = new ForEach<Binary>() {
        @Override
        public void on(Binary what) {
            accumulate(fixedNoteRule.apply(what));
        }
    };

    private void accumulate(FitnessValue value) {
        valueList.add(value);
    }

    private FitnessValue average(List<FitnessValue> valueList) {
        int total = 0;
        for (FitnessValue fitnessValue : valueList) {
            total += fitnessValue.get();
        }
        return new FitnessValue(total / valueList.size());
    }

}
