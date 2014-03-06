package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;

import java.util.ArrayList;
import java.util.List;

public class NoteDiversityRule implements FitnessRule<Member> {

    private final int minimumNoteVariety;

    public static NoteDiversityRule newInstance(int fixedValue) {
        return new NoteDiversityRule(fixedValue);
    }

    public NoteDiversityRule(int minimumNoteVariety) {
        this.minimumNoteVariety = minimumNoteVariety;
    }

    @Override
    public FitnessValue apply(Member what) {
        List<Integer> uniqueNotes = new ArrayList<Integer>(what.size());
        for (Note note : what.all().notes()) {
            int noteValue = note.decimal() % 12;
            if (!uniqueNotes.contains(noteValue)) {
                uniqueNotes.add(noteValue);
            }
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