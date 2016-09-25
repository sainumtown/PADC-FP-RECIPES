package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.recipes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MPSS-PC01 on 9/24/2016.
 */
public class FavouriteViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_favourite_title)
    TextView tvFavouriteTitle;

    @BindView(R.id.iv_favourite_photo)
    ImageView ivRestaurnatPhoto;

    private ControllerFavouriteItem mController;

    public FavouriteViewHolder(View itemView, ControllerFavouriteItem mControllerItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = mControllerItem;
    }

    @Override
    public void onClick(View view) {
        mController.onTapFavourite();
    }

    public interface ControllerFavouriteItem {
        void onTapFavourite();
    }
}
