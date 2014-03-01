package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

class SliderManager {

    private static final int SLIDER_COUNT = 4;
    private final Map<SliderName, Slider> sliders;

    public int get(SliderName name) {
        return sliders.get(name).getValue();
    }

    enum SliderName {
        MAX, INITIAL, ACCEPTABLE_FITNESS, BAR;
    }

    SliderManager() {
        this.sliders = new HashMap<SliderName, Slider>(SLIDER_COUNT);
    }

    public void create() {
        add(SliderName.MAX, Slider.newInstance("Max Population Size", 100, 20000, 10000));
        add(SliderName.INITIAL, Slider.newInstance("Initial Population Size", 100, 20000, 2500));
        add(SliderName.ACCEPTABLE_FITNESS, Slider.newInstance("Acceptable Fitness", 0, 100, 100));
        add(SliderName.BAR, Slider.newInstance("Bar", 0, 100, 50));
    }

    private void add(SliderName name, Slider slider) {
        sliders.put(name, slider);
    }

    public void attachTo(JPanel panel) {
        int row = 0;
        for (SliderName slider : SliderName.values()) {
            sliders.get(slider).attachTo(panel, row);
            row ++;
        }
    }

}
