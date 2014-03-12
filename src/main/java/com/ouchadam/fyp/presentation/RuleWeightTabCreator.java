package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class RuleWeightTabCreator extends TabCreator {

    private static final String TAB_TITLE = "Weighting";
    private final RuleWeightManager ruleManager;
    private JPanel parentPanel;

    RuleWeightTabCreator(JTabbedPane tabbedPane, RuleWeightManager ruleManager) {
        super(tabbedPane);
        this.ruleManager = ruleManager;
    }

    @Override
    public JTabbedPane create() {
        parentPanel = new JPanel();
        parentPanel.setBorder(new EmptyBorder(12, 20, 0, 20));
        return createTabbedPane(TAB_TITLE, parentPanel);
    }

    public void addRuleWeights() {
        parentPanel.removeAll();
        parentPanel.add(createRuleWeights());
    }

    private Component createRuleWeights() {
        JPanel slidersContainer = new JPanel(new GridLayout(1, 0));
        slidersContainer.setPreferredSize(new Dimension(500, 300));
        ruleManager.create();
        ruleManager.attachTo(slidersContainer);
        return slidersContainer;
    }

}
