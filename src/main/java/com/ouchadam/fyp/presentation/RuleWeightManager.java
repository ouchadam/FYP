package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class RuleWeightManager {

    private static final int RULE_COUNT = 4;
    private final Map<RuleManager.RuleName, RuleWeightView> rules;
    private final RuleController ruleController;

    RuleWeightManager(RuleController ruleController) {
        this.ruleController = ruleController;
        this.rules = new HashMap<RuleManager.RuleName, RuleWeightView>(RULE_COUNT);
    }

    public void clear(JPanel panel) {
        rules.clear();
        panel.removeAll();
    }

    public void create() {
        for (RuleContainer<Member> rule : ruleController.get()) {
            add(rule.ruleName, RuleWeightView.newInstance(rule));
        }
    }

    private void add(RuleManager.RuleName name, RuleWeightView ruleView) {
        rules.put(name, ruleView);
    }

    public void attachTo(JPanel panel) {
        for (RuleManager.RuleName rule : RuleManager.RuleName.values()) {
            if (rules.containsKey(rule)) {
                rules.get(rule).attachTo(panel);
            }
        }
    }

    public RuleWeightView get(RuleManager.RuleName name) {
        return rules.get(name);
    }

}
