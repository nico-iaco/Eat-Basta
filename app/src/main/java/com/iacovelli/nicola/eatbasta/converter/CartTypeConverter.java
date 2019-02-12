package com.iacovelli.nicola.eatbasta.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iacovelli.nicola.eatbasta.model.Product;

import java.lang.reflect.Type;

import androidx.room.TypeConverter;

public class CartTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static Product stringToCart(String data) {
        if (data == null) {
            return null;
        }
        Type type = new TypeToken<Product>() {
        }.getType();
        return gson.fromJson(data, type);
    }

    @TypeConverter
    public static String cartToString(Product p) {
        return gson.toJson(p);
    }

}
