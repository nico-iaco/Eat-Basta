package com.iacovelli.nicola.eatbasta.dao;

import com.iacovelli.nicola.eatbasta.model.Cart;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CartDao {

    @Insert
    void insert(Cart c);

    @Query("DELETE FROM cart")
    void deleteAll();

    @Query("SELECT * FROM cart")
    LiveData<List<Cart>> getAllCart();

    @Query("DELETE FROM cart WHERE id=:id")
    void deleteProductFromCart(int id);

}
