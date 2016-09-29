package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.recipes.R;
import com.padc.recipes.data.vos.RecipeVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MPSS-PC01 on 9/24/2016.
 */
public class FavouriteViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_favourite_title)
    TextView tvFavouriteTitle;

    @BindView(R.id.iv_favourite_photo)
    ImageView ivFavouritePhoto;

    private ControllerFavouriteItem mController;
    private RecipeVO mRecipe;

    public FavouriteViewHolder(View itemView, ControllerFavouriteItem mControllerItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = mControllerItem;
    }

    @Override
    public void onClick(View view) {
        mController.onTapFavouriteItem(String.valueOf(mRecipe.getRecipe_id()));
    }

    public void bindData(RecipeVO recipe) {
        mRecipe = recipe;
        tvFavouriteTitle.setText(recipe.getRecipe_title());
    }

    public interface ControllerFavouriteItem {
        void onTapFavouriteItem(String recipeId);
    }
}
