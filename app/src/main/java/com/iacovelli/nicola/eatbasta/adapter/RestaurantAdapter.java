package com.iacovelli.nicola.eatbasta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iacovelli.nicola.eatbasta.R;
import com.iacovelli.nicola.eatbasta.model.Restaurant;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    ArrayList<Restaurant> restaurantList;
    View.OnClickListener clickListener;

    public RestaurantAdapter(ArrayList<Restaurant> restaurantList, View.OnClickListener listener) {
        this.restaurantList = restaurantList;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.restaurant_item, viewGroup, false);
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

    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private final View v;
        private final ImageView restaurantImage;
        private final TextView restaurantName;
        private final TextView restaurantDescription;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            this.v = itemView;
            v.setOnClickListener(clickListener);
            restaurantImage = v.findViewById(R.id.restaurant_image);
            restaurantName = v.findViewById(R.id.restaurant_name);
            restaurantDescription = v.findViewById(R.id.restaurant_description);
        }

        public void bind(Restaurant r) {
            restaurantImage.setImageResource(r.getImage());
            restaurantName.setText(r.getName());
            restaurantDescription.setText(r.getDescription());
        }
    }
}
