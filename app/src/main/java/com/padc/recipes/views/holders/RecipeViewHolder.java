package com.padc.recipes.views.holders;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.utils.RecipeAppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_recipe_title)
    TextView tvRecipeTitle;

    @BindView(R.id.iv_recipe)
    ImageView ivRecipe;

    @BindView(R.id.overflow)
    ImageView ivOverflow;

    private ControllerRecipeItem mController;
    private RecipeVO mRecipe;

    public RecipeViewHolder(View itemView, ControllerRecipeItem controllerRecipeItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controllerRecipeItem;

        ivOverflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add to favourite list
                if(!mRecipe.isNotFavourite()) {
                    ivOverflow.setImageResource(R.drawable.ic_favorite_border_24dp_enable);
                }else {
                    ivOverflow.setImageResource(R.drawable.ic_favorite_border_24dp);
                }
                mController.onTapFavourite(String.valueOf(mRecipe.getRecipe_id()));

            }
        });
    }

    @Override
    public void onClick(View view) {
        mController.onTapRecipe(String.valueOf(mRecipe.getRecipe_id()));
    }

    public void bindData(RecipeVO recipe) {
        mRecipe = recipe;
        tvRecipeTitle.setText(recipe.getRecipe_title());
        if(mRecipe.isNotFavourite()) {
            ivOverflow.setImageResource(R.drawable.ic_favorite_border_24dp_enable);
        }else {
            ivOverflow.setImageResource(R.drawable.ic_favorite_border_24dp);
        }
    }

    public interface ControllerRecipeItem{
        void onTapRecipe(String recipeId);
        void onTapFavourite(String recipeId);
    }


}
