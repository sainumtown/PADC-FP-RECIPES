package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.recipes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sainumtown on 9/16/16.
 */

public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener   {

    @BindView(R.id.tv_recipe_video_title)
    TextView tvVideoTitle;

    @BindView(R.id.iv_recipe_video)
    ImageView ivVideo;

    private ControllerVideoItem mController;

    public VideoViewHolder(View itemView, ControllerVideoItem mControllerItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mController = mControllerItem;
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        mController.onTapVideo();
    }

    public interface ControllerVideoItem {
        void onTapVideo();
    }
}
