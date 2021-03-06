package com.iacovelli.nicola.eatbasta.room.dbAsyncTask;

import android.os.AsyncTask;

import com.iacovelli.nicola.eatbasta.dao.ProductDao;

public class UpdateProductTask extends AsyncTask<Integer, Void, Void> {
    private ProductDao taskDao;

    public UpdateProductTask(ProductDao productDao) {
        taskDao = productDao;
    }


    @Override
    protected Void doInBackground(Integer... ids) {
        taskDao.updateQuantity(ids[0], ids[1]);
        return null;
    }
}
