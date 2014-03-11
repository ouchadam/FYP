package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AlgorithmTabCreator extends TabCreator {

    private static final String TAB_TITLE = "Algorithm";
    private final SliderManager sliderManager;
    private JButton startStopButton;
    private JLabel textArea;
    private JButton saveButton;

    AlgorithmTabCreator(JTabbedPane tabbedPane, SliderManager sliderManager) {
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

    public AlgorithmTabManager createTabManager(ClickManager clickManager) {
        return new AlgorithmTabManager(sliderManager, startStopButton, textArea, saveButton, clickManager);
    }

}
