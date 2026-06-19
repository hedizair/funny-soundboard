package com.hedizair.soundboard.model.ui;

import javax.swing.JButton;

public class BackButton extends JButton {
    private NavigationController navigationController;

    public BackButton(NavigationController navigationController) {
        super("Back");
        this.navigationController = navigationController;
        this.addActionListener(e -> navigationController.back());
    }
}
