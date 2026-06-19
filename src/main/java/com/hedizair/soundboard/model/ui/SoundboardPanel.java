package com.hedizair.soundboard.model.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class SoundboardPanel extends JPanel {
    private String categoryName;
    private List<SoundboardComponent> soundBoardComponents;

    public SoundboardPanel(String categoryName) {
        this.categoryName = categoryName;
        this.soundBoardComponents = new ArrayList<>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<SoundboardComponent> getSoundBoardComponents() {
        return soundBoardComponents;
    }

    public void addSoundboardComponent(SoundboardComponent component) {
        soundBoardComponents.add(component);
        this.add((JComponent) component);
    }

}
