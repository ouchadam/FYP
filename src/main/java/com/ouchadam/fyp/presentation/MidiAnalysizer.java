package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.*;
import com.ouchadam.fyp.analysis.midi.ContainedMidiNote;

import java.util.List;

class MidiAnalysizer {

    public String[] analyse(MidiTrack midiTrack) {
        List<ContainedMidiNote> containedMidiNotes = new ContainedNoteCreator().process(midiTrack.getNotes());
        String[] results = new String[6];
        results[0] = getIntervalResult(containedMidiNotes);
        results[1] = getKeyLikelyhood(containedMidiNotes);
        results[2] = new EvenRhythmAnalysisRule().apply(containedMidiNotes);
        results[3] = new EvenRhythmAnalysisRule().apply(containedMidiNotes);
        results[4] = new EvenRhythmAnalysisRule().apply(containedMidiNotes);
        results[5] = new EvenRhythmAnalysisRule().apply(containedMidiNotes);
        return results;
    }

    private String getKeyLikelyhood(List<ContainedMidiNote> containedMidiNotes) {
        return new KeyAnalysis(new ScaleCreator()).apply(containedMidiNotes);
    }

    private String getIntervalResult(List<ContainedMidiNote> containedMidiNotes) {
        return new MaxIntervalCounter().apply(containedMidiNotes);
    }

}
