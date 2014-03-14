package com.ouchadam.fyp.presentation.midi;

import com.ouchadam.fyp.Log;

import java.io.File;

public class MidiSelection {

    private File file;

    public MidiSelection() {
        this.file = null;
    }

    public File getMidiFile() {
        return file;
    }

    public void setMidiFile(File file) {
        Log.i("Selected : " + file.getName());
        this.file = file;
    }

    public boolean hasMidiFile() {
        return file != null;
    }

}
