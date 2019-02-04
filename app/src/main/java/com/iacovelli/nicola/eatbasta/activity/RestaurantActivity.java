package com.iacovelli.nicola.eatbasta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.adapter.RestaurantAdapter;
import com.iacovelli.nicola.eatbasta.model.Restaurant;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantActivity extends AppCompatActivity {

    RecyclerView restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        restaurantList = findViewById(R.id.restaurant_list);
        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>(Arrays.asList(
                new Restaurant("Panucci's pizza", "The best pizza ever", R.drawable.logo),
                new Restaurant("Calabrese", "Mannaia a tia", R.drawable.logo),
                new Restaurant("Burger king", "Tutti i miei amici vanno a burger king", R.drawable.logo),
                new Restaurant("Mensa elis", "Ogni giorno surprise", R.drawable.logo)
        ));
        restaurantList.setAdapter(new RestaurantAdapter(restaurantArrayList));
        restaurantList.setLayoutManager(new LinearLayoutManager(this));
    }
}
