package com.ouchadam.fyp.presentation;

interface FileSelectionProvider {
    MidiFileChooser getFileChooser(MidiFileChooser.Type type);
}
