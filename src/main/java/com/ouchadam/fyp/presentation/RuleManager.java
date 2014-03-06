package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.analysis.Key;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

class RuleManager {

    private static final int RULE_COUNT = 4;
    private final Map<RuleName, RuleView> rules;

    enum RuleName {
        RANGE, KEY, DIVERSITY;
    }
    RuleManager() {
        this.rules = new HashMap<RuleName, RuleView>(RULE_COUNT);
    }

    public void create() {
        add(RuleName.RANGE, RuleView.newInstance("Range", 0, 24, 12, true));
        add(RuleName.KEY, CustomRuleView.newInstance("Key", 0, Key.values().length - 1, 0, false));
        add(RuleName.DIVERSITY, RuleView.newInstance("Diversity", 0, Member.CHILD_COUNT, 0, false));
    }

    private void add(RuleName name, RuleView ruleView) {
        rules.put(name, ruleView);
    }

    public void attachTo(JPanel panel) {
        for (RuleName rule : RuleName.values()) {
            rules.get(rule).attachTo(panel);
        }
    }

    public RuleView get(RuleName name) {
        return rules.get(name);
    }

}
