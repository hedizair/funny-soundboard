package com.hedizair.soundboard.model.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

public class MainWindow extends JFrame {
    private JPanel contentContainer;
    private SoundboardPanel currentPanel;

    public MainWindow(WindowConfig config, SoundboardPanel rootPanel, NavigationController navigationController) {
        this.setTitle("Funny Soundboard");

        this.setSize(config.getSizeX(), config.getSizeY());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.contentContainer = new JPanel(new BorderLayout());
        this.contentContainer.add(rootPanel, BorderLayout.CENTER);
        this.setContentPane(contentContainer);
        this.currentPanel = rootPanel;

        BackButton backButton = new BackButton(navigationController);
        this.contentContainer.add(backButton, BorderLayout.WEST);


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
