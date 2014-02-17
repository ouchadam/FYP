package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AnalyseTabManager extends TabManager {

    private static final String TAB_TITLE = "Analyse";
    private JButton openButton;
    private JButton analyseButton;

    AnalyseTabManager(JTabbedPane tabbedPane) {
        super(tabbedPane);
    }

    JTabbedPane create() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(openButton = createButton("Choose a MIDI file"));
        panel.add(analyseButton = createButton("Analyse..."));
        setAnaliseEnabled(false);
        panel.setPreferredSize(new Dimension(300, 210));
        panel.setBorder(new EmptyBorder(25, 20, 0, 20));
        return createTabbedPane(TAB_TITLE, panel);
    }

    void setOpenMidiListener(OnClickListener listener) {
        setClickListener(openButton, listener);
    }

    void setAnaliseListener(OnClickListener listener) {
        setClickListener(analyseButton, listener);
    }

    public void setAnaliseEnabled(boolean enabled) {
        this.analyseButton.setEnabled(enabled);
    }

    void setAnaliseText(String text) {
        analyseButton.setText(text);
    }

    JButton getOpenMidiButton() {
        return openButton;
    }

    JButton getAnalyseButton() {
        return analyseButton;
    }

}
