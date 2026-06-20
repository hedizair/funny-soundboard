package com.hedizair.soundboard.model.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

public class SoundActionPanel extends JPanel implements AudioPlayerListener {
    private StopButton stopButton;

    public SoundActionPanel(AudioPlayer audioPlayer, SoundboardPanel panel) {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        stopButton = new StopButton();
        stopButton.addActionListener(e -> {
            audioPlayer.stop();
        });

        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridwidth = 5;
        c.weightx = 0.95;
        this.add(stopButton, c);

        VolumeSlider volSlider = new VolumeSlider(0, 10, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 5;
        c.gridwidth = 1;
        c.weightx = 0.05;
        volSlider.addChangeListener(value -> {
            int realVolume = value * 10;
            audioPlayer.setVolume(realVolume);
        });
        this.add(volSlider, c);

        this.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        audioPlayer.addListener(this);
    }

    @Override
    public void onSoundFinished() {
        this.stopButton.setEnabled(false);
    }

    @Override
    public void onSoundStart() {
        this.stopButton.setEnabled(true);
    }
}