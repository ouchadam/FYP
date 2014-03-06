package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

class MidiFileChooser {

    private static final String NO_EXTENSION = "";
    private static final String EXTENSION_MIDI = "midi";
    private static final String EXTENSION_MID = "mid";
    private static final String EXTENSION_DESCRIPTION = ".MIDI";
    private final Component viewParent;

    enum Type {
        SAVE, OPEN
    }

    private final Type type;

    public interface FileChooserResult {
        void onSelection(File file);

        void onCancel();
    }

    MidiFileChooser(Component viewParent, Type type) {
        this.viewParent = viewParent;
        this.type = type;
    }

    public void choose(FileChooserResult result) {
        JFileChooser fileChooser = createFileChooser();
        int response = showDialog(fileChooser);
        if (response == JFileChooser.APPROVE_OPTION) {
            result.onSelection(getSelectedFile(fileChooser));
        } else {
            result.onCancel();
        }
    }

    private File getSelectedFile(JFileChooser fileChooser) {
        switch (type) {
            case SAVE:
                return forceMidiSaveType(fileChooser.getSelectedFile());
            case OPEN:
                return fileChooser.getSelectedFile();
            default:
                throw new RuntimeException("Unhandled type : " + type);
        }
    }

    private File forceMidiSaveType(File file) {
        String file_name = file.getAbsolutePath();
        if (!file_name.toLowerCase().endsWith(".mid") && !file_name.toLowerCase().endsWith(".midi")) {
            file_name += ".MID";
            return new File(file_name);
        }
        return file;
    }

    private int showDialog(JFileChooser fileChooser) {
        switch (type) {
            case SAVE:
                return fileChooser.showSaveDialog(viewParent);
            case OPEN:
                return fileChooser.showOpenDialog(viewParent);
            default:
                throw new RuntimeException("Unhandled save type");
        }
    }

    private JFileChooser createFileChooser() {
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
