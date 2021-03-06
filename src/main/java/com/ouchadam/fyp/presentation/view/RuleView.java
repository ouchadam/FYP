package com.ouchadam.fyp.presentation.view;

import com.ouchadam.fyp.presentation.tab.rule.RuleName;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class RuleView {

    private final JCheckBox checkBox;
    private final JSlider slider;
    private final JLabel valueLabel;

    public static RuleView newInstance(RuleName ruleName, int minimum, int maximum, int defaultValue, boolean defaultIsChecked) {
        RuleView ruleView = new RuleView(new JCheckBox(), new JSlider(), new JLabel());
        ruleView.init(ruleName, defaultIsChecked, defaultValue, minimum, maximum);
        return ruleView;
    }

    public RuleView(JCheckBox checkBox, JSlider slider, JLabel valueLabel) {
        this.checkBox = checkBox;
        this.slider = slider;
        this.valueLabel = valueLabel;
    }

    protected void init(RuleName ruleName, boolean defaultIsChecked, int defaultValue, int minimum, int maximum) {
        this.checkBox.setText(ruleName.toName());
        this.checkBox.setToolTipText(ruleName.toolTip());
        this.checkBox.setSelected(defaultIsChecked);
        this.slider.setMinimum(minimum);
        this.slider.setMaximum(maximum);
        this.slider.setValue(defaultValue);
        this.valueLabel.setPreferredSize(new Dimension(50, 30));
        this.checkBox.setPreferredSize(new Dimension(150, 30));
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
        JPanel ruleContainer = new JPanel();
        ruleContainer.add(checkBox);
        ruleContainer.add(slider);
        ruleContainer.add(valueLabel);
        panel.add(ruleContainer);
    }

    public boolean isChecked() {
        return checkBox.isSelected();
    }

    public int getValue() {
        return slider.getValue();
    }

    public RuleName getName() {
        return RuleName.from(checkBox.getText());
    }
}
