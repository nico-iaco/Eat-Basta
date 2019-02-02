package com.iacovelli.nicola.eatbasta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.adapter.ProductAdapter;
import com.iacovelli.nicola.eatbasta.model.Product;

import java.util.ArrayList;
import java.util.Arrays;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progress;
    TextView totalTxt;
    Button checkoutBtn;

    ArrayList<Product> productList = new ArrayList<>(Arrays.asList(new Product("Iphone", 15.5f), new Product("Mammt", 18.4f)));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.product_list);
        progress = findViewById(R.id.checkout_bar);
        totalTxt = findViewById(R.id.total_text);
        checkoutBtn = findViewById(R.id.checkout_button);
        ProductAdapter productAdapter = new ProductAdapter(productList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClick", "Toccato");
                checkProducts();
            }
        });
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout_menu:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
/*
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.product_list:
                checkProducts();
                break;
            default:
                Log.d(getLocalClassName(), "Default");
        }
    }*/


    private void checkProducts() {
        float total = 0;
        int countProducts = 0;
        for (Product p : productList) {
            total += (p.getProductPrice() * p.getProductQuantity());
            countProducts += p.getProductQuantity();
        }
        totalTxt.setText(String.valueOf(total));
        if (countProducts >= 8 && !checkoutBtn.isEnabled()) {
            checkoutBtn.setEnabled(true);
        } else if (countProducts < 8 && checkoutBtn.isEnabled()) {
            checkoutBtn.setEnabled(false);
        }

    }

}
