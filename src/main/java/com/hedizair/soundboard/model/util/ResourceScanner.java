package com.hedizair.soundboard.model.util;

import java.io.File;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hedizair.soundboard.model.core.Category;
import com.hedizair.soundboard.model.core.Icon;
import com.hedizair.soundboard.model.core.Sound;

public class ResourceScanner {

    public ResourceScanner() {

    }

    private List<Icon> recursiveScanIconFiles(File dir, File iconDir) {
        List<Icon> icons = new ArrayList<>();

        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                List<Icon> subPaths = recursiveScanIconFiles(file, iconDir);
                icons = Stream.concat(icons.stream(), subPaths.stream())
                        .collect(Collectors.toList());
            } else if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) {
                String relativePath = iconDir.toURI().relativize(file.toURI()).getPath();
                String identifier = relativePath.replace(".jpg", "").replace(".png", "");
                icons.add(new Icon(identifier, "icons/" + relativePath));
            }
        }
        return icons;
    }

    private List<Icon> scanIcons() {
        List<Icon> icons = new ArrayList<>();

        try {
            URL url = getClass().getClassLoader().getResource("icons");
            File iconsDir = new File(url.toURI());
            icons = recursiveScanIconFiles(iconsDir, iconsDir);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return icons;
    }

    private Category recursiveScanSoundFiles(File dir, File soundsDir) {
        Category category = new Category(dir.getName(), new ArrayList<>());
        File[] files = dir.listFiles();

        for (File file : files) {
            String relativePath = soundsDir.toURI().relativize(file.toURI()).getPath();

            if (file.isDirectory()) {
                Category subCategory = recursiveScanSoundFiles(file, soundsDir);
                subCategory.setIdentifier((relativePath.substring(0, relativePath.length() - 1)));
                category.addSoundComponent(subCategory);
            } else if (file.getName().endsWith(".wav")) {
                String identifier = relativePath.replace(".wav", "");
                category.addSoundComponent(
                        new Sound(identifier, file.getName(),
                                "sounds/" + relativePath));
            }
        }
        return category;
    }

    private List<Category> scanSounds() {

        List<Icon> icons = scanIcons();

        List<Category> categories = new ArrayList<>();
        try {
            URL url = getClass().getClassLoader().getResource("sounds");
            File soundsDir = new File(url.toURI());
            File[] files = soundsDir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    Category category = recursiveScanSoundFiles(file, soundsDir);
                    String relativePath = soundsDir.toURI().relativize(file.toURI()).getPath();
                    category.setIdentifier((relativePath.substring(0, relativePath.length() - 1)));
                    categories.add(category);
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        concatIconToSound(categories, icons);
        return categories;
    }


    private void concatIconToSound(List<Category> cats, List<Icon> icons) {
        Map<String, Icon> iconsByIdentifier = icons.stream()
                .collect(Collectors.toMap(Icon::getIdentifier, icon -> icon));

        cats.forEach(cat -> recursiveConcatIconToSound(cat, iconsByIdentifier));
    }

    private void recursiveConcatIconToSound(Category cat, Map<String, Icon> iconsByIdentifier) {
        Icon matchingIcon = iconsByIdentifier.get(cat.getIdentifier());
        if (matchingIcon != null) {
            cat.setIconPath(matchingIcon.getPath());
        }

        cat.getSoundComponents().forEach(component -> {
            Icon icon = iconsByIdentifier.get(component.getIdentifier());
            if (icon != null) {
                component.setIconPath(icon.getPath());
            }
            if (component instanceof Category) {
                recursiveConcatIconToSound((Category) component, iconsByIdentifier);
            }
        });
    }

    public List<Category> scan() {
        List<Category> categories = scanSounds();
        // Implementation for scanning resources
        return categories;
    }

}
