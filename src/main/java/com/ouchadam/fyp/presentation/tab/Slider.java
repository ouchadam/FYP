package com.ouchadam.fyp.presentation.tab;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

class Slider {

    private final JSlider slider;
    private final JLabel labelView;
    private final JLabel value;

    private int sliderValue;
    private GridBagConstraints constraints;

    public static Slider newInstance(String label, int min, int max, int defaultValue) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL);
        slider.setMinimum(min);
        slider.setMaximum(max);
        slider.setValue(defaultValue);
        JLabel labelView = new JLabel(label);
        JLabel value = new JLabel();
        return new Slider(slider, labelView, value);
    }

    Slider(JSlider slider, JLabel labelView, JLabel value) {
        this.slider = slider;
        this.labelView = labelView;
        this.value = value;
        init();
    }

    private void init() {
        this.sliderValue = slider.getValue();
        value.setMinimumSize(new Dimension(30, 20));
        value.setHorizontalAlignment(SwingConstants.RIGHT);
        updateText(sliderValue);
        slider.addChangeListener(onSliderChange);
    }

    protected void updateText(int value) {
        setValueText(String.valueOf(value));
    }

    private final ChangeListener onSliderChange = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            sliderValue = slider.getValue();
            updateText(sliderValue);
        }
    };

    protected void setValueText(String text) {
        value.setText(String.valueOf(text));
    }

    public int getValue() {
        return sliderValue;
    }

    public void attachTo(JPanel panel, int row) {
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(0,0,0,0);
        constraints.gridy = row;

        panel.add(labelView, labelConstraints());
        panel.add(slider, sliderConstraints());
        panel.add(value, valueConstraints());
    }

    private GridBagConstraints labelConstraints() {
        setConstraints(0);
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.2f;
        return constraints;
    }

    private GridBagConstraints sliderConstraints() {
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.7f;
        setConstraints(3);
        return constraints;
    }


    private GridBagConstraints valueConstraints() {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.weightx = 0.2f;
        setConstraints(6);
        return constraints;
    }

    private GridBagConstraints setConstraints(int col) {
        constraints.gridheight = 1;
        constraints.gridx = col;
        return constraints;
    }

    public void enable() {
        slider.setEnabled(true);
    }

    public void disable() {
        slider.setEnabled(false);
    }
}
