package com.ouchadam.fyp.presentation;

import com.ouchadam.fyp.algorithm.Member;
import com.ouchadam.fyp.algorithm.evaluate.rule.*;
import com.ouchadam.fyp.analysis.Key;

import javax.swing.*;

class RuleFactory {

    private final RuleManager ruleManager;
    private final RuleWeightManager ruleWeightManager;

    RuleFactory(RuleManager ruleManager, RuleWeightManager ruleWeightManager) {
        this.ruleManager = ruleManager;
        this.ruleWeightManager = ruleWeightManager;
    }

    public RuleContainer<Member> getContainer(RuleName ruleName) {
        RuleView ruleView = ruleManager.get(ruleName);
        RuleWeightView ruleWeightView = ruleWeightManager.get(ruleName);
        int weight = ruleWeightView.getValue();

        switch (ruleName) {
            case KEY:
                return new RuleContainer<Member>(FixedKeySignatureRule.newInstance(Key.values()[ruleView.getValue()]), RuleName.KEY, weight);

            case RANGE:
                return new RuleContainer<Member>(new NoteRangeRule(ruleView.getValue()), RuleName.RANGE, weight);

            case DIVERSITY:
                return new RuleContainer<Member>(new NoteDiversityRule(ruleView.getValue()), RuleName.DIVERSITY, weight);

            case INTERVAL:
                return new RuleContainer<Member>(new IntervalRangeRule(ruleView.getValue()), RuleName.INTERVAL, weight);

            case EVEN_RHYTHM:
                return new RuleContainer<Member>(new EvenRhythmRule(), RuleName.EVEN_RHYTHM, weight);

            case MIN_NOTE:
                return new RuleContainer<Member>(new MinimumNoteRule(ruleView.getValue()), RuleName.MIN_NOTE, weight);

            default:
                throw new RuntimeException("Unhandled type : " + ruleName);
        }
    }

    public void createRules() {
        ruleManager.create();
    }

    public void attachRulesTo(JPanel slidersContainer) {
        ruleManager.attachTo(slidersContainer);
    }

    public RuleView getRuleView(RuleName ruleName) {
        return ruleManager.get(ruleName);
    }

    public void clearRuleWeights(JPanel parentPanel) {
        ruleWeightManager.clear(parentPanel);
    }

    public void createRuleWeights() {
        ruleWeightManager.create(ruleManager);
    }

    public void attachRuleWeightsTo(JPanel slidersContainer) {
        ruleWeightManager.attachTo(slidersContainer);
    }
}
