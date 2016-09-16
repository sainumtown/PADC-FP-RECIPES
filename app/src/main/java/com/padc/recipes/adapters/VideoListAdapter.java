package com.padc.recipes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.views.holders.VideoViewHolder;

/**
 * Created by sainumtown on 9/16/16.
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private LayoutInflater mInflater;
    private VideoViewHolder.ControllerVideoItem mControllerVideoItem;

    public VideoListAdapter(VideoViewHolder.ControllerVideoItem controller) {
        this.mInflater = mInflater.from(RecipesApp.getContext());
        this.mControllerVideoItem = controller;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_video, parent, false);
        return new VideoViewHolder(itemView, mControllerVideoItem);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
