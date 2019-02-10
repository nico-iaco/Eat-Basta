package com.iacovelli.nicola.eatbasta.viewmodel;

import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.model.Product;
import com.iacovelli.nicola.eatbasta.model.Restaurant;

import java.util.ArrayList;
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
        List<Restaurant> restaurantArrayList = new ArrayList<>();
        restaurantArrayList.add(new Restaurant("Panucci's pizza", "The best pizza ever", R.drawable.restaurant, 10));
        restaurantArrayList.add(new Restaurant("Calabrese", "Mannaia a tia", R.drawable.restaurant, 15));
        restaurantArrayList.add(new Restaurant("Burger king", "Tutti i miei amici vanno a burger king", R.drawable.restaurant, 20));
        restaurantArrayList.add(new Restaurant("Mensa elis", "Ogni giorno surprise", R.drawable.restaurant, 50));
        restaurants.setValue(restaurantArrayList);
    }

    private void loadProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Iphone", 15.5f));
        productList.add(new Product("Mammt", 18.4f));
        productList.add(new Product("Onion", 1));
        productList.add(new Product("Lucidamario", 6.2f));
        productList.add(new Product("Mela", 0.4f));
        productList.add(new Product("Pera", 1.4f));
        products.setValue(productList);
    }
}
