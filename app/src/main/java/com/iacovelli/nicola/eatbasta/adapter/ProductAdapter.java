package com.iacovelli.nicola.eatbasta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.model.Product;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;
    private View.OnClickListener clickListener;


    public ProductAdapter(List<Product> products, View.OnClickListener clickListener) {
        this.products = products;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder viewHolder, int i) {
        Product p = products.get(i);
        viewHolder.bind(p);
    }



    @Override
    public int getItemCount() {
        if (products != null) {
            return products.size();
        } else {
            return 0;
        }
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements ScrollableNumberPickerListener {
        private final View view;
        private final TextView productName;
        private final TextView productPrice;
        private final ScrollableNumberPicker productQuantity;
        private Product p;

        public ProductViewHolder(View view) {
            super(view);
            this.view = view;
            productName = view.findViewById(R.id.product_name);
            productPrice = view.findViewById(R.id.product_price);
            productQuantity = view.findViewById(R.id.product_quantity);
            productQuantity.setListener(this);
        }

        public void bind(Product p) {
            this.p = p;
            productName.setText(p.getProductName());
            productPrice.setText(String.valueOf(p.getProductPrice()));
            productQuantity.setValue(p.getProductQuantity());
        }

        @Override
        public void onNumberPicked(int value) {
            p.setProductQuantity(value);
            clickListener.onClick(view);
        }
    }
}
