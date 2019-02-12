package com.iacovelli.nicola.eatbasta.model;

import com.iacovelli.nicola.eatbasta.converter.CartTypeConverter;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "cart")
public class Cart {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    @TypeConverters(CartTypeConverter.class)
    private Product product;

    public Cart(@NonNull Product product) {
        this.product = product;
    }

    @NonNull
    public Product getProduct() {
        return product;
    }

    public void setProduct(@NonNull Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
