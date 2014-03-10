package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.Log;
import com.ouchadam.fyp.analysis.*;

import javax.sound.midi.InvalidMidiDataException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class InteractionManager {

    private final MidiSelection midiSelection;
    private final SequenceController sequenceController;
    private final AlgorithmController algorithmController;
    private final FileSelectionProvider selectionProvider;
    private final DialogManager dialogManager;
    private final MidiReader midiReader;

    InteractionManager(MidiSelection midiSelection, SequenceController sequenceController, AlgorithmController algorithmController, FileSelectionProvider selectionProvider, DialogManager dialogManager, MidiReader midiReader) {
        this.midiSelection = midiSelection;
        this.sequenceController = sequenceController;
        this.algorithmController = algorithmController;
        this.selectionProvider = selectionProvider;
        this.dialogManager = dialogManager;
        this.midiReader = midiReader;
    }

    public OnClickListener openMidiListener() {
        return onOpenMidi;
    }

    private final OnClickListener onOpenMidi = new OnClickListener() {
        @Override
        public void onClick(Component component) {
            selectionProvider.getFileChooser(MidiFileChooser.Type.OPEN).choose(midiChooserResult);
            if (midiSelection.hasMidiFile()) {
                openMidi();
            } else {
                dialogManager.showMessageDialog(component, "Choose a .MIDI file first");
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

    private void openMidi() {
        try {
            MidiTrack midiTrack = readMidi(midiSelection);
            sequenceController.open(midiTrack);
        } catch (Exception e) {
            Log.e("Couldn't handle file", e);
            throw new RuntimeException("Couldn't handle file");
        }
    }

    private MidiTrack readMidi(MidiSelection midiSelection) throws IOException, InvalidMidiDataException {
        return midiReader.read(midiSelection);
    }

    public OnClickListener onStartStop() {
        return algorithmController.startStopListener();
    }

    public OnClickListener onSave() {
        return algorithmController.onSave();
    }

}
