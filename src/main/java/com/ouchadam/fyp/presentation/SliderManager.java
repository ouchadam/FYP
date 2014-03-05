package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class SliderManager {

    private static final int SLIDER_COUNT = 4;
    private final Map<SliderName, Slider> sliders;

    public int get(SliderName name) {
        return sliders.get(name).getValue();
    }

    enum SliderName {
        MAX, INITIAL, ACCEPTABLE_FITNESS, MUTATION_PERCENT, CROSSOVER_PERCENT
    }

    SliderManager() {
        this.sliders = new HashMap<SliderName, Slider>(SLIDER_COUNT);
    }

    public void create() {
        add(SliderName.MAX, Slider.newInstance("Max Population Size", 100, 20000, 2633));
        add(SliderName.INITIAL, Slider.newInstance("Initial Population Size", 100, 20000, 2500));
        add(SliderName.ACCEPTABLE_FITNESS, Slider.newInstance("Acceptable Fitness", 0, 100, 100));
        add(SliderName.MUTATION_PERCENT, Slider.newInstance("Mutation %", 0, 100, 5));
        add(SliderName.CROSSOVER_PERCENT, CustomTextSlider.newInstance("Crossover %", 0, 100, 0));
    }

    private void add(SliderName name, Slider slider) {
        sliders.put(name, slider);
    }

    public void attachTo(JPanel panel) {
        int row = 0;
        for (SliderName slider : SliderName.values()) {
            sliders.get(slider).attachTo(panel, row);
            row++;
        }
    }

    public void enable() {
        for (SliderName slider : SliderName.values()) {
            sliders.get(slider).enable();
        }
    }

    public void disable() {
        for (SliderName slider : SliderName.values()) {
            sliders.get(slider).disable();
        }
    }

    private static class CustomTextSlider extends Slider {

        public static Slider newInstance(String label, int min, int max, int defaultValue) {
            JSlider slider = new JSlider(JSlider.HORIZONTAL);
            slider.setMinimum(min);
            slider.setMaximum(max);
            slider.setValue(defaultValue);
            JLabel labelView = new JLabel(label);
            JLabel value = new JLabel();
            return new CustomTextSlider(slider, labelView, value);
        }

        CustomTextSlider(JSlider slider, JLabel labelView, JLabel value) {
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

}
