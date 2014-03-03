package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.analysis.midi.ContainedMidiNote;

import java.util.List;

public class IntervalCounter {

    public int max(List<ContainedMidiNote> notes) {
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

    public int min() {
        return 0;
    }

}
