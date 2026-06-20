package com.hedizair.soundboard.model.ui;

public class SoundButton extends SoundboardButton {

    private String soundName;
    private String soundFilePath;

    public SoundButton(String soundName, String soundFilePath, String iconFilePath, AudioPlayer audioPlayer,
            SoundboardPanel panel) {
        super(soundName, iconFilePath);
        this.soundName = soundName;
        this.soundFilePath = soundFilePath;
        this.addActionListener(e -> {
            audioPlayer.play(soundFilePath);
        });

    }

    public String getSoundName() {
        return soundName;
    }

    public String getSoundFilePath() {
        return soundFilePath;
    }
}