package com.padc.recipes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.adapters.FavouriteAdapter;
import com.padc.recipes.adapters.RestaurantAdapter;
import com.padc.recipes.views.holders.FavouriteViewHolder;
import com.padc.recipes.views.holders.RestaurntViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteFragment extends Fragment {
    @BindView(R.id.rv_favourite)
    RecyclerView rvFavourite;

    private FavouriteAdapter mFavouriteAdapter;
    FavouriteViewHolder.ControllerFavouriteItem mControllerFavouriteItem;


    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerFavouriteItem = (FavouriteViewHolder.ControllerFavouriteItem) context;
    }

    public static FavouriteFragment newInstance() {
        FavouriteFragment fragment = new FavouriteFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        ButterKnife.bind(this, view);

        mFavouriteAdapter = new FavouriteAdapter(mControllerFavouriteItem);
        rvFavourite.setAdapter(mFavouriteAdapter);

        int gridColumnSpanCount = 1;
        rvFavourite.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }
}
