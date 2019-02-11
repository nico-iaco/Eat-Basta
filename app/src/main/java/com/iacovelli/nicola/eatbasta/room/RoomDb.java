package com.iacovelli.nicola.eatbasta.room;

import android.content.Context;
import android.os.AsyncTask;

import com.iacovelli.nicola.eatbasta.dao.ProductDao;
import com.iacovelli.nicola.eatbasta.model.Product;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class RoomDb extends RoomDatabase {

    public abstract ProductDao productDao();

    private static volatile RoomDb instance;

    public static RoomDb getDb(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), RoomDb.class, "app_db").addCallback(callback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDb(instance).execute();
        }


    };

    private static class PopulateDb extends AsyncTask<Void, Void, Void> {

        private final ProductDao dao;

        PopulateDb(RoomDb db) {
            dao = db.productDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            dao.insert(new Product("Iphone", 15.5f));
            dao.insert(new Product("Mammt", 18.4f));
            dao.insert(new Product("Onion", 1));
            dao.insert(new Product("Lucidamario", 6.2f));
            dao.insert(new Product("Mela", 0.4f));
            dao.insert(new Product("Pera", 1.4f));
            return null;
        }
    }

}
