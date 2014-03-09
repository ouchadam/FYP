package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;

class InteractionManager {

    private final MidiSelection midiSelection;
    private final TextController textController;
    private final SequenceController sequenceController;

    private final AlgorithmController algorithmController;

    InteractionManager(MidiSelection midiSelection, TextController textController, SequenceController sequenceController, AlgorithmController algorithmController) {
        this.midiSelection = midiSelection;
        this.textController = textController;
        this.sequenceController = sequenceController;
        this.algorithmController = algorithmController;
    }

    public OnClickListener openMidiListener() {
        return onOpenMidi;
    }

    private final OnClickListener onOpenMidi = new OnClickListener() {
        @Override
        public void onClick(Component component) {
            MidiFileChooser midiFileChooser = new MidiFileChooser(component.getParent(), MidiFileChooser.Type.OPEN);
            midiFileChooser.choose(midiChooserResult);
            if (midiSelection.hasMidiFile()) {
                analyseMidi(component);
            } else {
                showMessageDialog(component, "Choose a .MIDI file first");
            }
        }
    };

    private final MidiFileChooser.FileChooserResult midiChooserResult = new MidiFileChooser.FileChooserResult() {
        @Override
        public void onSelection(File file) {
            midiSelection.setMidiFile(file);
            textController.setMidiSelection(file.getName());
        }

        @Override
        public void onCancel() {
            System.out.println("Canceled selection");
        }
    };

    private void analyseMidi(Component component) {
        File midiFile = midiSelection.getMidiFile();
        MidiTrack midiTrack = readMidi(midiFile);
        sequenceController.open(midiTrack);
        MidiAnalysizer midiAnalysizer = new MidiAnalysizer();
        String result = midiAnalysizer.analyse(midiTrack);
        showMessageDialog(component, result);
    }

    private MidiTrack readMidi(File midiFile) {
        try {
            return new MidiReader(new MidiMessageMarshaller()).read(midiFile);
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
