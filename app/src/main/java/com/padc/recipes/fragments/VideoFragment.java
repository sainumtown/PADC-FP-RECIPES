package com.padc.recipes.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.adapters.RestaurantAdapter;
import com.padc.recipes.adapters.VideoListAdapter;
import com.padc.recipes.views.holders.VideoViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {


    @BindView(R.id.rv_videos)
    RecyclerView rvVideo;

    private VideoListAdapter mVideoAdapter;
    VideoViewHolder.ControllerVideoItem  mController;

    public static VideoFragment newInstance() {

        Bundle args = new Bundle();

        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mController = (VideoViewHolder.ControllerVideoItem) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        ButterKnife.bind(this, view);

        mVideoAdapter = new VideoListAdapter(mController);
        rvVideo.setAdapter(mVideoAdapter);

        int gridColumnSpanCount = 1;
        rvVideo.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
        return view;
    }

}
