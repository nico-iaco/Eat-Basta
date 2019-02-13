package com.iacovelli.nicola.eatbasta.model;

import androidx.annotation.NonNull;

public class Restaurant {
    private String name;
    private String urlImage;
    private String address;
    private double minOrder;

    public Restaurant(String name, String address, String url, double minOrder) {
        this.name = name;
        this.address = address;
        this.urlImage = url;
        this.minOrder = minOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "Nome: " + name + "||Descrizione: " + address + "||Ordine minimo: " + minOrder;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
