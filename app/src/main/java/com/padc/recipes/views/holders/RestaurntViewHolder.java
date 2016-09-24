package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.data.vos.RestaurantVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurntViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_restaurant_title)
    TextView tvRestaurantTitle;

    @BindView(R.id.iv_restaurant_photo)
    ImageView ivRestaurnatPhoto;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @BindView(R.id.tv_open_close_time)
    TextView tvOpenCloseTime;

    @BindView(R.id.tv_township)
    TextView tvTownship;

    @BindView(R.id.tv_recommended_food)
    TextView tvRecommendedFood;

    private RestaurantVO mRestaurant;

    private ControllerRestaurantItem mController;

    public RestaurntViewHolder(View itemView, ControllerRestaurantItem mControllerItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = mControllerItem;
    }

    @Override
    public void onClick(View view) {
        mController.onTapRestaurant();
    }

    public void bindData(RestaurantVO restaurantVO) {
        mRestaurant = restaurantVO;
        tvRestaurantTitle.setText(mRestaurant.getRestaurant_name());
        tvAddress.setText(mRestaurant.getAddress());
        String serviceTime = RecipesApp.getContext().getString(R.string.lbl_morning) + " " + mRestaurant.getService_time().getStart()
                + RecipesApp.getContext().getString(R.string.lbl_from) + " " + RecipesApp.getContext().getString(R.string.lbl_end)
                + " " + mRestaurant.getService_time().getFinish();
        tvOpenCloseTime.setText(serviceTime);
        tvTownship.setText(mRestaurant.getTownship().getTownship_name());
        tvRecommendedFood.setText(mRestaurant.getMost_popular_recipes().get(0).getRecipe_name());
    }

    public interface ControllerRestaurantItem {
        void onTapRestaurant();
    }
}
