package com.hedizair.soundboard.model.ui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

import java.awt.BorderLayout;
import java.awt.Color;

public class BreadCrumbPanel extends JPanel implements NavigationListener {

    private JLabel currentLabel;
    private NavigationController navigationController;

    public BreadCrumbPanel(NavigationController navigationController) {
        super(new BorderLayout());

        this.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(5, 10, 5, 0)));

        this.navigationController = navigationController;
        this.currentLabel = new JLabel("root > ");
        this.add(currentLabel, BorderLayout.WEST);

        navigationController.addListener(this);

    }

    private void updateLabel() {
        String finalLabel = "";
        for (SoundboardPanel stack : this.navigationController.getNavigationStack()) {
            finalLabel += stack.getCategoryName() + " > ";
        }
        this.currentLabel.setText(finalLabel);         

    }

    @Override
    public void onNavigate() {
        updateLabel();
    }
}
