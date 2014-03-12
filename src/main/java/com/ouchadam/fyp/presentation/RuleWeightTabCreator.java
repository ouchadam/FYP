package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class RuleWeightTabCreator extends TabCreator {

    private static final String TAB_TITLE = "Weighting";
    private final RuleFactory ruleFactory;
    private JPanel parentPanel;

    RuleWeightTabCreator(JTabbedPane tabbedPane, RuleFactory ruleFactory) {
        super(tabbedPane);
        this.ruleFactory = ruleFactory;
    }

    @Override
    public JTabbedPane create() {
        parentPanel = new JPanel();
        parentPanel.setBorder(new EmptyBorder(12, 20, 0, 20));
        addRuleWeights();
        return createTabbedPane(TAB_TITLE, parentPanel);
    }

    public void addRuleWeights() {
        ruleFactory.clearRuleWeights(parentPanel);
        parentPanel.removeAll();
        parentPanel.add(createRuleWeights());
    }

    private Component createRuleWeights() {
        JPanel slidersContainer = new JPanel(new GridLayout(1, 0));
        slidersContainer.setPreferredSize(new Dimension(560, 250));
        ruleFactory.createRuleWeights();
        ruleFactory.attachRuleWeightsTo(slidersContainer);
        return slidersContainer;
    }

}
