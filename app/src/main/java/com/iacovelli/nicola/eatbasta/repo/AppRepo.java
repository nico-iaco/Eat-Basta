package com.iacovelli.nicola.eatbasta.repo;

import android.app.Application;

import com.iacovelli.nicola.eatbasta.dao.ProductDao;
import com.iacovelli.nicola.eatbasta.model.Product;
import com.iacovelli.nicola.eatbasta.room.RoomDb;
import com.iacovelli.nicola.eatbasta.room.dbAsyncTask.InsertTask;
import com.iacovelli.nicola.eatbasta.room.dbAsyncTask.UpdateTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class AppRepo {

    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    public AppRepo(Application application) {
        RoomDb db = RoomDb.getDb(application);
        productDao = db.productDao();
        allProducts = productDao.getAllProducts();
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public void insert(Product p) {
        new InsertTask(productDao).execute(p);
    }

    public void update(int id, int qt) {
        new UpdateTask(productDao).execute(id, qt);
    }


}
