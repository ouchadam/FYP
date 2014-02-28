package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.*;

import java.util.List;

class MidiAnalysizer {


    public String analyse(MidiTrack midiTrack) {
        List<ContainedMidiNote> containedMidiNotes = new ContainedNoteCreator().process(midiTrack.getNotes());
        StringBuilder builder = new StringBuilder();

        builder.append("Max Interval : ").append(getMaxInterval(containedMidiNotes)).append("\n");
        KeyAnalysis.Result keyResult = new KeyAnalysis(new ScaleCreator()).analyse(containedMidiNotes);
        builder.append("Key Likelyhood : ").append(keyResult.key).append(" ").append(keyResult.type).append(" ").append(keyResult.percent).append("%").append("\n");
        return builder.toString();
    }

    private int getMaxInterval(List<ContainedMidiNote> midiNotes) {
        return new IntervalCounter().max(midiNotes);
    }

}
