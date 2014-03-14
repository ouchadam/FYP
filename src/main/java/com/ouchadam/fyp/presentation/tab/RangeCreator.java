package com.ouchadam.fyp.presentation.tab;

import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import java.util.List;

public class RangeCreator {

    public Range from(List<Sequenced16thMidiNote> notes) {
        Range range = new Range();
        for (Sequenced16thMidiNote note : notes) {
            rangeFor(range, note.getKey());
        }
        return range;
    }

    private void rangeFor(Range range, int currentNote) {
        if (isHigherThan(range, currentNote)) {
            range.setHighest(currentNote);
        }
        if (isLowerThan(range, currentNote)) {
            range.setLowest(currentNote);
        }
    }

    private boolean isLowerThan(Range range, int currentNote) {
        return currentNote < range.getLowest();
    }

    private boolean isHigherThan(Range range, int currentNote) {
        return currentNote > range.getHighest();
    }

}
