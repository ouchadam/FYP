package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AlgorithmTabManager extends TabManager implements ParameterController {

    private static final String TAB_TITLE = "Algorithm";
    private final SliderManager sliderManager;
    private JButton startStopButton;
    private JLabel textArea;
    private JButton saveButton;

    AlgorithmTabManager(JTabbedPane tabbedPane, SliderManager sliderManager) {
        super(tabbedPane);
        this.sliderManager = sliderManager;
    }

    @Override
    public JTabbedPane create() {
        JPanel panel = new JPanel(new GridLayout(7, 1));
        JPanel fooPanel = new JPanel();
        textArea = new JLabel("Dummy text");
        startStopButton = createButton("Start");
        saveButton = createButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 40));
        startStopButton.setPreferredSize(new Dimension(100, 40));

        fooPanel.add(startStopButton);
        fooPanel.add(saveButton);

        panel.add(createSliders());
        panel.add(textArea);
        panel.add(fooPanel);
        panel.setPreferredSize(new Dimension(450, 800));
        panel.setBorder(new EmptyBorder(25, 20, 0, 20));
        return createTabbedPane(TAB_TITLE, panel);
    }

    private Component createSliders() {
        JPanel slidersContainer = new JPanel(new GridBagLayout());
        slidersContainer.setPreferredSize(new Dimension(450, 700));
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

    @Override
    public int mutationPercent() {
        return sliderManager.get(SliderManager.SliderName.MUTATION_PERCENT);
    }

    @Override
    public int crossoverPercent() {
        return sliderManager.get(SliderManager.SliderName.CROSSOVER_PERCENT);
    }

    @Override
    public void enable() {
        sliderManager.enable();
        saveButton.setEnabled(true);
    }

    @Override
    public void disable() {
        sliderManager.disable();
        saveButton.setEnabled(false);
    }

    public void setSaveListener(OnClickListener listener) {
        setClickListener(saveButton, listener);
    }
}
