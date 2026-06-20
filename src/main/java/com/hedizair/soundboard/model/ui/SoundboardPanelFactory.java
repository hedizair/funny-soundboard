package com.hedizair.soundboard.model.ui;

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
                SoundboardPanel subSoundboardPanel = new SoundboardPanel(subCategory.getName(), audioPlayer);
                SoundboardPanel finalPanel = recursiveBuild(subSoundboardPanel, subCategory);
                CategoryButton catButton = new CategoryButton(subCategory.getName(), subCategory.getIconPath(),
                        finalPanel, navigationController);
                panel.addSoundboardComponent(catButton);

            } else {
                Sound sound = (Sound) soundComponent;
                SoundButton soundButton = new SoundButton(sound.getName(), sound.getFilePath(), sound.getIconPath(),
                        audioPlayer, panel);
                panel.addSoundboardComponent(soundButton);

            }
        });

        return panel;
    }

    public SoundboardPanel build(SoundboardConfig soundboardConfig) {
        SoundboardPanel rootPanel = new SoundboardPanel("root", audioPlayer);
        soundboardConfig.getCategories().forEach(category -> {
            SoundboardPanel categoryPanel = new SoundboardPanel(category.getName(), audioPlayer);
            SoundboardPanel finalCategoryPanel = recursiveBuild(categoryPanel, category);
            CategoryButton catButton = new CategoryButton(category.getName(), category.getIconPath(),
                    finalCategoryPanel,
                    navigationController);
            rootPanel.addSoundboardComponent(catButton);
        });
        return rootPanel;
    }

}
