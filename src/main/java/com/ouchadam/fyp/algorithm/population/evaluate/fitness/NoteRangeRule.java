package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

import com.ouchadam.fyp.algorithm.ForEach;
import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;

import java.util.List;

public class NoteRangeRule implements FitnessRule<Member> {

    private final int range;
    private final int middleNote;

    private int penalty;

    public NoteRangeRule(int range, int middleNote) {
        this.range = range;
        this.middleNote = middleNote;
    }

    public static NoteRangeRule newInstance(int range, int middleNote) {
        return new NoteRangeRule(range, middleNote);
    }

    @Override
    public FitnessValue apply(Member what) {
        penalty = 0;
        what.forEach().note(checkRange);
        int value = 100 - penalty;
        return new FitnessValue(value < 0 ? 0 : value);
    }

    private final ForEach<Note> checkRange = new ForEach<Note>() {
        @Override
        public void on(Note what) {
            int noteDelta = Math.abs(what.decimal() - middleNote);
            if (noteDelta > range) {
                int rangeDelta = noteDelta - range;
                penalty += rangeDelta;
            }
        }
    };

}
