package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

class InteractionManager {

    private final MidiSelection midiSelection;
    private final ButtonController buttonController;
    private final TextController textController;

    InteractionManager(MidiSelection midiSelection, ButtonController buttonController, TextController textController) {
        this.midiSelection = midiSelection;
        this.buttonController = buttonController;
        this.textController = textController;
    }

    public OnClickListener openMidiListener() {
        return onOpenMidi;
    }

    private final OnClickListener onOpenMidi = new OnClickListener() {
        @Override
        public void onClick(Component component) {
            MidiFileChooser midiFileChooser = new MidiFileChooser(component.getParent());
            midiFileChooser.choose(midiChooserResult);
        }
    };

    private final MidiFileChooser.FileChooserResult midiChooserResult = new MidiFileChooser.FileChooserResult() {
        @Override
        public void onSelection(File file) {
            midiSelection.setMidiFile(file);
            buttonController.enableAnalise(true);
            textController.setMidiSelection(file.getName());
        }

        @Override
        public void onCancel() {
            System.out.println("Canceled selection");
        }
    };

    public OnClickListener analiseMidiListener() {
        return onAnaliseMidi;
    }

    private final OnClickListener onAnaliseMidi = new OnClickListener() {
        @Override
        public void onClick(Component component) {
            if (midiSelection.hasMidiFile()) {
                File midiFile = midiSelection.getMidiFile();
                int max = getMaxInterval(midiFile);
                showMessageDialog(component, "Max interval size : " + max);
            } else {
                showMessageDialog(component, "Choose a .MIDI file first");
            }
        }
    };

    private int getMaxInterval(File midiFile) {
        MidiTrack midiTrack = readMidi(midiFile, new MidiReader(new MidiMessageMarshaller()));
        List<ContainedMidiNote> process = new NoteProcessor().process(midiTrack.getNotes());
        return new IntervalCounter().max(process);
    }

    private void showMessageDialog(Component component, String message) {
        JOptionPane.showMessageDialog(component.getParent(), message);
    }

    private MidiTrack readMidi(File midiFile, MidiReader midiReader) {
        try {
            return midiReader.read(midiFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't handle file");
        }
    }

}
