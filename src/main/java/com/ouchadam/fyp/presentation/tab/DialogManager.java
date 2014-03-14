package com.ouchadam.fyp.presentation.tab;

import javax.swing.*;
import java.awt.*;

public class DialogManager {

    public void showMessageDialog(Component component, String message) {
        JOptionPane.showMessageDialog(component.getParent(), message);
    }

}
