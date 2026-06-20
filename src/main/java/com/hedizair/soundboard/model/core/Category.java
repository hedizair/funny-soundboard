package com.hedizair.soundboard.model.core;

import java.util.List;

// Category : Animals, Memes, Basic
public class Category implements SoundComponent {
    private String name;
    private List<SoundComponent> soundComponents;
    private String identifier;
    private String iconPath;

    public Category(String name, List<SoundComponent> soundComponents) {
        this.name = name;
        this.soundComponents = soundComponents;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getIconPath() {
        return iconPath;
    }

    @Override
    public void setIconPath(String path) {
        this.iconPath = path;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<SoundComponent> getSoundComponents() {
        return soundComponents;
    }

    public void addSoundComponent(SoundComponent soundComponent) {
        this.soundComponents.add(soundComponent);
    }
}
