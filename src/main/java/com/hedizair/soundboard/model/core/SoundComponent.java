package com.hedizair.soundboard.model.core;

public interface SoundComponent {
    String getName();

    String getIdentifier();

    String getIconPath();

    void setIdentifier(String identifier);

    void setIconPath(String path);

}
