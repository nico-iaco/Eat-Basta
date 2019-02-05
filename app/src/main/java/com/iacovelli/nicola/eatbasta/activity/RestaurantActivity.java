package com.iacovelli.nicola.eatbasta.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.adapter.RestaurantAdapter;
import com.iacovelli.nicola.eatbasta.model.Restaurant;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantActivity extends AppCompatActivity {

    RecyclerView restaurantList;
    RestaurantAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        restaurantList = findViewById(R.id.restaurant_list);

        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>(Arrays.asList(
                new Restaurant("Panucci's pizza", "The best pizza ever", R.drawable.restaurant, 10),
                new Restaurant("Calabrese", "Mannaia a tia", R.drawable.restaurant, 15),
                new Restaurant("Burger king", "Tutti i miei amici vanno a burger king", R.drawable.restaurant, 20),
                new Restaurant("Mensa elis", "Ogni giorno surprise", R.drawable.restaurant, 50)
        ));
        adapter = new RestaurantAdapter(restaurantArrayList);
        preferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        Log.d("package: ", getPackageName());

        adapter.setGridMode(preferences.getBoolean("restaurantGridLayout", false));
        if (adapter.isGridMode()) {
            layoutManager = new GridLayoutManager(this, 2);
        } else {
            layoutManager = new LinearLayoutManager(this);
        }
        restaurantList.setAdapter(adapter);
        restaurantList.setLayoutManager(layoutManager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("restaurantGridLayout", adapter.isGridMode());
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.restaurant_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.view_mode) {
            setLayoutManager();
            return true;
        }
        if (item.getItemId() == R.id.login_menu) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (item.getItemId() == R.id.checkout_menu) {
            startActivity(new Intent(this, CartActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setLayoutManager() {
        layoutManager = adapter.isGridMode() ? new LinearLayoutManager(this) : new GridLayoutManager(this, 2);
        adapter.setGridMode(!adapter.isGridMode());
        restaurantList.setLayoutManager(layoutManager);
        restaurantList.setAdapter(adapter);
    }
}
