package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteType;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;

import java.util.ArrayList;
import java.util.List;

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
