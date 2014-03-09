package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

class MidiFileChooser {

    private final Component viewParent;
    private final JFileChooser fileChooser;

    enum Type {
        SAVE, OPEN
    }

    private final Type type;

    public interface FileChooserResult {
        void onSelection(File file);

        void onCancel();
    }

    MidiFileChooser(Component viewParent, JFileChooser fileChooser, Type type) {
        this.viewParent = viewParent;
        this.fileChooser = fileChooser;
        this.type = type;
    }

    public void choose(FileChooserResult result) {
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

}
