package com.iacovelli.nicola.eatbasta.repo;

import android.app.Application;

import com.iacovelli.nicola.eatbasta.dao.CartDao;
import com.iacovelli.nicola.eatbasta.dao.ProductDao;
import com.iacovelli.nicola.eatbasta.enumeration.OperationType;
import com.iacovelli.nicola.eatbasta.model.Cart;
import com.iacovelli.nicola.eatbasta.model.Product;
import com.iacovelli.nicola.eatbasta.room.RoomDb;
import com.iacovelli.nicola.eatbasta.room.dbAsyncTask.CartAsyncTask;
import com.iacovelli.nicola.eatbasta.room.dbAsyncTask.InsertProductTask;
import com.iacovelli.nicola.eatbasta.room.dbAsyncTask.UpdateProductTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class AppRepo {

    private ProductDao productDao;
    private CartDao cartDao;
    private LiveData<List<Product>> allProducts;
    private LiveData<List<Cart>> allCart;

    public AppRepo(Application application) {
        RoomDb db = RoomDb.getDb(application);
        productDao = db.productDao();
        cartDao = db.cartDao();
        allProducts = productDao.getAllProducts();
        allCart = cartDao.getAllCart();
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public LiveData<List<Cart>> getAllCart() {
        return allCart;
    }

    public void insertProduct(Product p) {
        new InsertProductTask(productDao).execute(p);
    }

    public void updateProductQuantity(int id, int qt) {
        new UpdateProductTask(productDao).execute(id, qt);
    }

    public void insertProductIntoCart(Product p) {
        new CartAsyncTask(cartDao, OperationType.INSERT, p).execute();
    }

    public void removeProductFromCart(int id) {
        new CartAsyncTask(cartDao, OperationType.DELETE, id).execute();
    }


}
