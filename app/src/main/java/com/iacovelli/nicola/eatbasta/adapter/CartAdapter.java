package com.iacovelli.nicola.eatbasta.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iacovelli.nicola.eatbasta.OnProductRemovedFromCartListener;
import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.model.Cart;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Cart> data;
    private OnProductRemovedFromCartListener listener;

    public CartAdapter(List<Cart> carts) {
        data = carts;
    }

    public void setData(List<Cart> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void setListener(OnProductRemovedFromCartListener listener) {
        this.listener = listener;
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private TextView productQuantity;
        private TextView productName;
        private TextView productTotalPrice;
        private ImageView removeProduct;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productQuantity = itemView.findViewById(R.id.quantity_product_checkout);
            productName = itemView.findViewById(R.id.product_checkout);
            productTotalPrice = itemView.findViewById(R.id.price_product_checkout);
            removeProduct = itemView.findViewById(R.id.remove_product_checkout);
            removeProduct.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                builder.setMessage("Sei sicuro di voler rimuovere il prodotto dal carrello?")
                        .setPositiveButton("Si", (dialog, which) -> listener.onProductRemovedFromCart(data.get(getAdapterPosition()).getId()))
                        .setNegativeButton("No", null)
                        .show();

            });
        }

        public void bind() {
            Cart c = data.get(getAdapterPosition());
            productQuantity.setText(String.valueOf(c.getProduct().getProductQuantity()));
            productName.setText(c.getProduct().getProductName());
            productTotalPrice.setText(String.valueOf(c.getProduct().getProductPrice() * c.getProduct().getProductQuantity()));
        }
    }
}
