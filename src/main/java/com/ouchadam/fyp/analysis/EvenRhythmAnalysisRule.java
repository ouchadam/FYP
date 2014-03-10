package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.algorithm.Percentage;
import com.ouchadam.fyp.analysis.midi.ContainedMidiNote;
import com.ouchadam.fyp.analysis.midi.MidiNote;

import java.util.List;

public class EvenRhythmAnalysisRule implements AnalysisRule<ContainedMidiNote> {

    @Override
    public String apply(List<? extends ContainedMidiNote> notes) {
        return "Even note matches : " + (100 - percentOfOddNotes(notes)) + "%";
    }

    int percentOfOddNotes(List<? extends ContainedMidiNote> notes) {
        int penalty = 0;
        for (MidiNote note : notes) {
            long tick = note.getTick();
            long ticksToPosition = tick / ((960 * 4) / 16);
            if (ticksToPosition % 2 != 0) {
                penalty++;
            }
        }
        return Percentage.from(penalty, notes.size());
    }
}
