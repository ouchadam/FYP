package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class RuleTabCreator extends TabCreator implements RuleController {

    private static final String TAB_TITLE = "Rules";
    private final RuleFactory ruleFactory;

    RuleTabCreator(JTabbedPane tabbedPane, RuleFactory ruleFactory) {
        super(tabbedPane);
        this.ruleFactory = ruleFactory;
    }

    @Override
    public JTabbedPane create() {
        JPanel panel = new JPanel();
        panel.add(createRules());
        panel.setBorder(new EmptyBorder(12, 20, 0, 20));
        return createTabbedPane(TAB_TITLE, panel);
    }

    private Component createRules() {
        JPanel slidersContainer = new JPanel(new GridLayout(0, 1));
        ruleFactory.createRules();
        ruleFactory.attachRulesTo(slidersContainer);
        return slidersContainer;
    }

    @Override
    public List<RuleContainer<Member>> get() {
        List<RuleContainer<Member>> rules = new ArrayList<RuleContainer<Member>>();
        for (RuleName ruleName : RuleName.values()) {
            RuleView ruleView = ruleFactory.getRuleView(ruleName);
            if (ruleView.isChecked()) {
                rules.add(ruleFactory.getContainer(ruleName));
            }
        }
        return rules;
    }

}
