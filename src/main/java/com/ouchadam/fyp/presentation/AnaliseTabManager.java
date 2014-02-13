package com.ouchadam.fyp.presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AnaliseTabManager {

    private final JTabbedPane tabbedPane;
    private OnClickListener onOpen;
    private OnClickListener onAnalise;
    private JButton openButton;
    private JButton analiseButton;

    public AnaliseTabManager(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    JTabbedPane create() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(openButton = createButton("Choose a MIDI file", internalOnOpen));
        panel.add(analiseButton = createButton("Analise...", internalOnAnalise));
        setAnaliseEnabled(false);
        panel.setPreferredSize(new Dimension(300, 210));
        panel.setBorder(new EmptyBorder(25, 20, 0, 20));
        return createTabbedPane("Analise", tabbedPane, panel);
    }

    private JButton createButton(String title, ActionListener internalListener) {
        JButton button = new JButton(title);
        button.addActionListener(internalListener);
        return button;
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

    private final ActionListener internalOnOpen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            onClick(onOpen, openButton);
        }
    };

    private final ActionListener internalOnAnalise = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            onClick(onAnalise, analiseButton);
        }
    };

    private void onClick(OnClickListener listener, JButton button) {
        if (listener != null) {
            listener.onClick(button);
        }
    }

    void setOpenMidiListener(OnClickListener listener) {
        this.onOpen = listener;
    }

    void setAnaliseListener(OnClickListener listener) {
        this.onAnalise = listener;
    }

    public void setAnaliseEnabled(boolean enabled) {
        this.analiseButton.setEnabled(enabled);
    }

    void setAnaliseText(String text) {
        analiseButton.setText(text);
    }

    JButton getOpenMidiButton() {
        return openButton;
    }

    JButton getAnaliseButton() {
        return analiseButton;
    }
}
