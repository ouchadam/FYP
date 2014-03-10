package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class TabCreator {

    private final JTabbedPane tabbedPane;

    protected TabCreator(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public abstract JTabbedPane create();

    protected JTabbedPane createTabbedPane(String tabTitle, Component... components) {
        tabbedPane.add(tabTitle, addToPanel(new JPanel(), components));
        return tabbedPane;
    }

    private JPanel addToPanel(JPanel panel, Component... components) {
        for (Component component : components) {
            panel.add(component);
        }
        return panel;
    }


    protected JButton createButton(String title) {
        JButton button = new JButton();
        button.setText(title);
        return button;
    }

}
