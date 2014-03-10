package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.analysis.midi.MidiNote;

import java.util.List;

public class EvenRhythmAnalysisRule implements AnalysisRule<MidiNote> {

    @Override
    public String apply(List<? extends MidiNote> notes) {
        return "Foo : " + max(notes);
    }

    int max(List<? extends MidiNote> notes) {
        int penalty = 0;
        for (MidiNote note : notes) {
            long tick = note.getTick();
            if (tick / ((960 * 4) / 16) % 2 == 0) {
                penalty++;
            }
        }
        return penalty;
    }
}
