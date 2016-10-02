package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.recipes.R;
import com.padc.recipes.data.vos.AvailableRestaurantVO;
import com.padc.recipes.data.vos.MostPopularRecipeVO;
import com.padc.recipes.utils.RecipeAppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendedFoodsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_recommended_foods)
    TextView tvRecommendedFoods;

    @BindView(R.id.iv_recommended_foods)
    ImageView ivRecommendedFood;


    private ControllerRecommendedFoods mController;
    private MostPopularRecipeVO mMostPopularRecipe;

    public RecommendedFoodsViewHolder(View itemView, ControllerRecommendedFoods controllerItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controllerItem;
    }

    @Override
    public void onClick(View view) {
        mController.onTapRecommendedFoods(String.valueOf(mMostPopularRecipe.getRecipe_id()));
    }

    public void bindData(MostPopularRecipeVO mostPopularRecipe) {
        mMostPopularRecipe = mostPopularRecipe;
        tvRecommendedFoods.setText(mostPopularRecipe.getRecipe_name());

        String imageUrl =  RecipeAppConstants.IMAGE_ROOT_DIR +mostPopularRecipe.getPhotos();
        Glide.with(ivRecommendedFood.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivRecommendedFood);
    }

    public interface ControllerRecommendedFoods{
        void onTapRecommendedFoods(String recipeId);
    }


}
