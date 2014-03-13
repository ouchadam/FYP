package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AlgorithmTabCreator extends TabCreator {

    private static final String TAB_TITLE = "Algorithm";
    private static final int ALGORITHM_BUTTON_HEIGHT = 30;
    private static final int ALGORITHM_BUTTON_WIDTH = 100;
    private static final Dimension ALGORITHM_BUTTON_DIMENS = new Dimension(ALGORITHM_BUTTON_WIDTH, ALGORITHM_BUTTON_HEIGHT);

    private final SliderManager sliderManager;
    private JButton startStopButton;
    private JLabel textArea;
    private JButton saveButton;
    private JButton analyseButton;

    AlgorithmTabCreator(JTabbedPane tabbedPane, SliderManager sliderManager) {
        super(tabbedPane);
        this.sliderManager = sliderManager;
    }

    @Override
    public JTabbedPane create() {
        JPanel panel = new JPanel(new GridLayout(7, 1));
        JPanel fooPanel = new JPanel();
        textArea = new JLabel();
        initFitnessText(textArea);
        startStopButton = createButton("Start");
        startStopButton.setPreferredSize(ALGORITHM_BUTTON_DIMENS);
        saveButton = createButton("Save");
        saveButton.setPreferredSize(ALGORITHM_BUTTON_DIMENS);
        analyseButton = createButton("Analyse");
        analyseButton.setPreferredSize(ALGORITHM_BUTTON_DIMENS);

        fooPanel.add(startStopButton);
        fooPanel.add(saveButton);
        fooPanel.add(analyseButton);

        panel.add(createSliders());
        panel.add(textArea);
        panel.add(fooPanel);
        panel.setPreferredSize(new Dimension(450, 800));
        panel.setBorder(new EmptyBorder(12, 20, 0, 20));
        return createTabbedPane(TAB_TITLE, panel);
    }

    private void initFitnessText(JLabel label) {
        label.setText("Index :  ###    Fitness : ###");
    }

    private Component createSliders() {
        JPanel slidersContainer = new JPanel(new GridBagLayout());
        slidersContainer.setPreferredSize(new Dimension(450, 700));
        sliderManager.create();
        sliderManager.attachTo(slidersContainer);
        return slidersContainer;
    }

    public AlgorithmTabManager createTabManager(ClickManager clickManager) {
        return new AlgorithmTabManager(sliderManager, startStopButton, textArea, saveButton, analyseButton, clickManager);
    }

}
