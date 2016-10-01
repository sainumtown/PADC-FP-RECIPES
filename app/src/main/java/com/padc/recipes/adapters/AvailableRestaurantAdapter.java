package com.padc.recipes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.data.persistence.RecipeContract;
import com.padc.recipes.data.vos.AvailableRestaurantVO;
import com.padc.recipes.data.vos.RestaurantVO;
import com.padc.recipes.views.holders.AvailableRestaurantsViewHolder;
import com.padc.recipes.views.holders.RestaurntViewHolder;

import java.util.ArrayList;
import java.util.List;


public class AvailableRestaurantAdapter extends RecyclerView.Adapter<AvailableRestaurantsViewHolder> {

    private LayoutInflater mInflater;
    private AvailableRestaurantsViewHolder.ControllerAvailableRestaurantItem mControllerItem;
    private List<AvailableRestaurantVO> mAvailableRestaurnats = new ArrayList<>();


    public AvailableRestaurantAdapter(AvailableRestaurantsViewHolder.ControllerAvailableRestaurantItem controllerAvailableRestaurantItem,
                                      List<AvailableRestaurantVO> availableRestaurnats ) {
        mInflater = LayoutInflater.from(RecipesApp.getContext());
        this.mControllerItem = controllerAvailableRestaurantItem;
        mAvailableRestaurnats = availableRestaurnats;

    }


    @Override
    public AvailableRestaurantsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_available_restaurants, parent, false);

        return new AvailableRestaurantsViewHolder(itemView,mControllerItem);
    }

    @Override
    public void onBindViewHolder(AvailableRestaurantsViewHolder holder, int position) {
        holder.bindData(mAvailableRestaurnats.get(position));
    }

    @Override
    public int getItemCount() {
        return mAvailableRestaurnats.size();
    }


    public void setNewData(List<AvailableRestaurantVO> newAvialbleRestaurants) {
        mAvailableRestaurnats = newAvialbleRestaurants;
        notifyDataSetChanged();
    }
}
