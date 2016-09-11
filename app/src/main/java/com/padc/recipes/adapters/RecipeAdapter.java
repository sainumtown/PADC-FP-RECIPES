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
    private RecipeViewHolder.ControllerRecipeItem mController;

    public RecipeAdapter(RecipeViewHolder.ControllerRecipeItem controllerRecipeItem) {
        mInflater = LayoutInflater.from(RecipesApp.getContext());
        this.mController = controllerRecipeItem;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_recipe, parent, false);
        return new RecipeViewHolder(itemView, mController);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }


}
