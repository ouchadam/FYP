package com.ouchadam.fyp.presentation.view;

import com.ouchadam.fyp.analysis.ContainedMidiNote;
import com.ouchadam.fyp.analysis.ContainedNoteCreator;
import com.ouchadam.fyp.analysis.MidiTrack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class StepSequenceView extends JPanel {

    private static final int ROWS = 12;
    private static final int COLUMNS = 16 * 2;

    private GridLayout gridLayout;
    private Step[][] gridMembers;

    public void init() {
        gridLayout = new GridLayout(ROWS, COLUMNS, 2, 2);
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
        int index = 0;
        List<ContainedMidiNote> midiNotes = new ContainedNoteCreator().process(midiTrack.getNotes());
        for (ContainedMidiNote note : midiNotes) {
            gridMembers[note.getNote()][index].setSelected(true);
            int length = note.lengthInSixteenth(960);
            System.out.println("Note is : " + note.getNote() + " length tick is : " + note.getTickLength() + " 16 length is : " + length);
            index += length + 1;
            if (index >= COLUMNS) {
                break;
            }
        }
    }

    private static class Step extends JPanel implements MouseListener {

        private boolean selected = false;

        private Step() {
            setBackground(Color.DARK_GRAY);
            setPreferredSize(new Dimension(12, 12));
            addMouseListener(this);
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
            setBackground(selected ? Color.WHITE : Color.DARK_GRAY);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            setSelected(!selected);
        }

        @Override public void mousePressed(MouseEvent e) {
        }

        @Override public void mouseReleased(MouseEvent e) {
        }

        @Override public void mouseEntered(MouseEvent e) {
        }

        @Override public void mouseExited(MouseEvent e) {
        }
    }

}
