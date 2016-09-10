package com.padc.recipes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.views.holders.RecipeViewHolder;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private LayoutInflater mInflater;


    public RecipeAdapter() {
        mInflater = LayoutInflater.from(RecipesApp.getContext());

    }


    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_recipe, parent, false);

        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }


}
