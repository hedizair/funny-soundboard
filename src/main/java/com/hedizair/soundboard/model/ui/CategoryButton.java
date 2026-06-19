package com.hedizair.soundboard.model.ui;

import javax.swing.JButton;

public class CategoryButton extends JButton implements SoundboardComponent {
    private String categoryName;
    private SoundboardPanel targetPanel;
    private NavigationController navigationController;

    public CategoryButton(String categoryName, SoundboardPanel targetPanel) {
        super(categoryName);
        this.categoryName = categoryName;
        this.targetPanel = targetPanel;
        this.addActionListener(e -> {
            if (navigationController != null) {
                navigationController.navigate(targetPanel);
            }
        });
    }

    public String getCategoryName() {
        return categoryName;
    }

    public SoundboardPanel getTargetPanel() {
        return targetPanel;
    }

    public void setNavigationController(NavigationController navigationController) {
        this.navigationController = navigationController;
    }

}
