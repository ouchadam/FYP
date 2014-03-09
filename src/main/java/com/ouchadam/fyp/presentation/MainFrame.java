package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MainFrame {

    private static final int FRAME_WIDTH = 550;
    private static final int FRAME_HEIGHT = 400;
    private static final String FRAME_TITLE = "FYP : Adam Brown";

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
            uiListener.onUiReady(new FrameController(sequenceTabManager, algorithmTabManager, ruleTabManager, frame));
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

}
