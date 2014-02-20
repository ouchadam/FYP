package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AlgorithmTabManager extends TabManager {

    private static final String TAB_TITLE = "Algorithm";
    private JButton startButton;
    private JTextArea textArea;

    AlgorithmTabManager(JTabbedPane tabbedPane) {
        super(tabbedPane);
    }

    @Override
    public JTabbedPane create() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JPanel slidersContainer = new JPanel(new GridLayout(3, 2));
        initSliders(slidersContainer);
        textArea = new JTextArea("Dummy text");
        startButton = createButton("Start");
        panel.add(slidersContainer);
        panel.add(startButton);
        panel.add(textArea);
        panel.setPreferredSize(new Dimension(300, 210));
        panel.setBorder(new EmptyBorder(25, 20, 0, 20));
        return createTabbedPane(TAB_TITLE, panel);
    }

    private void initSliders(JPanel panel) {
        JSlider fooSlider = new JSlider(JSlider.HORIZONTAL);
        JSlider barSlider = new JSlider(JSlider.HORIZONTAL);
        JLabel fooLabel = new JLabel("Foo");
        JLabel barLabel = new JLabel("Bar");
        panel.add(fooLabel);
        panel.add(fooSlider);
        panel.add(barLabel);
        panel.add(barSlider);
    }

    void setStartListener(OnClickListener listener) {
        setClickListener(startButton, listener);
    }

    void updateText(String text) {
        System.out.println(text);
        textArea.setText(text);
    }

}
