package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

class RuleView {

    private final JCheckBox checkBox;

    public static RuleView newInstance(String label) {
        JCheckBox checkBox = new JCheckBox(label);
        return new RuleView(checkBox);
    }

    public RuleView(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public void attachTo(JPanel panel) {
        panel.add(checkBox);
    }

    public boolean isChecked() {
        return checkBox.isSelected();
    }
}
