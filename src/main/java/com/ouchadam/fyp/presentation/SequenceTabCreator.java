package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.MidiTrack;
import com.ouchadam.fyp.presentation.view.StepSequenceView;

import javax.swing.*;
import java.awt.*;

public class SequenceTabCreator extends TabCreator implements SequenceController {

    private static final String TAB_TITLE = "Analyse";
    private static final int ANALYSIS_LABEL_COUNT = 3;

    private final MidiPlayer midiPlayer;
    private final ClickManager clickManager;

    private StepSequenceView stepSequenceView;
    private MidiTrack midiTrack;
    private JButton play;
    private JButton openButton;

    private JLabel[] analysisLabels;


    public SequenceTabCreator(JTabbedPane tabbedPane, MidiPlayer midiPlayer, ClickManager clickManager) {
        super(tabbedPane);
        this.midiPlayer = midiPlayer;
        this.clickManager = clickManager;
    }

    @Override
    public JTabbedPane create() {
        JPanel parent = new JPanel();
        parent.add(createSequencePanel());

        JPanel analysis = new JPanel(new GridLayout(3, 1));
        analysis.setPreferredSize(new Dimension(300, 200));

        analysisLabels = new JLabel[ANALYSIS_LABEL_COUNT];

        for (int index = 0; index < ANALYSIS_LABEL_COUNT; index++) {
            analysisLabels[index] = new JLabel();
            analysis.add(analysisLabels[index]);
        }

        parent.add(analysis);

        return createTabbedPane(TAB_TITLE, parent);
    }

    private JPanel createSequencePanel() {
        JPanel parent = createParent();
        stepSequenceView = createAndInitStepSequence();
        JPanel openPanel = createAndInitOpenPanel();
        JPanel playPanel = createAndInitPlayPanel();
        parent.add(openPanel);
        parent.add(createSpace());
        parent.add(stepSequenceView);
        parent.add(createSpace());
        parent.add(playPanel);
        return parent;
    }

    private JPanel createAndInitPlayPanel() {
        play = createLoadDependantButton("Play");
        JPanel playPanel = new JPanel(new GridLayout(1, 1));
        playPanel.add(play);
        setPlayListener();
        return playPanel;
    }

    private JPanel createAndInitOpenPanel() {
        JPanel openPanel = new JPanel(new GridLayout(1, 1));
        openPanel.add(openButton = createButton("Choose a MIDI file"));
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
        analyse(midiTrack);
    }

    private void analyse(MidiTrack midiTrack) {
        MidiAnalysizer midiAnalysizer = new MidiAnalysizer();
        String[] result = midiAnalysizer.analyse(midiTrack);
        int index = 0;
        for (JLabel analysisLabel : analysisLabels) {
            analysisLabel.setText(result[index++]);
        }
    }

    void setPlayListener() {
        clickManager.setClickListener(play, onPlayStop);
    }

    private OnClickListener onPlayStop = new OnClickListener() {
        @Override
        public void onClick(Component component) {
            if (midiPlayer.isPlaying()) {
                midiPlayer.stop();
                play.setText("Play");
            } else {
                midiPlayer.play(midiTrack.getMeta(), stepSequenceView.getNotes());
                play.setText("Stop");
            }
        }
    };

    void setOpenMidiListener(OnClickListener listener) {
        clickManager.setClickListener(openButton, listener);
    }

}
