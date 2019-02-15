package com.iacovelli.nicola.eatbasta.utility;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iacovelli.nicola.eatbasta.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class JsonParserUtility {

    public static List<Restaurant> jsonToRestaurantList(String json) {
        JsonObject root = new JsonParser().parse(json).getAsJsonObject();
        JsonArray jsonArray = root.get("restaurants").getAsJsonArray();
        List<Restaurant> restaurants = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            JsonObject object = element.getAsJsonObject();
            Restaurant r = new Restaurant(object.get("name").getAsString(), object.get("address").getAsString(), object.get("img_url").getAsString(), object.get("min_order").getAsDouble());
            restaurants.add(r);
        }
        Log.d("Response: ", restaurants.toString());
        return restaurants;
    }
}
