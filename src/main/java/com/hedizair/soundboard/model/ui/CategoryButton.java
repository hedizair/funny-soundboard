package com.hedizair.soundboard.model.ui;

import javax.swing.JButton;

public class CategoryButton extends JButton implements SoundboardComponent {
    private String categoryName;
    private SoundboardPanel targetPanel;

    public CategoryButton(String categoryName, SoundboardPanel targetPanel, NavigationController navigationController) {
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


}
