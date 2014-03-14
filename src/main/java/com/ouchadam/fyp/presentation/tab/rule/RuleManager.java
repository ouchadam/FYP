package com.ouchadam.fyp.presentation.tab.rule;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.analysis.Key;
import com.ouchadam.fyp.presentation.view.CustomRuleView;
import com.ouchadam.fyp.presentation.view.RuleView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleManager {

    private static final int RULE_COUNT = 4;
    private final Map<RuleName, RuleView> rules;

    public RuleManager() {
        this.rules = new HashMap<RuleName, RuleView>(RULE_COUNT);
    }

    public void create() {
        createAndAddRuleViewFor(RuleName.RANGE, 0, 24, 12, true);
        createAndAddCustomRuleViewFor(RuleName.KEY, 0, Key.values().length - 1, 0, false);
        createAndAddRuleViewFor(RuleName.DIVERSITY, 0, Member.CHILD_COUNT, Member.CHILD_COUNT / 2, false);
        createAndAddRuleViewFor(RuleName.INTERVAL, 0, 12, 4, false);
        createAndAddRuleViewFor(RuleName.EVEN_RHYTHM, 0, 0, 0, true);
        createAndAddRuleViewFor(RuleName.MIN_NOTE, 1, Member.CHILD_COUNT, 6, true);
    }

    private void createAndAddRuleViewFor(RuleName ruleName, int minimum, int maximum, int defaultValue, boolean isChecked) {
        add(ruleName, RuleView.newInstance(ruleName, minimum, maximum, defaultValue, isChecked));
    }

    private void createAndAddCustomRuleViewFor(RuleName ruleName, int minimum, int maximum, int defaultValue, boolean isChecked) {
        add(ruleName, CustomRuleView.newInstance(ruleName, minimum, maximum, defaultValue, isChecked));
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

    public List<RuleView> getSelected() {
        List<RuleView> selectedViews = new ArrayList<RuleView>();
        for (RuleName rule : RuleName.values()) {
            if (rules.get(rule).isChecked()) {
                selectedViews.add(rules.get(rule));
            }
        }
        return selectedViews;
    }

}
