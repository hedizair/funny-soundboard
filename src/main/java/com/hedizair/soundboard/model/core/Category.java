package com.hedizair.soundboard.model.core;

import java.util.List;

// Category : Animals, Memes, Basic
public class Category implements SoundComponent {
    private String name;
    private List<SoundComponent> soundComponents;


    public Category(String name, List<SoundComponent> soundComponents) {
        this.name = name;
        this.soundComponents = soundComponents;
    }

    public String getName() {
        return name;
    }

    public List<SoundComponent> getSoundComponents() {

        return soundComponents;
    }

    public void addSoundComponent(SoundComponent soundComponent) {
        this.soundComponents.add(soundComponent);
    }
}
