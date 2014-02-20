package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AlgorithmTabManager extends TabManager {

    private static final String TAB_TITLE = "Algorithm";

    AlgorithmTabManager(JTabbedPane tabbedPane) {
        super(tabbedPane);
    }

    @Override
    public JTabbedPane create() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JSlider fooSlider = new JSlider(JSlider.HORIZONTAL);
        JSlider barSlider = new JSlider(JSlider.HORIZONTAL);
        JLabel fooLabel = new JLabel("Foo");
        JLabel barLabel = new JLabel("Bar");
        JButton startButton = createButton("Start");
        panel.add(fooLabel);
        panel.add(fooSlider);
        panel.add(barLabel);
        panel.add(barSlider);
        panel.add(startButton);
        panel.setPreferredSize(new Dimension(300, 210));
        panel.setBorder(new EmptyBorder(25, 20, 0, 20));
        return createTabbedPane(TAB_TITLE, panel);
    }

}
