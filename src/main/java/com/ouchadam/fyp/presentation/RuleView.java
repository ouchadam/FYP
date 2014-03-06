package com.ouchadam.fyp.presentation;

import javax.swing.*;

class RuleView {

    private final JCheckBox checkBox;

    public static RuleView newInstance(String label, boolean defaultIsChecked) {
        JCheckBox checkBox = new JCheckBox(label);
        RuleView ruleView = new RuleView(checkBox);
        ruleView.init(defaultIsChecked);
        return ruleView;
    }

    public RuleView(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }

    private void init(boolean defaultIsChecked) {
        this.checkBox.setSelected(defaultIsChecked);
    }

    public void attachTo(JPanel panel) {
        panel.add(checkBox);
    }

    public boolean isChecked() {
        return checkBox.isSelected();
    }
}
