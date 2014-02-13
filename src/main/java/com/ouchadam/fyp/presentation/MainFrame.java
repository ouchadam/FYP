package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame implements ButtonController, TextController {

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 300;
    private static final String FRAME_TITLE = "My frame";
    private final JFrame frame;

    private OnClickListener onOpen;
    private OnClickListener onAnalise;
    private JButton openButton;
    private JButton analiseButton;
    private JLabel selectedMidi;

    public static MainFrame newInstance() {
        MainFrame mainFrame = new MainFrame(new JFrame(BorderLayout.CENTER));
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
    }

    private void initSubPanes() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        JTabbedPane tabbedPane = new JTabbedPane();
        panel.add(createAnaliseTab(tabbedPane));
        panel.add(createAlgorithmTab(tabbedPane));
        frame.add(panel);
    }

    private JTabbedPane createAnaliseTab(JTabbedPane tabbedPane) {
        openButton = createButton("Choose a MIDI file", internalOnOpen);
        analiseButton = createButton("Analise", internalOnAnalise);
        analiseButton.setEnabled(false);
        selectedMidi = new JLabel();
        return createTabbedPane("Analise", tabbedPane, selectedMidi, openButton, analiseButton);
    }

    private JTabbedPane createAlgorithmTab(JTabbedPane tabbedPane) {
        JButton fooButton = new JButton();
        return createTabbedPane("Algorithm", tabbedPane, fooButton);
    }

    private JButton createButton(String title, ActionListener internalListener) {
        JButton button = new JButton(title);
        button.addActionListener(internalListener);
        return button;
    }

    private JTabbedPane createTabbedPane(String tile, JTabbedPane pane, Component... components) {
        pane.add(tile, addToPanel(new JPanel(), components));
        return pane;
    }

    private JPanel addToPanel(JPanel panel, Component... components) {
        for (Component component : components) {
            panel.add(component);
        }
        return panel;
    }

    private final ActionListener internalOnOpen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            onClick(onOpen, openButton);
        }
    };

    private final ActionListener internalOnAnalise = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            onClick(onAnalise, analiseButton);
        }
    };

    private void onClick(OnClickListener listener, JButton button) {
        if (listener != null) {
            listener.onClick(button);
        }
    }

    private final WindowAdapter closeWindow = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            System.exit(0);
        }
    };

    public void setOpenMidiListener(OnClickListener listener) {
        this.onOpen = listener;
    }

    public void setAnaliseListener(OnClickListener listener) {
        this.onAnalise = listener;
    }

    @Override
    public void enableAnalise(boolean enabled) {
        this.analiseButton.setEnabled(enabled);
    }

    JButton getOpenMidiButton() {
        return openButton;
    }

    JButton getAnaliseButton() {
        return analiseButton;
    }

    @Override
    public void setMidiSelection(String text) {
        selectedMidi.setText(text);
    }
}
