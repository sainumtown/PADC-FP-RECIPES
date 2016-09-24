package com.padc.recipes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.views.holders.RecipeViewHolder;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private LayoutInflater mInflater;
    private RecipeViewHolder.ControllerRecipeItem mController;
    private List<RecipeVO> mRecipeList;

    public RecipeAdapter(RecipeViewHolder.ControllerRecipeItem controllerRecipeItem, List<RecipeVO> recipeList) {
        mInflater = LayoutInflater.from(RecipesApp.getContext());
        this.mController = controllerRecipeItem;
        this.mRecipeList = recipeList;
    }



    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_recipe, parent, false);
        return new RecipeViewHolder(itemView, mController);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bindData(mRecipeList.get(position));

    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }


}
