package com.iacovelli.nicola.eatbasta.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.iacovelli.nicola.eatbasta.model.Restaurant;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progress;
    TextView totalTxt;
    Button checkoutBtn;
    Restaurant r;

    ArrayList<Product> productList = new ArrayList<>(Arrays.asList(new Product("Iphone", 15.5f), new Product("Mammt", 18.4f)));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        r = (Restaurant) b.getSerializable("Ristorante");
        Log.d("Ristorante: ", r.toString());
        recyclerView = findViewById(R.id.product_list);
        progress = findViewById(R.id.checkout_bar);
        progress.setIndeterminate(false);
        progress.setMax((int) Math.round(r.getMinOrder()));
        progress.setProgress(0);
        totalTxt = findViewById(R.id.total_text);
        checkoutBtn = findViewById(R.id.checkout_button);
        ProductAdapter productAdapter = new ProductAdapter(productList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkProducts();
            }
        });
        recyclerView.setAdapter(productAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
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


    @TargetApi(Build.VERSION_CODES.N)
    private void checkProducts() {
        double total = 0;
        for (Product p : productList) {
            total += (p.getProductPrice() * p.getProductQuantity());
        }
        progress.setProgress((int) Math.round(total), true);
        DecimalFormat df = new DecimalFormat("###.##");
        String totalString = df.format(total);
        totalTxt.setText(totalString);
        if (total >= r.getMinOrder() && !checkoutBtn.isEnabled()) {
            checkoutBtn.setEnabled(true);
        } else if (total < r.getMinOrder() && checkoutBtn.isEnabled()) {
            checkoutBtn.setEnabled(false);
        }

    }

}
