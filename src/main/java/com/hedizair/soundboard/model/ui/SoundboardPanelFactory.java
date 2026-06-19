package com.hedizair.soundboard.model.ui;

import java.util.HashMap;

import com.hedizair.soundboard.model.core.Category;
import com.hedizair.soundboard.model.core.Sound;
import com.hedizair.soundboard.model.core.SoundboardConfig;

public class SoundboardPanelFactory {

    private NavigationController navigationController;
    private AudioPlayer audioPlayer;

    public SoundboardPanelFactory(NavigationController navigationController, AudioPlayer audioPlayer) {
        this.navigationController = navigationController;
        this.audioPlayer = audioPlayer;
    }

    private SoundboardPanel recursiveBuild(SoundboardPanel panel, Category category) {

        category.getSoundComponents().forEach(soundComponent -> {
            if (soundComponent instanceof Category) {
                Category subCategory = (Category) soundComponent;
                SoundboardPanel subSoundboardPanel = new SoundboardPanel(subCategory.getName());
                SoundboardPanel finalPanel = recursiveBuild(subSoundboardPanel, subCategory);
                CategoryButton catButton = new CategoryButton(subCategory.getName(), finalPanel, navigationController);
                panel.addSoundboardComponent(catButton);

            } else {
                Sound sound = (Sound) soundComponent;
                SoundButton soundButton = new SoundButton(sound.getName(), sound.getFilePath(), audioPlayer);
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
            CategoryButton catButton = new CategoryButton(category.getName(), finalCategoryPanel, navigationController);
            rootPanel.addSoundboardComponent(catButton);
        });
        return rootPanel;
    }

}
