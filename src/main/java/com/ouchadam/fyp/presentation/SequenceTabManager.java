package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.MidiTrack;
import com.ouchadam.fyp.presentation.view.StepSequenceView;

import javax.swing.*;
import java.awt.*;

public class SequenceTabManager extends TabManager implements SequenceController {

    private StepSequenceView stepSequenceView;
    private MidiTrack midiTrack;
    private JButton play;
    private JButton save;

    public SequenceTabManager(JTabbedPane tabbedPane) {
        super(tabbedPane);
    }

    @Override
    public JTabbedPane create() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        stepSequenceView = new StepSequenceView();
        stepSequenceView.init();
        panel.add(stepSequenceView);
        play = createLoadDependantButton("Play");
        save = createLoadDependantButton("Save");
        panel.add(play);
        panel.add(save);
        setPlayListener();
        return createTabbedPane("Sequence", panel);
    }

    private JButton createLoadDependantButton(String title) {
        JButton button = createButton(title);
        button.setEnabled(false);
        return button;
    }

    @Override
    public void open(MidiTrack midiTrack) {
        this.midiTrack = midiTrack;
        stepSequenceView.open(midiTrack);
        play.setEnabled(true);
        save.setEnabled(true);
    }

    void setPlayListener() {
        setClickListener(play, onPlay);
    }

    private OnClickListener onPlay = new OnClickListener() {
        @Override
        public void onClick(Component component) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new MidiPlayer().play(midiTrack.getMeta(), stepSequenceView.getNotes());
                }
            }).start();
        }
    };

}
