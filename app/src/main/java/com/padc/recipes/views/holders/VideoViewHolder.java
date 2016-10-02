package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.padc.recipes.R;
import com.padc.recipes.contents.YouTubeContent;
import com.padc.recipes.utils.RecipeAppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sainumtown on 9/16/16.
 */

public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,YouTubeThumbnailView.OnInitializedListener  {

    @BindView(R.id.imageView_thumbnail)
    YouTubeThumbnailView iv_youtube_thumbnail;

    @BindView(R.id.tv_video_title)
    TextView tv_video_title;

    YouTubeContent.YouTubeVideo mVideo;


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

    public void bindData(YouTubeContent.YouTubeVideo youTubeVideo) {
        mVideo =youTubeVideo;
        tv_video_title.setText(youTubeVideo.title);

        //Initialise the thumbnail
        iv_youtube_thumbnail.setTag(youTubeVideo.id);
        iv_youtube_thumbnail.initialize(RecipeAppConstants.ACCESS_TOKEN_YOU_TUBE, this);

    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

    }

    public interface ControllerVideoItem {
        void onTapVideo();
    }
}
