package com.ouchadam.fyp.presentation.midi;

import com.ouchadam.fyp.Log;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MidiSystemWrapper {

    public void write(Sequence sequence, File file) {
        try {
            MidiSystem.write(sequence, MidiSystem.getMidiFileTypes(sequence)[0], file);
        } catch (IOException e) {
            Log.e("Failed to save", e);
        }
    }

    public Sequence getSequence(InputStream input) {
        try {
            return MidiSystem.getSequence(input);
        } catch (Exception e) {
            Log.e("Failed to get sequence from input stream", e);
            throw new RuntimeException("Failed to get sequence from input stream", e);
        }
    }

    public Sequencer getSequencer() {
        try {
            return MidiSystem.getSequencer();
        } catch (MidiUnavailableException e) {
            Log.e("Failed to get Midi Sequencer", e);
            throw new RuntimeException("Failed to get Midi Sequencer", e);
        }
    }
}
