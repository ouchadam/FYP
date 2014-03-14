package com.ouchadam.fyp.presentation.view;

import com.ouchadam.fyp.analysis.Key;
import com.ouchadam.fyp.presentation.tab.rule.RuleName;

import javax.swing.*;

public class CustomRuleView extends RuleView {

    public static RuleView newInstance(RuleName ruleName, int minimum, int maximum, int defaultValue, boolean defaultIsChecked) {
        CustomRuleView ruleView = new CustomRuleView(new JCheckBox(ruleName.toName()), new JSlider(), new JLabel());
        ruleView.init(ruleName, defaultIsChecked, defaultValue, minimum, maximum);
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
