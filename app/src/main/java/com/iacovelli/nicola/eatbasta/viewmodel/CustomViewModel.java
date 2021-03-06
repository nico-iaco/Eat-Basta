package com.iacovelli.nicola.eatbasta.viewmodel;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.iacovelli.nicola.eatbasta.model.Cart;
import com.iacovelli.nicola.eatbasta.model.Product;
import com.iacovelli.nicola.eatbasta.model.Restaurant;
import com.iacovelli.nicola.eatbasta.repo.AppRepo;
import com.iacovelli.nicola.eatbasta.request.ApiRequest;
import com.iacovelli.nicola.eatbasta.utility.JsonParserUtility;

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
        ApiRequest.getInstance(getApplication().getApplicationContext()).request(Request.Method.GET, "restaurants/", response -> {
            restaurants.setValue(JsonParserUtility.jsonToRestaurantList(response));
        }, error -> {
            Log.d("Errore: ", error.getMessage());
        });
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

    public void clearCart() {
        repo.clearCart();
    }
}
