package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class RuleWeightManager {

    private static final int RULE_COUNT = 4;
    private final Map<RuleName, RuleWeightView> rules;

    RuleWeightManager() {
        this.rules = new HashMap<RuleName, RuleWeightView>(RULE_COUNT);
    }

    public void clear(JPanel panel) {
        rules.clear();
        panel.removeAll();
    }

    public void create(RuleManager ruleManager) {
        for (RuleView rule : ruleManager.getSelected()) {
            add(rule.getName(), RuleWeightView.newInstance(rule.getName()));
        }
    }

    private void add(RuleName name, RuleWeightView ruleView) {
        rules.put(name, ruleView);
    }

    public void attachTo(JPanel panel) {
        for (RuleName rule : RuleName.values()) {
            if (rules.containsKey(rule)) {
                rules.get(rule).attachTo(panel);
            }
        }
    }

    public RuleWeightView get(RuleName name) {
        return rules.get(name);
    }

}
