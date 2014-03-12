package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

class MainFrame {

    private static final int FRAME_WIDTH = 550;
    private static final int FRAME_HEIGHT = 340;
    private static final String FRAME_TITLE = "FYP : Adam Brown";

    private final JFrame frame;
    private final UiReadyListener uiListener;

    private AlgorithmTabCreator algorithmTabCreator;
    private SequenceTabCreator sequenceTabManager;
    private RuleTabCreator ruleTabManager;
    private RuleWeightTabCreator ruleWeightTabCreator;
    private JTabbedPane tabbedPane;

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
            uiListener.onUiReady(new FrameController(sequenceTabManager, getAlgorithmTabManager(), ruleTabManager, frame));
        }
    };

    private AlgorithmTabManager getAlgorithmTabManager() {
        return algorithmTabCreator.createTabManager(new ClickManager());
    }

    void initFrame() {
        frame.setTitle(FRAME_TITLE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocation(10, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initSubPanes();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void initSubPanes() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        tabbedPane = new JTabbedPane();
        algorithmTabCreator = new AlgorithmTabCreator(tabbedPane, new SliderManager());
        sequenceTabManager = new SequenceTabCreator(tabbedPane, new MidiPlayer(new MidiSystemWrapper()), new ClickManager());
        ruleTabManager = new RuleTabCreator(tabbedPane, new RuleManager());
        ruleWeightTabCreator = new RuleWeightTabCreator(tabbedPane, new RuleWeightManager(ruleTabManager));
        panel.add(sequenceTabManager.create());
        panel.add(algorithmTabCreator.create());
        panel.add(ruleTabManager.create());
        panel.add(ruleWeightTabCreator.create());

        tabbedPane.addChangeListener(onTabChange);

        frame.add(panel);
    }

    private final ChangeListener onTabChange = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            if (tabbedPane.getSelectedIndex() == 3) {
                ruleWeightTabCreator.addRuleWeights();
            }
        }
    };

}
