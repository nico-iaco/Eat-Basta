package com.iacovelli.nicola.eatbasta.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.adapter.ProductAdapter;
import com.iacovelli.nicola.eatbasta.model.Product;
import com.iacovelli.nicola.eatbasta.viewmodel.CustomViewModel;

import java.text.DecimalFormat;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progress;
    private TextView totalTxt;
    private TextView minOrderTxt;
    private Button checkoutBtn;
    private float minimumOrder = 40.4f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.product_list);
        progress = findViewById(R.id.checkout_bar);
        progress.setIndeterminate(false);
        progress.setMax((int) (minimumOrder * 100));
        progress.setProgress(0);
        totalTxt = findViewById(R.id.total_text);
        minOrderTxt = findViewById(R.id.restaurant_min_order_cart);
        minOrderTxt.setText(String.valueOf(minimumOrder));
        checkoutBtn = findViewById(R.id.checkout_button);
        CustomViewModel model = ViewModelProviders.of(this).get(CustomViewModel.class);
        model.getProducts().observe(this, p -> {
            ProductAdapter productAdapter = new ProductAdapter(p, v -> checkProducts(p));
            recyclerView.setAdapter(productAdapter);
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        });


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
    private void checkProducts(List<Product> productList) {
        double total = 0;
        Log.d("Dimensione: ", String.valueOf(productList.size()));
        //productList.removeIf(p -> p.getProductQuantity()==0);
        //Log.d("Dimensione: ", String.valueOf(productList.size()));
        for (Product p : productList) {
            total += (p.getProductPrice() * p.getProductQuantity());
        }
        progress.setProgress((int) Math.round(total * 100), true);
        DecimalFormat df = new DecimalFormat("###.##");
        String totalString = df.format(total);
        totalTxt.setText(totalString);
        if (total >= minimumOrder && !checkoutBtn.isEnabled()) {
            checkoutBtn.setEnabled(true);
        } else if (total < minimumOrder && checkoutBtn.isEnabled()) {
            checkoutBtn.setEnabled(false);
        }

    }

}
