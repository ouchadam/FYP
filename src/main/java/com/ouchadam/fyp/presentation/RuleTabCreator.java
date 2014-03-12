package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.evaluate.rule.*;
import com.ouchadam.fyp.analysis.Key;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class RuleTabCreator extends TabCreator implements RuleController {

    private static final String TAB_TITLE = "Rules";
    private final RuleManager ruleManager;

    RuleTabCreator(JTabbedPane tabbedPane, RuleManager ruleManager) {
        super(tabbedPane);
        this.ruleManager = ruleManager;
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
        ruleManager.create();
        ruleManager.attachTo(slidersContainer);
        return slidersContainer;
    }

    @Override
    public List<RuleContainer<Member>> get() {
        List<RuleContainer<Member>> rules = new ArrayList<RuleContainer<Member>>();
        for (RuleManager.RuleName ruleName : RuleManager.RuleName.values()) {
            RuleView ruleView = ruleManager.get(ruleName);
            if (ruleView.isChecked()) {
                switch (ruleName) {
                    case KEY:
                        rules.add(new RuleContainer<Member>(FixedKeySignatureRule.newInstance(Key.values()[ruleView.getValue()]), RuleManager.RuleName.KEY));
                        break;

                    case RANGE:
                        rules.add(new RuleContainer<Member>(new NoteRangeRule(ruleView.getValue()), RuleManager.RuleName.RANGE));
                        break;

                    case DIVERSITY:
                        rules.add(new RuleContainer<Member>(new NoteDiversityRule(ruleView.getValue()), RuleManager.RuleName.DIVERSITY));
                        break;

                    case INTERVAL:
                        rules.add(new RuleContainer<Member>(new IntervalRangeRule(ruleView.getValue()), RuleManager.RuleName.INTERVAL));
                        break;

                    case EVEN_RHYTHM:
                        rules.add(new RuleContainer<Member>(new EvenRhythmRule(), RuleManager.RuleName.EVEN_RHYTHM));
                        break;

                    case MIN_NOTE:
                        rules.add(new RuleContainer<Member>(new MinimumNoteRule(ruleView.getValue()), RuleManager.RuleName.MIN_NOTE));
                        break;
                }

            }
        }
        return rules;
    }

}
