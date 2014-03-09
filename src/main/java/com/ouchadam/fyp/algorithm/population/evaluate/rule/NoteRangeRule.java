package com.ouchadam.fyp.algorithm.population.evaluate.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.NoteValue;
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
        int middleNote = getMidRange(what.all().noteValues());
        for (NoteValue noteValue : what.all().noteValues()) {
            int noteDelta = Math.abs(noteValue.decimal() - middleNote);
            if (noteDelta > range) {
                int rangeDelta = noteDelta - range;
                penalty += rangeDelta;
            }
        }
        int value = 100 - penalty;
        return new FitnessValue(value < 0 ? 0 : value);
    }

    private int getMidRange(List<NoteValue> noteValues) {
        int highest = NoteValue.NOTE_MIN;
        int lowest = NoteValue.NOTE_MAX;
        for (NoteValue noteValue : noteValues) {
            int currentNote = noteValue.decimal();
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
