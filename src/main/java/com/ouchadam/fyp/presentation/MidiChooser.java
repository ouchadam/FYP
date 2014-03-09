package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

class MidiChooser {

    private static final String NO_EXTENSION = "";
    private static final String EXTENSION_MIDI = "midi";
    private static final String EXTENSION_MID = "mid";
    private static final String EXTENSION_DESCRIPTION = ".MIDI";

    public JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(midiFilter);
        return fileChooser;
    }

    private final FileFilter midiFilter = new FileFilter() {
        @Override
        public boolean accept(File file) {
            return file.isDirectory() || matches(EXTENSION_MIDI, file) || matches(EXTENSION_MID, file);
        }

        @Override
        public String getDescription() {
            return EXTENSION_DESCRIPTION;
        }
    };

    private boolean matches(String extension, File file) {
        return extension.equalsIgnoreCase(getExtension(file));
    }

    private String getExtension(File file) {
        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            return file.getName().substring(i + 1);
        }
        return NO_EXTENSION;
    }

}
