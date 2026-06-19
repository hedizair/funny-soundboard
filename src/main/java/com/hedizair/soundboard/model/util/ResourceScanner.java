package com.hedizair.soundboard.model.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.hedizair.soundboard.model.core.Category;
import com.hedizair.soundboard.model.core.Sound;

public class ResourceScanner {

    public ResourceScanner() {

    }

    private void scanResources(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                // c'est une catégorie
            } else if (file.getName().endsWith(".wav")) {
                // c'est un son
            }
        }

    }

    private List<String> scanIcons() {
        try {
            URL url = getClass().getClassLoader().getResource("icons");
            File iconsDir = new File(url.toURI());
            File[] files = iconsDir.listFiles();
            scanResources(files);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private Category recursiveScanSoundFiles(File dir, File soundsDir) {
        Category category = new Category(dir.getName(), new ArrayList<>());
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                Category subCategory = recursiveScanSoundFiles(file, soundsDir);
                category.addSoundComponent(subCategory);
            } else if (file.getName().endsWith(".mp3")) {
                category.addSoundComponent(
                        new Sound(file.getName(), "sounds/" + soundsDir.toURI().relativize(file.toURI()).getPath()));
            }
        }
        return category;
    }

    private List<Category> scanSounds() {

        List<String> iconPaths = new ArrayList<>();

        List<Category> categories = new ArrayList<>();
        try {
            URL url = getClass().getClassLoader().getResource("sounds");
            File soundsDir = new File(url.toURI());
            File[] files = soundsDir.listFiles();
            scanResources(files);
            for (File file : files) {
                if (file.isDirectory()) {
                    Category category = recursiveScanSoundFiles(file, soundsDir);
                    categories.add(category);
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // Implementation for scanning sounds
        return categories;
    }

    public List<Category> scan() {
        List<Category> categories = scanSounds();

        // Implementation for scanning resources
        return categories;
    }

}
