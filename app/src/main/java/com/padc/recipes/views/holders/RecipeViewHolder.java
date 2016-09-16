package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.recipes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_recipe_title)
    TextView tvRecipeTitle;

    @BindView(R.id.iv_recipe)
    ImageView ivRecipe;

    private ControllerRecipeItem mController;

    public RecipeViewHolder(View itemView, ControllerRecipeItem controllerRecipeItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controllerRecipeItem;
    }

    @Override
    public void onClick(View view) {
        mController.onTapRecipe();
    }

    public interface ControllerRecipeItem{
        void onTapRecipe();
    }
}
