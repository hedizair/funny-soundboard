package com.hedizair.soundboard;

import java.util.List;

import com.hedizair.soundboard.model.core.Category;
import com.hedizair.soundboard.model.core.SoundboardConfig;
import com.hedizair.soundboard.model.ui.AudioPlayer;
import com.hedizair.soundboard.model.ui.MainWindow;
import com.hedizair.soundboard.model.ui.NavigationController;
import com.hedizair.soundboard.model.ui.SoundboardPanel;
import com.hedizair.soundboard.model.ui.SoundboardPanelFactory;
import com.hedizair.soundboard.model.util.ResourceScanner;

public class AppInitializer {
    public AppInitializer() {
    }

    public void initialize() {
        ResourceScanner resourceScanner = new ResourceScanner();

        List<Category> categories = resourceScanner.scan();

        SoundboardConfig soundboardConfig = new SoundboardConfig(categories);

        NavigationController navigationController = new NavigationController();

        AudioPlayer audioPlayer = new AudioPlayer();

        SoundboardPanelFactory soundboardPanelFactory = new SoundboardPanelFactory(navigationController, audioPlayer);
        SoundboardPanel rootPanel = soundboardPanelFactory.build(soundboardConfig);

        MainWindow mainWindow = new MainWindow(rootPanel, navigationController, audioPlayer);
        navigationController.setwindow(mainWindow);

        mainWindow.launch();
    }

}
