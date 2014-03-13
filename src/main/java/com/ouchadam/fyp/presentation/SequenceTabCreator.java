package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.Log;
import com.ouchadam.fyp.analysis.MidiMessageMarshaller;
import com.ouchadam.fyp.analysis.MidiReader;
import com.ouchadam.fyp.analysis.MidiTrack;
import com.ouchadam.fyp.presentation.view.RangeCreator;
import com.ouchadam.fyp.presentation.view.StepSequenceView;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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
        JPanel parent = new JPanel(new BorderLayout());
        JPanel sequencePanel = createSequencePanel();
        sequencePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(sequencePanel);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(220, 270));

        scrollPane.setAlignmentX(ScrollPane.CENTER_ALIGNMENT);

        parent.add(scrollPane, BorderLayout.WEST);

        JPanel analysis = new JPanel(new GridLayout(3, 1, 0, 0));
        analysis.setPreferredSize(new Dimension(300, 200));

        analysisLabels = new JLabel[ANALYSIS_LABEL_COUNT];

        for (int index = 0; index < ANALYSIS_LABEL_COUNT; index++) {
            analysisLabels[index] = new JLabel();
            analysis.add(analysisLabels[index]);
        }

        parent.add(analysis, BorderLayout.EAST);

        return createTabbedPane(TAB_TITLE, parent);
    }

    private JPanel createSequencePanel() {
        JPanel parent = new JPanel(new BorderLayout());
        stepSequenceView = createAndInitStepSequence();
        JPanel openPanel = createAndInitOpenPanel();
        JPanel playPanel = createAndInitPlayPanel();
        parent.add(openPanel, BorderLayout.NORTH);
        parent.add(stepSequenceView, BorderLayout.CENTER);
        parent.add(playPanel, BorderLayout.SOUTH);
        return parent;
    }

    private JPanel createAndInitPlayPanel() {
        play = createLoadDependantButton("Play");
        JPanel playPanel = new JPanel();
        playPanel.add(play);
        setPlayListener();
        return playPanel;
    }

    private JPanel createAndInitOpenPanel() {
        JPanel openPanel = new JPanel();
        openButton = createSequenceButton("Choose a MIDI file");
        openPanel.add(openButton);
        return openPanel;
    }

    private JButton createSequenceButton(String label) {
        JButton button = createButton(label);
        button.setPreferredSize(new Dimension(190, 30));
        return button;
    }

    private StepSequenceView createAndInitStepSequence() {
        StepSequenceView stepSequenceView = new StepSequenceView(new RangeCreator());
        stepSequenceView.init(12);
        return stepSequenceView;
    }

    private JButton createLoadDependantButton(String title) {
        JButton button = createSequenceButton(title);
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

    @Override
    public void analyse(File file) {
        System.out.println("Analyse");
        try {
            MidiReader midiReader = new MidiReader(new MidiMessageMarshaller(), new MidiSystemWrapper());
            open(midiReader.read(file));
        } catch (Exception e) {
            Log.e("Failed to analyse file", e);
        }
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
