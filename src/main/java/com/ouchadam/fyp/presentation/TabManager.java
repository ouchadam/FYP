package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class TabManager {

    private final JTabbedPane tabbedPane;

    protected TabManager(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

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

    public void setClickListener(JButton button, OnClickListener onClickListener) {
        button.addActionListener(wrapClickListener(button, onClickListener));
    }

    private ActionListener wrapClickListener(final JButton button, final OnClickListener listener) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClick(listener, button);
            }
        };
    }

    private void onClick(OnClickListener listener, JButton button) {
        if (listener != null) {
            listener.onClick(button);
        }
    }

}
