package com.padc.recipes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.components.MMCheckBox;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.views.holders.ShoppingListViewHolder;

import java.util.ArrayList;
import java.util.List;


public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListViewHolder> {

    private LayoutInflater mInflater;



    public ShoppingListAdapter(List<RecipeVO> recipeList) {
        mInflater = LayoutInflater.from(RecipesApp.getContext());
        mRecipeList = recipeList;
    }

    List<RecipeVO> mRecipeList = new ArrayList<>();
    @Override
    public ShoppingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_shopping_list, parent, false);

        return new ShoppingListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShoppingListViewHolder holder, int position) {
        holder.bindData(mRecipeList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }


    public void setNewData(List<RecipeVO> recipeList) {
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }
}
