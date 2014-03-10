package com.ouchadam.fyp.presentation;

import java.io.File;

public class MidiSelection {

    private File file;

    MidiSelection() {
        this.file = null;
    }

    public File getMidiFile() {
        return file;
    }

    public void setMidiFile(File file) {
        System.out.println("Selected : " + file.getName());
        this.file = file;
    }

    public boolean hasMidiFile() {
        return file != null;
    }

}
