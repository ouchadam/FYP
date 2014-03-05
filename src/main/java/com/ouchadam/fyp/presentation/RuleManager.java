package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class RuleManager {

    private static final int RULE_COUNT = 4;
    private final Map<RuleName, RuleView> rules;

    public RuleView isChecked(RuleName name) {
        return rules.get(name);
    }

    enum RuleName {
        RANGE, KEY
    }

    RuleManager() {
        this.rules = new HashMap<RuleName, RuleView>(RULE_COUNT);
    }

    public void create() {
        add(RuleName.RANGE, RuleView.newInstance("Range"));
        add(RuleName.KEY, RuleView.newInstance("Key"));
    }

    private void add(RuleName name, RuleView ruleView) {
        rules.put(name, ruleView);
    }

    public void attachTo(JPanel panel) {
        int row = 0;
        for (RuleName rule : RuleName.values()) {
            rules.get(rule).attachTo(panel);
        }
    }

}
