package com.iacovelli.nicola.eatbasta.room.dbAsyncTask;

import android.os.AsyncTask;

import com.iacovelli.nicola.eatbasta.dao.ProductDao;
import com.iacovelli.nicola.eatbasta.model.Product;

public class InsertTask extends AsyncTask<Product, Void, Void> {
    private ProductDao taskDao;

    public InsertTask(ProductDao productDao) {
        taskDao = productDao;
    }


    @Override
    protected Void doInBackground(Product... products) {
        taskDao.insert(products[0]);
        return null;
    }
}
