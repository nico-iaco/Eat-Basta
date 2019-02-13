package com.iacovelli.nicola.eatbasta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.model.Product;
import com.iacovelli.nicola.eatbasta.utility.OnQuantityChangeListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;
    private OnQuantityChangeListener changeListener;


    public ProductAdapter(List<Product> products, OnQuantityChangeListener clickListener) {
        this.products = products;
        this.changeListener = clickListener;
    }

    public void setChangeListener(OnQuantityChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public List<Product> getProducts() {
        return products;
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

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final View view;
        private final TextView productName;
        private final TextView productPrice;
        private final Button increase;
        private final Button decrease;
        private final TextView productQuantity;
        private Product p;

        public ProductViewHolder(View view) {
            super(view);
            this.view = view;
            productName = view.findViewById(R.id.product_name);
            productPrice = view.findViewById(R.id.product_price);
            productQuantity = view.findViewById(R.id.product_quantity);
            increase = view.findViewById(R.id.increase_quantity);
            decrease = view.findViewById(R.id.decrease_quantity);
            increase.setOnClickListener(this);
            decrease.setOnClickListener(this);
        }

        public void bind(Product p) {
            this.p = p;
            productName.setText(p.getProductName());
            productPrice.setText(String.valueOf(p.getProductPrice()));
            productQuantity.setText(String.valueOf(p.getProductQuantity()));
        }

        @Override
        public void onClick(View v) {
            int value = p.getProductQuantity();
            switch (v.getId()) {
                case R.id.increase_quantity:
                    value++;
                    break;
                case R.id.decrease_quantity:
                    int tmp = value - 1;
                    if (tmp >= 0) {
                        value = tmp;
                    }
                    break;
            }
            changeListener.onQuantityChange(p.getId(), value);

        }


    }
}
