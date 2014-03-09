package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;

import java.util.List;

public class IntervalRangeRule implements FitnessRule<Member> {

    public static IntervalRangeRule newInstance() {
        return new IntervalRangeRule();
    }

    @Override
    public FitnessValue apply(Member what) {
        List<NoteValue> noteValues = what.all().noteValues();
        int matches = 0;
        if (getJump(noteValues.get(0), noteValues.get(1)) > 4) {
            matches++;
        }
        for (int index = 2; index < what.all().noteValues().size(); index++) {
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
