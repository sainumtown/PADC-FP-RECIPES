package com.padc.recipes.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.padc.recipes.R;
import com.padc.recipes.activities.CustomLightboxActivity;
import com.padc.recipes.adapters.RestaurantAdapter;
import com.padc.recipes.adapters.VideoListAdapter;
import com.padc.recipes.contents.YouTubeContent;
import com.padc.recipes.utils.RecipeAppConstants;
import com.padc.recipes.views.holders.VideoViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends ListFragment {


    VideoViewHolder.ControllerVideoItem mController;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new VideoListAdapter(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        // title
        getActivity().setTitle(R.string.video_list_title);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        final Context context = getActivity();
        final String DEVELOPER_KEY = RecipeAppConstants.ACCESS_TOKEN_YOU_TUBE;
        final YouTubeContent.YouTubeVideo video = YouTubeContent.ITEMS.get(position);

        final Intent lightboxIntent = new Intent(context, CustomLightboxActivity.class);
        lightboxIntent.putExtra(CustomLightboxActivity.KEY_VIDEO_ID, video.id);
        startActivity(lightboxIntent);
    }

}
