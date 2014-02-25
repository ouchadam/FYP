package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AlgorithmTabManager extends TabManager {

    private static final String TAB_TITLE = "Algorithm";
    private JButton startStopButton;
    private JLabel textArea;

    AlgorithmTabManager(JTabbedPane tabbedPane) {
        super(tabbedPane);
    }

    @Override
    public JTabbedPane create() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        textArea = new JLabel("Dummy text");
        startStopButton = createButton("Start");
        panel.add(createSliders());
        panel.add(startStopButton);
        panel.add(textArea);
        panel.setPreferredSize(new Dimension(300, 210));
        panel.setBorder(new EmptyBorder(25, 20, 0, 20));
        return createTabbedPane(TAB_TITLE, panel);
    }

    private Component createSliders() {
        JPanel slidersContainer = new JPanel(new GridLayout(3, 2));
        initSliders(slidersContainer);
        return slidersContainer;
    }

    private void initSliders(JPanel panel) {
        JSlider fooSlider = new JSlider(JSlider.HORIZONTAL);
        JSlider barSlider = new JSlider(JSlider.HORIZONTAL);
        JLabel fooLabel = new JLabel("Foo");
        JLabel barLabel = new JLabel("Bar");
        panel.add(fooLabel);
        panel.add(fooSlider);
        panel.add(barLabel);
        panel.add(barSlider);
    }

    void setStartStopListener(OnClickListener listener) {
        setClickListener(startStopButton, listener);
    }

    void updateText(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textArea.setText(text);
            }
        });
    }

    public void setStartStopText(AlgorithmController.Status startStopText) {
        switch (startStopText) {
            case IDLE:
                startStopButton.setText("Start");
                break;
            case RUNNING:
                startStopButton.setText("Stop");
                break;
            default:
                throw new RuntimeException("Unhandled start stop : " + startStopText.name());
        }
    }

    public void setResultColour(Color colour) {
        textArea.setForeground(colour);
    }
}
