package com.hedizair.soundboard.model.ui;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class SoundboardPanel extends JPanel implements AudioPlayerListener {
    private static final int COLUMNS = 4;

    private String categoryName;
    private JPanel buttonsGrid;
    private List<SoundboardButton> soundBoardComponents;

    public SoundboardPanel(String categoryName, AudioPlayer audioPlayer) {
        super(new GridBagLayout());
        this.categoryName = categoryName;
        this.soundBoardComponents = new ArrayList<>();

        this.buttonsGrid = new JPanel(new GridLayout(0, COLUMNS, 10, 10));
        this.add(buttonsGrid);
        audioPlayer.addListener(this);

    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<SoundboardButton> getSoundBoardComponents() {
        return soundBoardComponents;
    }

    public void addSoundboardComponent(SoundboardButton component) {
        soundBoardComponents.add(component);
        this.buttonsGrid.add((JComponent) component);
    }

    @Override
    public void onSoundFinished() {
        soundBoardComponents.forEach(c -> c.setEnabled(true));

    }

    @Override
    public void onSoundStart() {
        soundBoardComponents.forEach(c -> c.setEnabled(false));

    }
}