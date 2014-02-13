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

    private AnaliseTabManager analiseTabManager;
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
        analiseTabManager = new AnaliseTabManager(tabbedPane);
        algorithmTabManager = new AlgorithmTabManager(tabbedPane);
        panel.add(analiseTabManager.create());
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
        analiseTabManager.setOpenMidiListener(listener);
    }

    public void setAnaliseListener(OnClickListener listener) {
        analiseTabManager.setAnaliseListener(listener);
    }

    @Override
    public void enableAnalise(boolean enabled) {
        analiseTabManager.setAnaliseEnabled(enabled);
    }

    JButton getOpenMidiButton() {
        return analiseTabManager.getOpenMidiButton();
    }

    JButton getAnaliseButton() {
        return analiseTabManager.getAnaliseButton();
    }

    @Override
    public void setMidiSelection(String text) {
        analiseTabManager.setAnaliseText("Analise : " + text);
    }
}
