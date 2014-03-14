package com.ouchadam.fyp.algorithm.evaluate.rule;

import com.ouchadam.fyp.algorithm.domain.Member;
import com.ouchadam.fyp.algorithm.domain.NoteType;
import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;

public class EvenRhythmRule implements FitnessRule<Member> {

    @Override
    public FitnessValue apply(Member what) {
        int penalty = 0;
        int noteCount = 0;
        int index = 0;
        for (NoteType type : what.all().noteTypes()) {
            if (type == NoteType.NOTE && index % 2 != 0) {
                penalty++;
            }
            if (type == NoteType.NOTE) {
                noteCount++;
            }
            index++;
        }
        return new FitnessValue(100 - Percentage.from(penalty, noteCount));
    }

}
