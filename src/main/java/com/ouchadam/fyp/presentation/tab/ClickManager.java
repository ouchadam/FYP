package com.ouchadam.fyp.presentation.tab;

import com.ouchadam.fyp.presentation.OnClickListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickManager {

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
