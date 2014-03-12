package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

class RuleWeightView {

    private static final int MINIMUM_WEIGHT = 0;
    private static final int MAXIMUM_WEIGHT = 100;
    private final JSlider slider;
    private final JLabel sliderName;
    private final JLabel valueLabel;

    public static RuleWeightView newInstance(RuleContainer<Member> ruleContainer) {
        RuleWeightView ruleView = new RuleWeightView(new JSlider(JSlider.VERTICAL), new JLabel(ruleContainer.ruleName.name()), new JLabel());
        ruleView.init();
        return ruleView;
    }

    public RuleWeightView(JSlider slider, JLabel sliderName, JLabel valueLabel) {
        this.slider = slider;
        this.sliderName = sliderName;
        this.valueLabel = valueLabel;
    }

    protected void init() {
        this.slider.setMinimum(MINIMUM_WEIGHT);
        this.slider.setMaximum(MAXIMUM_WEIGHT);
        this.slider.setValue(100);
        this.slider.setAlignmentX(JSlider.LEFT_ALIGNMENT);
        setTextFromSlider();
        this.slider.addChangeListener(sliderChange);
    }

    private final ChangeListener sliderChange = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            setTextFromSlider();
        }
    };

    private void setTextFromSlider() {
        valueLabel.setText(updateValueText(getValue()));
    }

    protected String updateValueText(int value) {
        return String.valueOf(value);
    }

    public void attachTo(JPanel panel) {
        JPanel ruleContainer = new JPanel(new GridLayout(3, 1));
        ruleContainer.setPreferredSize(new Dimension(50, 200));

        ruleContainer.add(wrapWith(50, 20, valueLabel));
        JPanel sliderWrapper = wrapWith(50, 150, slider);

        ruleContainer.add(sliderWrapper);
        ruleContainer.add(wrapWith(100, 20, sliderName));
        panel.add(ruleContainer);
    }

    private JPanel wrapWith(int width, int height, Component component) {
        JPanel panel = new JPanel();
        Dimension preferredSize = new Dimension(width, height);
        panel.setPreferredSize(preferredSize);
        panel.add(component);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panel;
    }

    public int getValue() {
        return slider.getValue();
    }
}
