package com.iacovelli.nicola.eatbasta.model;

import androidx.annotation.DrawableRes;

public class Restaurant {
    private String name;
    private @DrawableRes
    int image;
    private String description;

    public Restaurant(String name, String description, @DrawableRes int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @DrawableRes
    int getImage() {
        return image;
    }

    public void setImage(@DrawableRes int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
