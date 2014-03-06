package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.population.evaluate.rule.FitnessRule;
import com.ouchadam.fyp.algorithm.population.evaluate.rule.FixedKeySignatureRule;
import com.ouchadam.fyp.algorithm.population.evaluate.rule.NoteDiversityRule;
import com.ouchadam.fyp.algorithm.population.evaluate.rule.NoteRangeRule;
import com.ouchadam.fyp.analysis.Key;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class RuleTabManager extends TabManager implements RuleController {

    private static final String TAB_TITLE = "Rules";
    private final RuleManager ruleManager;

    RuleTabManager(JTabbedPane tabbedPane, RuleManager ruleManager) {
        super(tabbedPane);
        this.ruleManager = ruleManager;
    }

    @Override
    public JTabbedPane create() {
        JPanel panel = new JPanel();
        panel.add(createRules());
        panel.setBorder(new EmptyBorder(25, 20, 0, 20));
        return createTabbedPane(TAB_TITLE, panel);
    }

    private Component createRules() {
        JPanel slidersContainer = new JPanel(new GridLayout(0, 1));
        ruleManager.create();
        ruleManager.attachTo(slidersContainer);
        return slidersContainer;
    }

    @Override
    public List<FitnessRule<Member>> get() {
        List<FitnessRule<Member>> rules = new ArrayList<FitnessRule<Member>>();
        for (RuleManager.RuleName ruleName : RuleManager.RuleName.values()) {
            RuleView ruleView = ruleManager.get(ruleName);
            if (ruleView.isChecked()) {
                switch (ruleName) {
                    case KEY :
                        rules.add(FixedKeySignatureRule.newInstance(Key.C));
                        break;

                    case RANGE :
                        rules.add(NoteRangeRule.newInstance(ruleView.getValue()));
                        break;

                    case DIVERSITY :
                        rules.add(NoteDiversityRule.newInstance(ruleView.getValue()));
                        break;
                }

            }
        }
        return rules;
    }
}
