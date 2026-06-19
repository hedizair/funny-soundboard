package com.hedizair.soundboard.model.ui;

import javax.swing.JButton;

public class SoundButton extends JButton implements SoundboardComponent {
    private String soundName;
    private String soundFilePath;

    public SoundButton(String soundName, String soundFilePath) {
        super(soundName);
        this.soundName = soundName;
        this.soundFilePath = soundFilePath;
    }

    public String getSoundName() {
        return soundName;
    }

    public String getSoundFilePath() {
        return soundFilePath;
    }

    public void playSound() {
        // Implement sound playing logic here
    }
    
}
