package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.MidiTrack;

import java.io.File;

public interface SequenceController {
    void open(MidiTrack midiTrack);
    void analyse(File file);
}
