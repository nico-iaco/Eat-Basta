package com.iacovelli.nicola.eatbasta.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.adapter.CartAdapter;
import com.iacovelli.nicola.eatbasta.model.Cart;
import com.iacovelli.nicola.eatbasta.viewmodel.CustomViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CheckoutActivity extends AppCompatActivity {

    RecyclerView cartList;
    TextView totalCheckout;
    Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        cartList = findViewById(R.id.checkout_list);
        totalCheckout = findViewById(R.id.total_checkout);
        payButton = findViewById(R.id.pay_checkout);
        cartList.setLayoutManager(new LinearLayoutManager(this));
        CustomViewModel model = ViewModelProviders.of(this).get(CustomViewModel.class);
        CartAdapter adapter = new CartAdapter(null);
        adapter.setListener(model::removeProductFromCart);
        cartList.setAdapter(adapter);
        model.getCart().observe(this, (carts -> {
            adapter.setData(carts);
            float tot = 0;
            for (Cart c : carts) {
                tot += (c.getProduct().getProductQuantity() * c.getProduct().getProductPrice());
            }
            totalCheckout.setText(String.valueOf(tot));
            if (tot < 40.5 && payButton.isEnabled()) {
                payButton.setEnabled(false);
            }
        }));

    }
}
