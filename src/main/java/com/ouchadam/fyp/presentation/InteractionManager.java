package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;

class InteractionManager {

    private final MidiSelection midiSelection;
    private final ButtonController buttonController;
    private final TextController textController;
    private final SequenceController sequenceController;

    private final AlgorithmController algorithmController;

    InteractionManager(MidiSelection midiSelection, ButtonController buttonController, TextController textController, SequenceController sequenceController, AlgorithmController algorithmController) {
        this.midiSelection = midiSelection;
        this.buttonController = buttonController;
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
                MidiTrack midiTrack = readMidi(midiFile);
                sequenceController.open(midiTrack);
                MidiAnalysizer midiAnalysizer = new MidiAnalysizer();
                String result = midiAnalysizer.analyse(midiTrack);
                showMessageDialog(component, result);
            } else {
                showMessageDialog(component, "Choose a .MIDI file first");
            }
        }
    };


    private void showMessageDialog(Component component, String message) {
        JOptionPane.showMessageDialog(component.getParent(), message);
    }

    private MidiTrack readMidi(File midiFile) {
        try {
            return new MidiReader(new MidiMessageMarshaller()).read(midiFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't handle file");
        }
    }

    public OnClickListener onStartStop() {
        return algorithmController.listener();
    }


}
