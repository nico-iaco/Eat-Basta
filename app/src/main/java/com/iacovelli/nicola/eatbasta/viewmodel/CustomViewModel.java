package com.iacovelli.nicola.eatbasta.viewmodel;

import android.app.Application;

import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.model.Cart;
import com.iacovelli.nicola.eatbasta.model.Product;
import com.iacovelli.nicola.eatbasta.model.Restaurant;
import com.iacovelli.nicola.eatbasta.repo.AppRepo;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CustomViewModel extends AndroidViewModel {
    private AppRepo repo;
    private MutableLiveData<List<Restaurant>> restaurants;
    private LiveData<List<Product>> products;
    private LiveData<List<Cart>> cart;

    public CustomViewModel(Application application) {
        super(application);
        repo = new AppRepo(application);
    }

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

    public synchronized LiveData<List<Cart>> getCart() {
        if (cart == null) {
            cart = new MutableLiveData<>();
            loadCart();
        }
        return cart;
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
        products = repo.getAllProducts();
    }

    public void updateProduct(int id, int qt) {
        repo.updateProductQuantity(id, qt);
    }

    private void loadCart() {
        cart = repo.getAllCart();
    }

    public void insertProductIntoCart(Product p) {
        repo.insertProductIntoCart(p);
    }

    public void removeProductFromCart(int id) {
        repo.removeProductFromCart(id);
    }
}
