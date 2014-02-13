package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MainFrame implements ButtonController {

    private final JFrame frame;

    private OnClickListener onOpen;
    private OnClickListener onAnalise;
    private JButton openButton;
    private JButton analiseButton;

    MainFrame(JFrame frame) {
        this.frame = frame;
    }

    public static MainFrame newInstance() {
        MainFrame mainFrame = new MainFrame(new JFrame());
        mainFrame.initFrame();
        return mainFrame;
    }

    private void initFrame() {
        frame.setTitle("My frame");
        frame.setSize(300, 300);
        frame.setLocation(10, 200);
        frame.addWindowListener(closeWindow);
        initSubPanes();
        frame.setVisible(true);
    }

    private void initSubPanes() {
        openButton = createButton("Choose a MIDI file", internalOnOpen);
        analiseButton = createButton("Analise", internalOnAnalise);
        analiseButton.setEnabled(false);
        frame.add(addToPanel(new JPanel(), openButton, analiseButton));
    }

    private JButton createButton(String title, ActionListener internalListener) {
        JButton button = new JButton(title);
        button.addActionListener(internalListener);
        return button;
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

    private final WindowAdapter closeWindow = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            System.exit(0);
        }
    };

    public void setOpenMidiListener(OnClickListener listener) {
        this.onOpen = listener;
    }

    public void setAnaliseListener(OnClickListener listener) {
        this.onAnalise = listener;
    }

    @Override
    public void enableAnalise(boolean enabled) {
        this.analiseButton.setEnabled(enabled);
    }
}
