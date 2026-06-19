package com.hedizair.soundboard.model.ui;

import java.util.HashMap;

import com.hedizair.soundboard.model.core.Category;
import com.hedizair.soundboard.model.core.Sound;
import com.hedizair.soundboard.model.core.SoundboardConfig;

public class SoundboardPanelFactory {
    public SoundboardPanelFactory() {

    }

    private SoundboardPanel recursiveBuild(SoundboardPanel panel, Category category) {

        category.getSoundComponents().forEach(soundComponent -> {
            if (soundComponent instanceof Category) {
                Category subCategory = (Category) soundComponent;
                SoundboardPanel subSoundboardPanel = new SoundboardPanel(subCategory.getName());
                SoundboardPanel finalPanel = recursiveBuild(subSoundboardPanel, subCategory);
                CategoryButton catButton = new CategoryButton(subCategory.getName(), finalPanel);
                panel.addSoundboardComponent(catButton);

            } else {
                Sound sound = (Sound) soundComponent;
                SoundButton soundButton = new SoundButton(sound.getName(), sound.getFilePath());
                panel.addSoundboardComponent(soundButton);

            }
        });

        return panel;
    }

    public SoundboardPanel build(SoundboardConfig soundboardConfig) {
        SoundboardPanel rootPanel = new SoundboardPanel("root");
        soundboardConfig.getCategories().forEach(category -> {
            SoundboardPanel categoryPanel = new SoundboardPanel(category.getName());
            SoundboardPanel finalCategoryPanel = recursiveBuild(categoryPanel, category);
            CategoryButton catButton = new CategoryButton(category.getName(), finalCategoryPanel);
            rootPanel.addSoundboardComponent(catButton);
        });
        return rootPanel;
    }

}
