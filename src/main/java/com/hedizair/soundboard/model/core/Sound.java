package com.hedizair.soundboard.model.core;

public class Sound implements SoundComponent {
    private String name;
    private String filePath;
    private String imgPath;

    public Sound(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
