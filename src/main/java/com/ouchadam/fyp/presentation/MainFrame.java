package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MainFrame implements ButtonController, TextController {

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 300;
    private static final String FRAME_TITLE = "My frame";
    private final JFrame frame;

    private AnalyseTabManager analyseTabManager;
    private AlgorithmTabManager algorithmTabManager;

    public static MainFrame newInstance() {
        MainFrame mainFrame = new MainFrame(new JFrame());
        mainFrame.initFrame();
        return mainFrame;
    }

    MainFrame(JFrame frame) {
        this.frame = frame;
    }

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
        analyseTabManager = new AnalyseTabManager(tabbedPane);
        panel.add(analyseTabManager.create());
        algorithmTabManager = new AlgorithmTabManager(tabbedPane);
        panel.add(algorithmTabManager.create());
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
        analyseTabManager.setOpenMidiListener(listener);
    }

    public void setAnaliseListener(OnClickListener listener) {
        analyseTabManager.setAnaliseListener(listener);
    }

    @Override
    public void enableAnalise(boolean enabled) {
        analyseTabManager.setAnaliseEnabled(enabled);
    }

    JButton getOpenMidiButton() {
        return analyseTabManager.getOpenMidiButton();
    }

    JButton getAnaliseButton() {
        return analyseTabManager.getAnaliseButton();
    }

    @Override
    public void setMidiSelection(String text) {
        analyseTabManager.setAnaliseText("Analise : " + text);
    }
}
