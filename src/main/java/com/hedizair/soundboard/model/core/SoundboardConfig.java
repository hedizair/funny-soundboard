package com.hedizair.soundboard.model.core;

import java.util.List;

public class SoundboardConfig {
    private List<Category> categories;

    public SoundboardConfig(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
