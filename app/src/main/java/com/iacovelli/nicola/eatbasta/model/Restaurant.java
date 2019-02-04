package com.iacovelli.nicola.eatbasta.model;

import java.io.Serializable;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class Restaurant implements Serializable {
    private String name;
    private @DrawableRes
    int image;
    private String description;
    private double minOrder;

    public Restaurant(String name, String description, @DrawableRes int image, double minOrder) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.minOrder = minOrder;
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

    public double getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(double minOrder) {
        this.minOrder = minOrder;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nome: " + name + "||Descrizione: " + description + "||Ordine minimo: " + minOrder;
    }
}
