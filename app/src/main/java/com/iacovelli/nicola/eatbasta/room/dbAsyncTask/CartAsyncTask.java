package com.iacovelli.nicola.eatbasta.room.dbAsyncTask;

import android.os.AsyncTask;

import com.iacovelli.nicola.eatbasta.dao.CartDao;
import com.iacovelli.nicola.eatbasta.enumeration.OperationType;
import com.iacovelli.nicola.eatbasta.model.Cart;
import com.iacovelli.nicola.eatbasta.model.Product;

public class CartAsyncTask extends AsyncTask<Void, Void, Void> {

    private CartDao cartDao;
    private OperationType operationType;
    private Product p;
    private int id;

    public CartAsyncTask(CartDao cartDao, OperationType o) {
        this.cartDao = cartDao;
        operationType = o;
    }

    public CartAsyncTask(CartDao cartDao, OperationType o, Product p) {
        this(cartDao, o);
        this.p = p;
    }

    public CartAsyncTask(CartDao cartDao, OperationType o, int id) {
        this(cartDao, o);
        this.id = id;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        switch (operationType) {
            case INSERT:
                cartDao.insert(new Cart(p));
                break;
            case DELETE:
                cartDao.deleteProductFromCart(id);
                break;
            case DELETEALL:
                cartDao.deleteAll();
                break;
        }
        return null;
    }
}
