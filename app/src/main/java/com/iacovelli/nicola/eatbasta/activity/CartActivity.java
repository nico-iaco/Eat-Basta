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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.adapter.ProductAdapter;
import com.iacovelli.nicola.eatbasta.model.Product;
import com.iacovelli.nicola.eatbasta.model.Restaurant;
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
    private ImageView restaurantImage;
    private TextView restaurantName;
    private TextView restaurantAddress;
    private ProgressBar progress;
    private TextView totalTxt;
    private TextView minOrderTxt;
    private Button checkoutBtn;
    private double minimumOrder = 40.4;
    private ProductAdapter adapter = new ProductAdapter(null, null);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent input = getIntent();
        CustomViewModel model = ViewModelProviders.of(this).get(CustomViewModel.class);
        recyclerView = findViewById(R.id.product_list);
        restaurantImage = findViewById(R.id.restaurant_image_cart);
        restaurantName = findViewById(R.id.restaurant_name_cart);
        restaurantAddress = findViewById(R.id.restaurant_description_cart);
        progress = findViewById(R.id.checkout_bar);
        progress.setIndeterminate(false);
        progress.setProgress(0);
        totalTxt = findViewById(R.id.total_text);
        minOrderTxt = findViewById(R.id.restaurant_min_order_cart);
        if (input != null) {
            int indexRestaurant = input.getIntExtra("restaurant_index", 0);
            model.getRestaurants().observe(this, restaurants -> {
                Restaurant r = restaurants.get(indexRestaurant);
                Glide.with(this).load(r.getUrlImage()).into(restaurantImage);
                restaurantName.setText(r.getName());
                restaurantAddress.setText(r.getAddress());
                minimumOrder = r.getMinOrder();
                minOrderTxt.setText(String.valueOf(minimumOrder));
                progress.setMax((int) (minimumOrder * 100));
            });

        }
        checkoutBtn = findViewById(R.id.checkout_button);

        checkoutBtn.setOnClickListener(v -> {
            List<Product> list = adapter.getProducts();
            for (Product p : list) {
                if (p.getProductQuantity() != 0) {
                    model.insertProductIntoCart(p);
                }
            }
            Intent i = new Intent(CartActivity.this, CheckoutActivity.class);
            i.putExtra("restaurant_name", restaurantName.getText().toString());
            i.putExtra("restaurant_address", restaurantAddress.getText().toString());
            i.putExtra("min_order", minimumOrder);
            startActivity(i);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        model.getProducts().observe(this, p -> {
            adapter.setProducts(p);
            adapter.setChangeListener(model::updateProduct);
            checkProducts(p);

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
        //TODO: Ottimizzare la funzione
        double total = 0;
        Log.d("Dimensione: ", String.valueOf(productList.size()));
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
