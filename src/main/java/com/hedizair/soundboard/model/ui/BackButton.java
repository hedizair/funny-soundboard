package com.hedizair.soundboard.model.ui;

import javax.swing.JButton;

public class BackButton extends JButton {

    public BackButton(NavigationController navigationController) {
        super("Back");
        this.addActionListener(e -> navigationController.back());
    }
}
