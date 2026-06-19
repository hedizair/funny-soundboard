package com.hedizair.soundboard;
import java.util.List;

import javax.swing.*;

import com.hedizair.soundboard.model.core.Category;
import com.hedizair.soundboard.model.core.SoundboardConfig;
import com.hedizair.soundboard.model.ui.MainWindow;
import com.hedizair.soundboard.model.ui.WindowConfig;
import com.hedizair.soundboard.model.util.ResourceScanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello, SoundBox!");
        new AppInitializer().initialize();;
        
    }
}
