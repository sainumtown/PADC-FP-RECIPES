package com.padc.recipes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.views.holders.FavouriteViewHolder;

/**
 * Created by MPSS-PC01 on 9/24/2016.
 */
public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteViewHolder> {
    private LayoutInflater mInflater;
    private FavouriteViewHolder.ControllerFavouriteItem mControllerItem;

    public FavouriteAdapter(FavouriteViewHolder.ControllerFavouriteItem controllerFavouriteItem) {
        mInflater = LayoutInflater.from(RecipesApp.getContext());
        this.mControllerItem = controllerFavouriteItem;

    }

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_favourite, parent, false);

        return new FavouriteViewHolder(itemView,mControllerItem);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }


}
