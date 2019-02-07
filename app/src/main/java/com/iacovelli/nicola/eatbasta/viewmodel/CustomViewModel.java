package com.iacovelli.nicola.eatbasta.viewmodel;

import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.model.Product;
import com.iacovelli.nicola.eatbasta.model.Restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustomViewModel extends ViewModel {
    private MutableLiveData<List<Restaurant>> restaurants;
    private MutableLiveData<List<Product>> products;

    public synchronized LiveData<List<Restaurant>> getRestaurants() {
        if (restaurants == null) {
            restaurants = new MutableLiveData<>();
            loadRestaurants();
        }
        return restaurants;
    }

    public synchronized LiveData<List<Product>> getProducts() {
        if (products == null) {
            products = new MutableLiveData<>();
            loadProducts();
        }
        return products;
    }

    private void loadRestaurants() {
        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>(Arrays.asList(
                new Restaurant("Panucci's pizza", "The best pizza ever", R.drawable.restaurant, 10),
                new Restaurant("Calabrese", "Mannaia a tia", R.drawable.restaurant, 15),
                new Restaurant("Burger king", "Tutti i miei amici vanno a burger king", R.drawable.restaurant, 20),
                new Restaurant("Mensa elis", "Ogni giorno surprise", R.drawable.restaurant, 50)
        ));
        restaurants.setValue(restaurantArrayList);
    }

    private void loadProducts() {
        List<Product> productList = new ArrayList<>(Arrays.asList(
                new Product("Iphone", 15.5f),
                new Product("Mammt", 18.4f)
        ));
        products.setValue(productList);
    }
}
