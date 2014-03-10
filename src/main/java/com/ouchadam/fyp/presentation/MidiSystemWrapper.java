package com.ouchadam.fyp.presentation;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.File;
import java.io.IOException;

class MidiSystemWrapper {

    public void write(Sequence sequence, File file) {
        try {
            MidiSystem.write(sequence, MidiSystem.getMidiFileTypes(sequence)[0], file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save");
        }
    }


}
