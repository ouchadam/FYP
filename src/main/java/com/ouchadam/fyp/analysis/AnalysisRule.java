package com.ouchadam.fyp.analysis;

import com.ouchadam.fyp.analysis.midi.MidiNote;

import java.util.List;

public interface AnalysisRule<T extends MidiNote> {
    String apply(List<? extends T> notes);
}
