package com.iacovelli.nicola.eatbasta.dao;


import com.iacovelli.nicola.eatbasta.model.Product;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product p);

    @Query("DELETE FROM product_table")
    void deleteAll();

    @Query("SELECT * FROM product_table")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM product_table WHERE id=:user")
    Product getProduct(int user);

    @Query("UPDATE product_table SET productQuantity=:qt WHERE id=:id")
    void updateQuantity(int id, int qt);

}
