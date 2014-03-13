package com.ouchadam.fyp.presentation.view;

import com.ouchadam.fyp.Log;
import com.ouchadam.fyp.analysis.ContainedNoteCreator;
import com.ouchadam.fyp.analysis.MidiTrack;
import com.ouchadam.fyp.analysis.SequencedNoteCreator;
import com.ouchadam.fyp.analysis.midi.ContainedMidiNote;
import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StepSequenceView extends JPanel {

    private static final int ROWS = 12;
    private static final int COLUMNS = 16;

    private Step[][] gridMembers;
    private List<Sequenced16thMidiNote> notes;

    public void init() {
        GridLayout gridLayout = new GridLayout(ROWS, COLUMNS, 2, 2);
        gridMembers = new Step[ROWS][COLUMNS];
        setLayout(gridLayout);

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                Step step = new Step();
                gridMembers[row][column] = step;
                addToLayout(step);
            }
        }
    }

    private Component addToLayout(Component component) {
        return add(component);
    }

    public void open(MidiTrack midiTrack) {
        clearGrid();
        notes = toSequencedNote(midiTrack);
        for (Sequenced16thMidiNote note : notes) {
            if (note.position() >= COLUMNS) {
                break;
            } else {
                addToGrid(note);
            }
        }
    }

    private void clearGrid() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                gridMembers[row][column].setSelected(false);
            }
        }
    }

    private List<Sequenced16thMidiNote> toSequencedNote(MidiTrack midiTrack) {
        List<ContainedMidiNote> containedMidiNotes = new ContainedNoteCreator().process(midiTrack.getNotes());
        return new SequencedNoteCreator(midiTrack.getMeta().getResolution()).process(containedMidiNotes);
    }

    private void addToGrid(Sequenced16thMidiNote midiNote) {
        Log.d("Note is : " + midiNote.getNote() + " position in 16ths : " + midiNote.position() + " length is 16ths: " + midiNote.length());
        for (int index = 0; index < midiNote.length(); index++) {
            gridMembers[midiNote.getNote().value()][midiNote.position() + index].setSelected(true);
        }
    }

    public List<Sequenced16thMidiNote> getNotes() {
        return notes;
    }

    private static class Step extends JPanel {

        private static final int WIDTH = 12;
        private static final int HEIGHT = 12;

        private boolean selected = false;

        private Step() {
            setBackground(Color.DARK_GRAY);
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
            setBackground(selected ? Color.WHITE : Color.DARK_GRAY);
        }

    }

}
