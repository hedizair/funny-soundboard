package com.hedizair.soundboard.model.core;

public class Sound implements SoundComponent {
    private String identifier;
    private String name;
    private String filePath;
    private String iconPath;

    public Sound(String identifier, String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
        this.identifier = identifier;

    }

    @Override
    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
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
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public void setIconPath(String path) {
        this.iconPath = path;

    }
}
