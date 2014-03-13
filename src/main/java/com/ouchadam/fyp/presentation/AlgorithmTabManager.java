package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;

class AlgorithmTabManager implements ParameterController {

    private final SliderManager sliderManager;
    private final JButton startStopButton;
    private final JLabel textArea;
    private final JButton saveButton;
    private final JButton analyseButton;
    private final ClickManager clickManager;

    AlgorithmTabManager(SliderManager sliderManager, JButton startStopButton, JLabel textArea, JButton saveButton, JButton analyseButton, ClickManager clickManager) {
        this.sliderManager = sliderManager;
        this.startStopButton = startStopButton;
        this.textArea = textArea;
        this.saveButton = saveButton;
        this.analyseButton = analyseButton;
        this.clickManager = clickManager;
    }

    void setStartStopListener(OnClickListener listener) {
        clickManager.setClickListener(startStopButton, listener);
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
        clickManager.setClickListener(saveButton, listener);
    }

    public void setAnalyseListener(OnClickListener listener) {
        clickManager.setClickListener(analyseButton, listener);
    }
}
