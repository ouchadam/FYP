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
        this.valueLabel.setPreferredSize(new Dimension(50, 30));
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
        JPanel ruleContainer = new JPanel(new GridLayout(3, 0));
        ruleContainer.add(sliderName);
        ruleContainer.add(slider);
        ruleContainer.add(valueLabel);
        panel.add(ruleContainer);
    }

    public int getValue() {
        return slider.getValue();
    }
}
