package com.padc.recipes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.views.holders.RecipeViewHolder;
import com.padc.recipes.views.holders.RestaurntViewHolder;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurntViewHolder> {

    private LayoutInflater mInflater;
    private RestaurntViewHolder.ControllerRestaurantItem mControllerItem;

    public RestaurantAdapter(RestaurntViewHolder.ControllerRestaurantItem controllerRestaurantItem) {
        mInflater = LayoutInflater.from(RecipesApp.getContext());
        this.mControllerItem = controllerRestaurantItem;

    }

    @Override
    public RestaurntViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_restaurnat, parent, false);

        return new RestaurntViewHolder(itemView,mControllerItem);
    }

    @Override
    public void onBindViewHolder(RestaurntViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }


}
