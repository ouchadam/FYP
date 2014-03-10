package com.ouchadam.fyp.algorithm.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;

import java.util.List;

public class IntervalRangeRule implements FitnessRule<Member> {

    private final int value;

    public IntervalRangeRule(int value) {
        this.value = value;
    }

    public static IntervalRangeRule newInstance(int value) {
        return new IntervalRangeRule(value);
    }

    @Override
    public FitnessValue apply(Member what) {
        List<NoteValue> noteValues = what.only().noteStartValues();
        if (noteValues.size() < 2) {
            return FitnessValue.min();
        }
        int matches = 0;
        int firstJump = getJump(noteValues.get(0), noteValues.get(1));
        if (firstJump > value && firstJump <= value * 2) {
            matches++;
        }
        for (int index = 2; index < noteValues.size(); index++) {
            int gap = getJump(noteValues.get(index - 1), noteValues.get(index));
            if (gap >= 1 && gap <= 3) {
                matches++;
            }
        }
        return new FitnessValue(Percentage.from(matches, (noteValues.size() - 1)));
    }

    private int getJump(NoteValue noteValue1, NoteValue noteValue2) {
        return Math.abs(noteValue1.decimal() - noteValue2.decimal());
    }

}
