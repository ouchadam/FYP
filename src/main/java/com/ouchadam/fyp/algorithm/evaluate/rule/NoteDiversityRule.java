package com.ouchadam.fyp.algorithm.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteValue;
import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.algorithm.evaluate.fitness.FitnessValue;

import java.util.ArrayList;
import java.util.List;

public class NoteDiversityRule implements FitnessRule<Member> {

    private final int minimumNoteVariety;

    public NoteDiversityRule(int minimumNoteVariety) {
        this.minimumNoteVariety = minimumNoteVariety;
    }

    @Override
    public FitnessValue apply(Member what) {
        List<NoteValue> noteValues = what.only().noteStartValues();
        List<Integer> uniqueNotes = new ArrayList<Integer>(noteValues.size());
        for (NoteValue note : noteValues) {
            int noteValue = note.decimal() % 12;
            if (!uniqueNotes.contains(noteValue)) {
                uniqueNotes.add(noteValue);
            }
        }
        if (uniqueNotes.size() == 1) {
            return FitnessValue.min();
        }
        return new FitnessValue(percentageOfVariety(uniqueNotes.size()));
    }

    private int percentageOfVariety(int uniqueNotes) {
        return handleOverPercent(uniqueNotes);
    }

    private int handleOverPercent(int uniqueNotes) {
        int percent = Percentage.from(uniqueNotes, minimumNoteVariety);
        return percent > 100 ? 100 : percent;
    }

}
