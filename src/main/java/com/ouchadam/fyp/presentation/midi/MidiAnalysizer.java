package com.ouchadam.fyp.presentation.midi;

import com.ouchadam.fyp.analysis.*;
import com.ouchadam.fyp.analysis.midi.ContainedMidiNote;
import com.ouchadam.fyp.analysis.ScaleCreator;

import java.util.List;

public class MidiAnalysizer {

    public String[] analyse(MidiTrack midiTrack) {
        List<ContainedMidiNote> containedMidiNotes = new ContainedNoteCreator().process(midiTrack.getNotes());
        String[] results = new String[3];
        results[0] = getIntervalResult(containedMidiNotes);
        results[1] = getKeyLikelyhood(containedMidiNotes);
        results[2] = new EvenRhythmAnalysisRule().apply(containedMidiNotes);
        return results;
    }

    private String getKeyLikelyhood(List<ContainedMidiNote> containedMidiNotes) {
        return new KeyAnalysis(new ScaleCreator()).apply(containedMidiNotes);
    }

    private String getIntervalResult(List<ContainedMidiNote> containedMidiNotes) {
        return new MaxIntervalCounter().apply(containedMidiNotes);
    }

}
