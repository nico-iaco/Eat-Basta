package com.iacovelli.nicola.eatbasta.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.adapter.RestaurantAdapter;
import com.iacovelli.nicola.eatbasta.model.Restaurant;

import java.util.ArrayList;
import java.util.Arrays;

public class RestaurantActivity extends AppCompatActivity {

    RecyclerView restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        restaurantList = findViewById(R.id.restaurant_list);
        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>(Arrays.asList(
                new Restaurant("Panucci's pizza", "The best pizza ever", R.drawable.logo),
                new Restaurant("Panucci's pizza", "The best pizza ever", R.drawable.logo),
                new Restaurant("Panucci's pizza", "The best pizza ever", R.drawable.logo),
                new Restaurant("Panucci's pizza", "The best pizza ever", R.drawable.logo)
        ));
        restaurantList.setAdapter(new RestaurantAdapter(restaurantArrayList));
        restaurantList.addItemDecoration(new DividerItemDecoration(restaurantList.getContext(), DividerItemDecoration.VERTICAL));
        restaurantList.setLayoutManager(new LinearLayoutManager(this));
    }
}
