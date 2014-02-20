package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.GenerationCallback;
import com.ouchadam.fyp.algorithm.crossover.population.Evaluation;
import com.ouchadam.fyp.analysis.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

class InteractionManager {

    private final MidiSelection midiSelection;
    private final ButtonController buttonController;
    private final TextController textController;
    private final SequenceController sequenceController;

    InteractionManager(MidiSelection midiSelection, ButtonController buttonController, TextController textController, SequenceController sequenceController) {
        this.midiSelection = midiSelection;
        this.buttonController = buttonController;
        this.textController = textController;
        this.sequenceController = sequenceController;
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
                sequenceController.open(readMidi(midiFile));
                showMessageDialog(component, "Max interval size : " + max);
            } else {
                showMessageDialog(component, "Choose a .MIDI file first");
            }
        }
    };

    private int getMaxInterval(File midiFile) {
        MidiTrack midiTrack = readMidi(midiFile);
        List<ContainedMidiNote> process = new ContainedNoteCreator().process(midiTrack.getNotes());
        return new IntervalCounter().max(process);
    }

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

    public OnClickListener onStart() {
        return onStart;
    }

    private final OnClickListener onStart = new OnClickListener() {
        @Override
        public void onClick(Component component) {
            GenerationController generationController = new GenerationController();
            generationController.setGenerationCallback(onGeneration);
            generationController.start();
        }
    };

    private final GenerationCallback onGeneration = new GenerationCallback() {
        @Override
        public void onGeneration(Evaluation evaluation) {
            textController.appendGenerationText("Fitness : " + evaluation.fitnessValue(0).get());
        }
    };

}
