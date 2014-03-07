package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;

import java.util.List;

public class IntervalRangeRule implements FitnessRule<Member> {

    public static IntervalRangeRule newInstance() {
        return new IntervalRangeRule();
    }

    @Override
    public FitnessValue apply(Member what) {
        List<Note> notes = what.all().notes();
        int matches = 0;
        if (getJump(notes.get(0), notes.get(1)) > 4) {
            matches++;
        }
        for (int index = 2; index < what.all().notes().size(); index++) {
            int gap = getJump(notes.get(index - 1), notes.get(index));
            if (gap >= 1 && gap <= 3) {
                matches++;
            }
        }
        return new FitnessValue(Percentage.from(matches, (notes.size() - 1)));
    }

    private int getJump(Note note1, Note note2) {
        return Math.abs(note1.decimal() - note2.decimal());
    }

}
