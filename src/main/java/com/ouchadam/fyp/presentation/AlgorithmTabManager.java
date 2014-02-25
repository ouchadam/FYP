package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class AlgorithmTabManager extends TabManager {

    private static final String TAB_TITLE = "Algorithm";
    private JButton startStopButton;
    private JLabel textArea;

    AlgorithmTabManager(JTabbedPane tabbedPane) {
        super(tabbedPane);
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
        int sliderCount = 4;
        JPanel slidersContainer = new JPanel(new GridBagLayout());
        slidersContainer.setPreferredSize(new Dimension(400, 400));

        List<Slider> sliders = new ArrayList<Slider>(sliderCount);

        sliders.add(Slider.newInstance("Max Population Size", 100, 10000));
        sliders.add(Slider.newInstance("Initial Population Size", 100, 10000));
        sliders.add(Slider.newInstance("Foo", 0 , 100));
        sliders.add(Slider.newInstance("Bar", 0 , 100));

        int row = 0;
        for (Slider slider : sliders) {
            slider.attachTo(slidersContainer, row);
            row ++;
        }

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
}
