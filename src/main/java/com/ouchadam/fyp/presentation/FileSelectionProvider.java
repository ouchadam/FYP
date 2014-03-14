package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.presentation.midi.MidiFileChooser;

interface FileSelectionProvider {
    MidiFileChooser getFileChooser(MidiFileChooser.Type type);
}
