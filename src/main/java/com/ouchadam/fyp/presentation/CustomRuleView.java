package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.analysis.Key;

import javax.swing.*;

class CustomRuleView extends RuleView {

    public static RuleView newInstance(String label, int minimum, int maximum, int defaultValue, boolean defaultIsChecked) {
        CustomRuleView ruleView = new CustomRuleView(new JCheckBox(label), new JSlider(), new JLabel());
        ruleView.init(defaultIsChecked, defaultValue, minimum, maximum);
        return ruleView;
    }

    public CustomRuleView(JCheckBox checkBox, JSlider slider, JLabel valueLabel) {
        super(checkBox, slider, valueLabel);
    }

    @Override
    protected String updateValueText(int value) {
        return Key.values()[value].name();
    }
}
