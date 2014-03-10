package com.ouchadam.fyp.presentation;

import javax.swing.*;
import java.awt.*;

class DialogManager {

    public void showMessageDialog(Component component, String message) {
        JOptionPane.showMessageDialog(component.getParent(), message);
    }

}
