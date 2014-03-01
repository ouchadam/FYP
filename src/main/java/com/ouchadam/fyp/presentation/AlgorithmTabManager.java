package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AlgorithmTabManager extends TabManager implements ParameterController {

    private static final String TAB_TITLE = "Algorithm";
    private final SliderManager sliderManager;
    private JButton startStopButton;
    private JLabel textArea;

    AlgorithmTabManager(JTabbedPane tabbedPane, SliderManager sliderManager) {
        super(tabbedPane);
        this.sliderManager = sliderManager;
    }

    @Override
    public JTabbedPane create() {
        JPanel panel = new JPanel(new GridLayout(7, 1));
        textArea = new JLabel("Dummy text");
        startStopButton = createButton("Start");
        panel.add(createSliders());
        panel.add(startStopButton);
        panel.add(textArea);
        panel.setPreferredSize(new Dimension(400, 600));
        panel.setBorder(new EmptyBorder(25, 20, 0, 20));
        return createTabbedPane(TAB_TITLE, panel);
    }

    private Component createSliders() {
        JPanel slidersContainer = new JPanel(new GridBagLayout());
        slidersContainer.setPreferredSize(new Dimension(400, 400));
        sliderManager.create();
        sliderManager.attachTo(slidersContainer);
        return slidersContainer;
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

    @Override
    public int initialPopulation() {
        return sliderManager.get(SliderManager.SliderName.INITIAL);
    }

    @Override
    public int maxPopulation() {
        return sliderManager.get(SliderManager.SliderName.MAX);
    }

    @Override
    public int acceptableFitness() {
        return sliderManager.get(SliderManager.SliderName.ACCEPTABLE_FITNESS);
    }
}
