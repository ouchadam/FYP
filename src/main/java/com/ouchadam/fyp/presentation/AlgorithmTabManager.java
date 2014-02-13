package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AlgorithmTabManager {

    private final JTabbedPane tabbedPane;

    AlgorithmTabManager(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    JTabbedPane create() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JSlider fooSlider = new JSlider(JSlider.HORIZONTAL);
        JSlider barSlider = new JSlider(JSlider.HORIZONTAL);
        JLabel fooLabel = new JLabel("Foo");
        JLabel barLabel = new JLabel("Bar");
        JButton startButton = new JButton("Start");
        panel.add(fooLabel);
        panel.add(fooSlider);
        panel.add(barLabel);
        panel.add(barSlider);
        panel.add(startButton);
        panel.setPreferredSize(new Dimension(300, 210));
        panel.setBorder(new EmptyBorder(25, 20, 0, 20));
        return createTabbedPane("Algorithm", tabbedPane, panel);
    }

    private JTabbedPane createTabbedPane(String tile, JTabbedPane pane, Component... components) {
        pane.add(tile, addToPanel(new JPanel(), components));
        return pane;
    }

    private JPanel addToPanel(JPanel panel, Component... components) {
        for (Component component : components) {
            panel.add(component);
        }
        return panel;
    }

}
