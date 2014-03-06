package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;

class RandomAtZeroSlider extends Slider {

    public static Slider newInstance(String label, int min, int max, int defaultValue) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL);
        slider.setMinimum(min);
        slider.setMaximum(max);
        slider.setValue(defaultValue);
        JLabel labelView = new JLabel(label);
        JLabel value = new JLabel();
        return new RandomAtZeroSlider(slider, labelView, value);
    }

    RandomAtZeroSlider(JSlider slider, JLabel labelView, JLabel value) {
        super(slider, labelView, value);
    }

    @Override
    public void attachTo(JPanel panel, int row) {
        panel.setPreferredSize(new Dimension(panel.getWidth(), 100));
        super.attachTo(panel, row);
    }

    @Override
    protected void updateText(int value) {
        if (value == 0) {
            setValueText("Random");
        } else {
            super.updateText(value);
        }
    }
}
