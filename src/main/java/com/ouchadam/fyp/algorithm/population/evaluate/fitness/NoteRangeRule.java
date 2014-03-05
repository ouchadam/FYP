package com.ouchadam.fyp.algorithm.population.evaluate.fitness;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;

public class NoteRangeRule implements FitnessRule<Member> {

    private final int range;
    private final int middleNote;

    public NoteRangeRule(int range, int middleNote) {
        this.range = range;
        this.middleNote = middleNote;
    }

    public static NoteRangeRule newInstance(int range, int middleNote) {
        return new NoteRangeRule(range, middleNote);
    }

    @Override
    public FitnessValue apply(Member what) {
        int penalty = 0;
        for (Note note : what.all().notes()) {
            int noteDelta = Math.abs(note.decimal() - middleNote);
            if (noteDelta > range) {
                int rangeDelta = noteDelta - range;
                penalty += rangeDelta;
            }
        }
        int value = 100 - penalty;
        return new FitnessValue(value < 0 ? 0 : value);
    }

}
