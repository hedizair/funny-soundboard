package com.hedizair.soundboard.model.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

public class MainWindow extends JFrame {
    private NavigationController navigationController;
    private JPanel contentContainer;
    private SoundboardPanel currentPanel;

    public MainWindow(WindowConfig config, SoundboardPanel rootPanel) {
        this.setTitle("Funny Soundboard");

        this.setSize(config.getSizeX(), config.getSizeY());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.contentContainer = new JPanel(new BorderLayout());
        this.contentContainer.add(rootPanel, BorderLayout.CENTER);
        this.setContentPane(contentContainer);
        this.currentPanel = rootPanel;


        this.navigationController = new NavigationController(this, rootPanel);

        BackButton backButton = new BackButton(navigationController);
        this.contentContainer.add(backButton, BorderLayout.WEST);


    }

    public void launch() {
        this.setVisible(true);
    }

    public void changePanel(SoundboardPanel newPanel) {
        System.out.println("Changing panel to: " + newPanel.getCategoryName());
        this.contentContainer.remove(this.currentPanel);
        this.contentContainer.add(newPanel, BorderLayout.CENTER);
        this.currentPanel = newPanel;
        this.revalidate();
        this.repaint();
    }
}
