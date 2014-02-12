package com.ouchadam.fyp.analysis;

import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

class BarCounter {

    private static final int QUARTER_NOTES_PER_BAR = 4;

    public int getBarCount(Sequence sequence, Track track) {
        return Math.round(getQuarterNoteCount(sequence, track) / QUARTER_NOTES_PER_BAR);
    }

    private float getQuarterNoteCount(Sequence sequence, Track track) {
        return (float) track.ticks() / sequence.getResolution();
    }

}
