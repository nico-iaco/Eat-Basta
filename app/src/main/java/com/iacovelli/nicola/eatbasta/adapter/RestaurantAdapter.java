package com.iacovelli.nicola.eatbasta.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.activity.CartActivity;
import com.iacovelli.nicola.eatbasta.model.Restaurant;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurantList;
    private boolean isGridMode;

    public RestaurantAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;

    }

    public boolean isGridMode() {
        return isGridMode;
    }

    public void setGridMode(boolean gridMode) {
        isGridMode = gridMode;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layout = isGridMode ? R.layout.restaurant_item_grid : R.layout.restaurant_item;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        return new RestaurantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, int i) {
        Restaurant restaurant = restaurantList.get(i);
        restaurantViewHolder.bind(restaurant);
    }

    @Override
    public int getItemCount() {
        return restaurantList != null ? restaurantList.size() : 0;
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final View v;
        private final ImageView restaurantImage;
        private final TextView restaurantName;
        private final TextView restaurantDescription;
        private final TextView restaurantMinOrder;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            this.v = itemView;
            restaurantImage = v.findViewById(R.id.restaurant_image);
            restaurantName = v.findViewById(R.id.restaurant_name);
            restaurantDescription = v.findViewById(R.id.restaurant_description);
            restaurantMinOrder = v.findViewById(R.id.restaurant_min_order);
            v.setOnClickListener(this);
        }

        public void bind(Restaurant r) {
            Log.d("Immagine: ", String.valueOf(r.getImage()));
            restaurantName.setText(r.getName());
            restaurantDescription.setText(r.getDescription());
            restaurantMinOrder.setText("Ordine minimo: " + String.valueOf(r.getMinOrder()));
            Glide.with(v).load(r.getImage()).into(restaurantImage);
            //restaurantImage.setImageResource(r.getImage());
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), CartActivity.class);
            v.getContext().startActivity(i);
        }
    }
}
