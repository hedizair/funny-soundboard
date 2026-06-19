package com.hedizair.soundboard;

import java.util.List;

import com.hedizair.soundboard.model.core.Category;
import com.hedizair.soundboard.model.core.SoundComponent;
import com.hedizair.soundboard.model.core.SoundboardConfig;
import com.hedizair.soundboard.model.ui.MainWindow;
import com.hedizair.soundboard.model.ui.SoundboardPanel;
import com.hedizair.soundboard.model.ui.SoundboardPanelFactory;
import com.hedizair.soundboard.model.ui.WindowConfig;
import com.hedizair.soundboard.model.util.ResourceScanner;

public class AppInitializer {
    public AppInitializer() {
    }

    public void initialize() {
        ResourceScanner resourceScanner = new ResourceScanner();

        List<Category> categories = resourceScanner.scan();

        SoundboardConfig soundboardConfig = new SoundboardConfig(categories);

        SoundboardPanelFactory soundboardPanelFactory = new SoundboardPanelFactory();

        SoundboardPanel rootPanel = soundboardPanelFactory.build(soundboardConfig);

        WindowConfig windowConfig = new WindowConfig();

        MainWindow mainWindow = new MainWindow(windowConfig, rootPanel);

        mainWindow.launch();
    }

}
