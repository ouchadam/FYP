package com.ouchadam.fyp.presentation.view;

import com.ouchadam.fyp.Log;
import com.ouchadam.fyp.analysis.ContainedNoteCreator;
import com.ouchadam.fyp.analysis.MidiTrack;
import com.ouchadam.fyp.analysis.SequencedNoteCreator;
import com.ouchadam.fyp.analysis.midi.ContainedMidiNote;
import com.ouchadam.fyp.analysis.midi.Sequenced16thMidiNote;
import com.ouchadam.fyp.presentation.tab.NoteRange;
import com.ouchadam.fyp.presentation.tab.RangeCreator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StepSequenceView extends JPanel {

    private static final int COLUMNS = 16;
    private final RangeCreator rangeCreator;

    private Step[][] gridMembers;
    private List<Sequenced16thMidiNote> notes;
    private Playhead[] playheads;

    public StepSequenceView(RangeCreator rangeCreator) {
        this.rangeCreator = rangeCreator;
    }

    public void open(MidiTrack midiTrack) {
        notes = toSequencedNote(midiTrack);
        NoteRange noteRange = new NoteRange(rangeCreator.from(notes));
        init(noteRange.size() + 1);
        for (Sequenced16thMidiNote note : notes) {
            if (note.position() >= COLUMNS) {
                break;
            } else {
                addToGrid(noteRange, note);
            }
        }
    }

    public void init(int gridSize) {
        clearGrid();
        GridLayout gridLayout = new GridLayout(gridSize + 1, COLUMNS, 2, 2);
        gridMembers = new Step[gridSize][COLUMNS];
        setLayout(gridLayout);

        playheads = new Playhead[COLUMNS];

        for (int column = 0; column < COLUMNS; column++) {
            Playhead step = new Playhead();
            playheads[column] = step;
            addToLayout(step);
        }

        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                Step step = new Step();
                gridMembers[row][column] = step;
                addToLayout(step);
            }
        }
        updateUI();
    }

    private Component addToLayout(Component component) {
        return add(component);
    }

    private void clearGrid() {
        removeAll();
        gridMembers = null;
    }

    private List<Sequenced16thMidiNote> toSequencedNote(MidiTrack midiTrack) {
        List<ContainedMidiNote> containedMidiNotes = new ContainedNoteCreator().process(midiTrack.getNotes());
        return new SequencedNoteCreator(midiTrack.getMeta().getResolution()).process(containedMidiNotes);
    }

    private void addToGrid(NoteRange noteRange, Sequenced16thMidiNote midiNote) {
        Log.d("Note is : " + midiNote.getNote() + " " + midiNote.getKey() + " position in 16ths : " + midiNote.position() + " length is 16ths: " + midiNote.length());
        for (int index = 0; index < midiNote.length(); index++) {
            if (index + midiNote.position() >= COLUMNS) {
                break;
            }
            gridMembers[noteRange.applyInvertedRange(midiNote.getKey())][midiNote.position() + index].setSelected(true);
        }
    }

    public List<Sequenced16thMidiNote> getNotes() {
        return notes;
    }

    public void setPlayhead(final int position) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (position == 0) {
                    playheads[COLUMNS - 1].setNotPlaying();
                } else {
                    playheads[position - 1].setNotPlaying();
                }
                playheads[position].setPlaying();
                updateUI();
            }
        });

    }

    private static class Step extends JPanel {

        private static final int WIDTH = 4;
        private static final int HEIGHT = 4;

        private Step() {
            setBackground(Color.DARK_GRAY);
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        public void setSelected(boolean selected) {
            setBackground(selected ? Color.WHITE : Color.DARK_GRAY);
        }

    }

    private static class Playhead extends JPanel {

        private static final int WIDTH = 4;
        private static final int HEIGHT = 4;

        private Playhead() {
            setBackground(Color.GRAY);
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        public void setPlaying() {
            setBackground(Color.WHITE);
        }

        public void setNotPlaying() {
            setBackground(Color.GRAY);
        }

    }


}
