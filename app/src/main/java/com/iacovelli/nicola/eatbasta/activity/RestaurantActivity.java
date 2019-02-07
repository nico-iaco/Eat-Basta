package com.iacovelli.nicola.eatbasta.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.adapter.RestaurantAdapter;
import com.iacovelli.nicola.eatbasta.viewmodel.CustomViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantActivity extends AppCompatActivity {

    RecyclerView restaurantList;
    RestaurantAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SharedPreferences preferences;
    boolean isActuallyAGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        restaurantList = findViewById(R.id.restaurant_list);
        preferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        isActuallyAGrid = preferences.getBoolean("restaurantGridLayout", false);
        CustomViewModel model = ViewModelProviders.of(this).get(CustomViewModel.class);
        model.getRestaurants().observe(this, r -> {
            adapter = new RestaurantAdapter(r);
            adapter.setGridMode(isActuallyAGrid);
            if (adapter.isGridMode()) {
                layoutManager = new GridLayoutManager(this, 2);
            } else {
                layoutManager = new LinearLayoutManager(this);
            }
            restaurantList.setAdapter(adapter);
            restaurantList.setLayoutManager(layoutManager);
        });

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
        if (item.getItemId() == R.id.logout_menu) {
            FirebaseAuth.getInstance().signOut();
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
