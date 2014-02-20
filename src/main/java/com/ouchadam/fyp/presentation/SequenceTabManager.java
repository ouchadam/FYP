package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.MidiTrack;
import com.ouchadam.fyp.presentation.view.StepSequenceView;

import javax.swing.*;

public class SequenceTabManager extends TabManager implements SequenceController {

    private StepSequenceView stepSequenceView;

    public SequenceTabManager(JTabbedPane tabbedPane) {
        super(tabbedPane);
    }

    @Override
    public JTabbedPane create() {
        stepSequenceView = new StepSequenceView();
        stepSequenceView.init();
        return createTabbedPane("Sequence", stepSequenceView);
    }

    @Override
    public void open(MidiTrack midiTrack) {
        stepSequenceView.open(midiTrack);
    }
}
