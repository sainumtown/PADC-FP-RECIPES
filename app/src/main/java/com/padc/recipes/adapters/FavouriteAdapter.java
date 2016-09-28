package com.padc.recipes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.views.holders.FavouriteViewHolder;

import java.util.List;

/**
 * Created by MPSS-PC01 on 9/24/2016.
 */
public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteViewHolder> {
    private LayoutInflater mInflater;
    private FavouriteViewHolder.ControllerFavouriteItem mControllerItem;
    private List<RecipeVO> mRecipeList;

    public FavouriteAdapter(FavouriteViewHolder.ControllerFavouriteItem controllerFavouriteItem, List<RecipeVO> recipeList) {
        mInflater = LayoutInflater.from(RecipesApp.getContext());
        this.mControllerItem = controllerFavouriteItem;
        this.mRecipeList = recipeList;

    }

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_favourite, parent, false);
        return new FavouriteViewHolder(itemView, mControllerItem);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        holder.bindData(mRecipeList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public void setNewData(List<RecipeVO> newRecipeList) {
        mRecipeList = newRecipeList;
        notifyDataSetChanged();
    }

}
