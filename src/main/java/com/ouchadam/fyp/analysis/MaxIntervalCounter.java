package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.analysis.midi.MidiNote;

import java.util.List;

public class MaxIntervalCounter implements AnalysisRule<MidiNote> {

    @Override
    public String apply(List<? extends MidiNote> notes) {
        return "Max Interval : " + max(notes);
    }

    int max(List<? extends MidiNote> notes) {
        int result = 0;
        for (int index = 1; index < notes.size(); index++) {
            int currentNote = notes.get(index).getKey();
            int previousNote = notes.get(index - 1).getKey();
            int interval = Math.abs(currentNote - previousNote);
            if (interval > result) {
                result = interval;
            }
        }
        return result;
    }

}
