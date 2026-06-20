package com.hedizair.soundboard.model.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

public class BackButtonPanel extends JPanel implements NavigationListener {
    private NavigationController navigationController;
    private JButton backButton;

    public BackButtonPanel(NavigationController navigationController) {
        super(new BorderLayout());
        this.navigationController = navigationController;

        // Button
        BackButton backButton = new BackButton();
        backButton.addActionListener(e -> {
            navigationController.back();
        });
        backButton.setEnabled(false);
        this.add(backButton, BorderLayout.NORTH);
        this.backButton = backButton;

        // Panel
        this.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 0, 10)));

        navigationController.addListener(this);

    }

    @Override
    public void onNavigate() {
        if (this.navigationController == null) {
            return;
        }
        if (this.navigationController.isAtRoot()) {
            this.backButton.setEnabled(false);
            return;
        }
        this.backButton.setEnabled(true);
    }
}
