package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.MidiTrack;
import com.ouchadam.fyp.presentation.view.StepSequenceView;

import javax.swing.*;
import java.awt.*;

public class SequenceTabManager extends TabManager implements SequenceController {

    private StepSequenceView stepSequenceView;
    private MidiTrack midiTrack;
    private JButton play;

    public SequenceTabManager(JTabbedPane tabbedPane) {
        super(tabbedPane);
    }

    @Override
    public JTabbedPane create() {
        JPanel panel = new JPanel(new GridLayout(2, 1));

        stepSequenceView = new StepSequenceView();
        stepSequenceView.init();
        panel.add(stepSequenceView);
        play = new JButton("Play");
        play.setEnabled(false);
        panel.add(play);
        return createTabbedPane("Sequence", panel);
    }

    @Override
    public void open(MidiTrack midiTrack) {
        this.midiTrack = midiTrack;
        stepSequenceView.open(midiTrack);
        play.setEnabled(true);
    }

    void setPlayListener(OnClickListener listener) {
        setClickListener(play, listener);
    }
}
