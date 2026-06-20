package com.hedizair.soundboard.model.core;

public class Icon {
    private String path;
    private String identifier;

    public Icon(String identifier, String iconPath) {
        this.path = iconPath;
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getPath() {
        return this.path;
    }
}
