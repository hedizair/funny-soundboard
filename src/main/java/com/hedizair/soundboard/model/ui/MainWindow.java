package com.hedizair.soundboard.model.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
    private static final int sizeX = 1000;
    private static final int sizeY = 700;
    private JPanel contentContainer;
    private SoundboardPanel currentPanel;

    public MainWindow(SoundboardPanel rootPanel, NavigationController navigationController,
            AudioPlayer audioPlayer) {
        this.setTitle("Funny Soundboard");

        this.setSize(sizeX, sizeY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.contentContainer = new JPanel(new BorderLayout());

        // Soundboard buttons Panel
        this.contentContainer.add(rootPanel, BorderLayout.CENTER);
        this.currentPanel = rootPanel;

        // Back Button Panel
        JPanel backButtonPanel = new BackButtonPanel(navigationController);
        this.contentContainer.add(backButtonPanel, BorderLayout.WEST);

        // Action Panel
        JPanel actionPannel = new SoundActionPanel(audioPlayer, this.currentPanel);
        this.contentContainer.add(actionPannel, BorderLayout.SOUTH);

        this.setContentPane(contentContainer);

    }

    public void launch() {
        this.setVisible(true);
    }

    public void changePanel(SoundboardPanel newPanel) {
        this.contentContainer.remove(this.currentPanel);
        this.contentContainer.add(newPanel, BorderLayout.CENTER);
        this.currentPanel = newPanel;
        this.revalidate();
        this.repaint();
    }

    public SoundboardPanel getCurrentPanel() {
        return currentPanel;
    }
}
