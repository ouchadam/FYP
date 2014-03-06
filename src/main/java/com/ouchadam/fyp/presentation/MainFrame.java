package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.population.evaluate.fitness.FitnessRule;
import com.ouchadam.fyp.analysis.MidiTrack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

class MainFrame implements TextController, SequenceController, ParameterController, RuleController {

    private static final int FRAME_WIDTH = 550;
    private static final int FRAME_HEIGHT = 400;
    private static final String FRAME_TITLE = "My frame";

    private final JFrame frame;
    private final UiReadyListener uiListener;

    private AlgorithmTabManager algorithmTabManager;
    private SequenceTabManager sequenceTabManager;
    private RuleTabManager ruleTabManager;

    public static MainFrame newInstance(UiReadyListener uiListener) {
        MainFrame mainFrame = new MainFrame(new JFrame(), uiListener);
        mainFrame.createAndShowGui();
        return mainFrame;
    }

    MainFrame(JFrame frame, UiReadyListener uiListener) {
        this.frame = frame;
        this.uiListener = uiListener;
    }

    void createAndShowGui() {
        SwingUtilities.invokeLater(createGui);
    }

    private final Runnable createGui = new Runnable() {
        @Override
        public void run() {
            initFrame();
            uiListener.onUiReady();
        }
    };

    void initFrame() {
        frame.setTitle(FRAME_TITLE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocation(10, 200);
        frame.addWindowListener(closeWindow);
        initSubPanes();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void initSubPanes() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane();
        algorithmTabManager = new AlgorithmTabManager(tabbedPane, new SliderManager());
        sequenceTabManager = new SequenceTabManager(tabbedPane);
        ruleTabManager = new RuleTabManager(tabbedPane, new RuleManager());
        panel.add(sequenceTabManager.create());
        panel.add(algorithmTabManager.create());
        panel.add(ruleTabManager.create());
        frame.add(panel);
    }

    private final WindowAdapter closeWindow = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            System.exit(0);
        }
    };

    public void setOpenMidiListener(OnClickListener listener) {
        sequenceTabManager.setOpenMidiListener(listener);
    }

    public void setSaveListener(OnClickListener onClickListener) {
        algorithmTabManager.setSaveListener(onClickListener);
    }

    JButton getOpenMidiButton() {
        return sequenceTabManager.getOpenMidiButton();
    }

    @Override
    public void setMidiSelection(String text) {
        sequenceTabManager.setAnaliseText("Analyse : " + text);
    }

    @Override
    public void appendGenerationText(String text) {
        algorithmTabManager.updateText(text);
    }

    @Override
    public void setStartStop(AlgorithmController.Status startStopText) {
        algorithmTabManager.setStartStopText(startStopText);
    }

    @Override
    public void setResultColour(Color colour) {
        algorithmTabManager.setResultColour(colour);
    }

    @Override
    public void open(MidiTrack midiTrack) {
        sequenceTabManager.open(midiTrack);
    }

    public void setStartStopListener(OnClickListener onClickListener) {
        algorithmTabManager.setStartStopListener(onClickListener);
    }

    @Override
    public int initialPopulation() {
        return algorithmTabManager.initialPopulation();
    }

    @Override
    public int maxPopulation() {
        return algorithmTabManager.maxPopulation();
    }

    @Override
    public int acceptableFitness() {
        return algorithmTabManager.acceptableFitness();
    }

    @Override
    public int mutationPercent() {
        return algorithmTabManager.mutationPercent();
    }

    @Override
    public int crossoverPercent() {
        return algorithmTabManager.crossoverPercent();
    }

    @Override
    public void enable() {
        algorithmTabManager.enable();
    }

    @Override
    public void disable() {
        algorithmTabManager.disable();
    }

    @Override
    public List<FitnessRule<Member>> get() {
        return ruleTabManager.get();
    }
}
