package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;

class InteractionManager {

    private final MidiSelection midiSelection;
    private final SequenceController sequenceController;
    private final AlgorithmController algorithmController;
    private final FileSelectionProvider selectionProvider;

    InteractionManager(MidiSelection midiSelection, SequenceController sequenceController, AlgorithmController algorithmController, FileSelectionProvider selectionProvider) {
        this.midiSelection = midiSelection;
        this.sequenceController = sequenceController;
        this.algorithmController = algorithmController;
        this.selectionProvider = selectionProvider;
    }

    public OnClickListener openMidiListener() {
        return onOpenMidi;
    }

    private final OnClickListener onOpenMidi = new OnClickListener() {
        @Override
        public void onClick(Component component) {
            selectionProvider.getFileChooser(MidiFileChooser.Type.OPEN).choose(midiChooserResult);
            if (midiSelection.hasMidiFile()) {
                analyseMidi();
            } else {
                showMessageDialog(component, "Choose a .MIDI file first");
            }
        }
    };

    private final MidiFileChooser.FileChooserResult midiChooserResult = new MidiFileChooser.FileChooserResult() {
        @Override
        public void onSelection(File file) {
            midiSelection.setMidiFile(file);
        }

        @Override
        public void onCancel() {
            System.out.println("Canceled selection");
        }
    };

    private void analyseMidi() {
        File midiFile = midiSelection.getMidiFile();
        MidiTrack midiTrack = readMidi(midiFile);
        sequenceController.open(midiTrack);
    }

    private MidiTrack readMidi(File midiFile) {
        try {
            return new MidiReader(new MidiMessageMarshaller(), new MidiSystemWrapper()).read(midiFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't handle file");
        }
    }

    private void showMessageDialog(Component component, String message) {
        JOptionPane.showMessageDialog(component.getParent(), message);
    }

    public OnClickListener onStartStop() {
        return algorithmController.startStopListener();
    }

    public OnClickListener onSave() {
        return algorithmController.onSave();
    }

}
