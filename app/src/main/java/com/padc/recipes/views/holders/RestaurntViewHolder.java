package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.recipes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurntViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_restaurant_title)
    TextView tvRestaurantTitle;

    @BindView(R.id.iv_restaurant_photo)
    ImageView ivRestaurnatPhoto;

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

    public interface ControllerRestaurantItem {
        void onTapRestaurant();
    }
}
