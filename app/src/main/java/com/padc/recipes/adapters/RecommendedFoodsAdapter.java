package com.padc.recipes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.data.vos.AvailableRestaurantVO;
import com.padc.recipes.data.vos.MostPopularRecipeVO;
import com.padc.recipes.views.holders.AvailableRestaurantsViewHolder;
import com.padc.recipes.views.holders.RecommendedFoodsViewHolder;

import java.util.ArrayList;
import java.util.List;


public class RecommendedFoodsAdapter extends RecyclerView.Adapter<RecommendedFoodsViewHolder> {

    private LayoutInflater mInflater;
    private RecommendedFoodsViewHolder.ControllerRecommendedFoods mControllerItem;
    private List<MostPopularRecipeVO> mMostPopularRecipes = new ArrayList<>();


    public RecommendedFoodsAdapter(RecommendedFoodsViewHolder.ControllerRecommendedFoods controllerRecommendedFoods,
                                   List<MostPopularRecipeVO> mostPopularRecipeVOs ) {
        mInflater = LayoutInflater.from(RecipesApp.getContext());
        this.mControllerItem = controllerRecommendedFoods;
        mMostPopularRecipes = mostPopularRecipeVOs;

    }


    @Override
    public RecommendedFoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_recommended_foods, parent, false);

        return new RecommendedFoodsViewHolder(itemView,mControllerItem);
    }

    @Override
    public void onBindViewHolder(RecommendedFoodsViewHolder holder, int position) {
        holder.bindData(mMostPopularRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mMostPopularRecipes.size();
    }


    public void setNewData(List<MostPopularRecipeVO> newMostPopularRecipe) {
        mMostPopularRecipes = newMostPopularRecipe;
        notifyDataSetChanged();
    }
}
