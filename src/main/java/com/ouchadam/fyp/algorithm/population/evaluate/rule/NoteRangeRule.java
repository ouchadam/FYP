package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.Note;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessValue;

import java.util.List;

public class NoteRangeRule implements FitnessRule<Member> {

    private final int range;

    public NoteRangeRule(int range) {
        this.range = range;
    }

    public static NoteRangeRule newInstance(int range) {
        return new NoteRangeRule(range);
    }

    @Override
    public FitnessValue apply(Member what) {
        int penalty = 0;
        int middleNote = getMidRange(what.all().notes());
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

    private int getMidRange(List<Note> notes) {
        int highest = Note.NOTE_MIN;
        int lowest = Note.NOTE_MAX;
        for (Note note : notes) {
            int currentNote = note.decimal();
            if (currentNote > highest) {
                highest = currentNote;
            }
            if (currentNote < lowest) {
                lowest = currentNote;
            }
        }
        return calculateMidRange(highest, lowest);
    }

    private int calculateMidRange(int highest, int lowest) {
        return lowest + ((highest - lowest) / 2);
    }

}
