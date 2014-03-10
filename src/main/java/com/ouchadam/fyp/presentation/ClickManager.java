package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ClickManager {

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
