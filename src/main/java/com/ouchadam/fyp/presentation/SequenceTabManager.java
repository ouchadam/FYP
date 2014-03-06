package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.MidiTrack;
import com.ouchadam.fyp.presentation.view.StepSequenceView;

import javax.swing.*;
import java.awt.*;

public class SequenceTabManager extends TabManager implements SequenceController {

    private static final String TAB_TITLE = "Analyse";
    private StepSequenceView stepSequenceView;
    private MidiTrack midiTrack;
    private JButton play;
    private JButton openButton;
    private JButton analyseButton;

    public SequenceTabManager(JTabbedPane tabbedPane) {
        super(tabbedPane);
    }

    @Override
    public JTabbedPane create() {
        JPanel parent = createParent();
        stepSequenceView = createAndInitStepSequence();
        JPanel openPanel = createAndInitOpenPanel();
        JPanel playPanel = createAndInitPlayPanel();
        parent.add(openPanel);
        parent.add(createSpace());
        parent.add(stepSequenceView);
        parent.add(createSpace());
        parent.add(playPanel);
        return createTabbedPane(TAB_TITLE, parent);
    }

    private JPanel createAndInitPlayPanel() {
        play = createLoadDependantButton("Play");
        JPanel playPanel = new JPanel(new GridLayout(1, 1));
        playPanel.add(play);
        setPlayListener();
        return playPanel;
    }

    private JPanel createAndInitOpenPanel() {
        JPanel openPanel = new JPanel(new GridLayout(2, 1));
        openPanel.add(openButton = createButton("Choose a MIDI file"));
        openPanel.add(analyseButton = createButton("Analyse..."));
        setAnaliseEnabled(false);
        openPanel.setPreferredSize(new Dimension(0, 40));
        return openPanel;
    }

    private JPanel createParent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        return panel;
    }

    private StepSequenceView createAndInitStepSequence() {
        StepSequenceView stepSequenceView = new StepSequenceView();
        stepSequenceView.init();
        return stepSequenceView;
    }

    private JPanel createSpace() {
        JPanel space = new JPanel();
        space.setPreferredSize(new Dimension(0, 12));
        return space;
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

    void setOpenMidiListener(OnClickListener listener) {
        setClickListener(openButton, listener);
    }

    void setAnaliseListener(OnClickListener listener) {
        setClickListener(analyseButton, listener);
    }

    public void setAnaliseEnabled(boolean enabled) {
        this.analyseButton.setEnabled(enabled);
    }

    void setAnaliseText(String text) {
        analyseButton.setText(text);
    }

    JButton getOpenMidiButton() {
        return openButton;
    }

    JButton getAnalyseButton() {
        return analyseButton;
    }

}
