package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.recipes.R;
import com.padc.recipes.data.persistence.RecipeContract;
import com.padc.recipes.data.vos.AvailableRestaurantVO;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.utils.RecipeAppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AvailableRestaurantsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_available_restaurant_title)
    TextView tvAvailableRestaurantTile;

    @BindView(R.id.iv_available_restaurants_photo)
    ImageView ivAvailableRestaurnatsPhoto;


    private ControllerAvailableRestaurantItem mController;
    private AvailableRestaurantVO mAvailableRestaurant;

    public AvailableRestaurantsViewHolder(View itemView, ControllerAvailableRestaurantItem controllerAvailableRestaurantItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controllerAvailableRestaurantItem;
    }

    @Override
    public void onClick(View view) {
        mController.onTapAvailableRestaurant(String.valueOf(mAvailableRestaurant.getRestaurants_id()));
    }

    public void bindData(AvailableRestaurantVO availableRestaurant) {
        mAvailableRestaurant = availableRestaurant;
        tvAvailableRestaurantTile.setText(availableRestaurant.getRestaurants_name());

        String imageUrl =  RecipeAppConstants.IMAGE_ROOT_DIR +mAvailableRestaurant.getImage();
        Glide.with(ivAvailableRestaurnatsPhoto.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivAvailableRestaurnatsPhoto);
    }

    public interface ControllerAvailableRestaurantItem{
        void onTapAvailableRestaurant(String  restaurantId);
    }


}
