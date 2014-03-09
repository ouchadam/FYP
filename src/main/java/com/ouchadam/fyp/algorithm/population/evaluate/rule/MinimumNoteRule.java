package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;

public class MinimumNoteRule implements FitnessRule<Member> {

    private final int minimumNoteCount;

    public MinimumNoteRule(int minimumNoteCount) {
        this.minimumNoteCount = minimumNoteCount;
    }

    @Override
    public FitnessValue apply(Member what) {
        int noteCount = what.only().noteStartValues().size();
        int percentOfRequired = Percentage.from(noteCount, minimumNoteCount);
        return new FitnessValue(percentOfRequired > 100 ? 100 : percentOfRequired);
    }

}
