package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

class RuleWeightView {

    private static final int MINIMUM_WEIGHT = 0;
    private static final int MAXIMUM_WEIGHT = 100;
    private static final int DEFAULT_WEIGHT = 100;

    private final JSlider slider;
    private final JLabel sliderName;
    private final JLabel valueLabel;

    public static RuleWeightView newInstance(RuleName ruleName) {
        RuleWeightView ruleView = new RuleWeightView(new JSlider(JSlider.VERTICAL), new JLabel(ruleName.toName()), new JLabel());
        ruleView.init(DEFAULT_WEIGHT);
        return ruleView;
    }

    public static RuleWeightView from(RuleWeightView ruleWeightView) {
        RuleWeightView ruleView = new RuleWeightView(new JSlider(JSlider.VERTICAL), new JLabel(ruleWeightView.sliderName.getText()), new JLabel());
        ruleView.init(ruleWeightView.getSliderValue());
        return ruleView;
    }

    public RuleWeightView(JSlider slider, JLabel sliderName, JLabel valueLabel) {
        this.slider = slider;
        this.sliderName = sliderName;
        this.valueLabel = valueLabel;
    }

    protected void init(int defaultValue) {
        this.slider.setMinimum(MINIMUM_WEIGHT);
        this.slider.setMaximum(MAXIMUM_WEIGHT);
        this.slider.setValue(defaultValue);
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
        valueLabel.setText(updateValueText(getSliderValue()));
    }

    protected String updateValueText(int value) {
        return String.valueOf(value);
    }

    public void attachTo(JPanel panel) {
        JPanel ruleContainer = new JPanel(new GridLayout(3, 1));
        ruleContainer.setPreferredSize(new Dimension(50, 200));
        ruleContainer.add(valueLabel);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(slider, BorderLayout.WEST);

        ruleContainer.add(panel1);
        ruleContainer.add(sliderName);
        panel.add(ruleContainer);
    }

    public float getValue() {
        return getSliderValue() / 100f;
    }

    private int getSliderValue() {
        return slider.getValue();
    }
}
