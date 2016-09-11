package com.padc.recipes.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.adapters.RestaurantAdapter;
import com.padc.recipes.views.holders.RestaurntViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantListFragment extends Fragment {

    @BindView(R.id.rv_restaurants)
    RecyclerView rvRestaurant;

    private RestaurantAdapter mRestaurantAdapter;
    RestaurntViewHolder.ControllerRestaurantItem mControllerRestaurantItem;


    public RestaurantListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerRestaurantItem = (RestaurntViewHolder.ControllerRestaurantItem) context;
    }

    public static RestaurantListFragment newInstance() {

        Bundle args = new Bundle();

        RestaurantListFragment fragment = new RestaurantListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        ButterKnife.bind(this, view);

        mRestaurantAdapter = new RestaurantAdapter(mControllerRestaurantItem);
        rvRestaurant.setAdapter(mRestaurantAdapter);

        int gridColumnSpanCount = 1;
        rvRestaurant.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_restaurant_search).setVisible(true);
        super.onPrepareOptionsMenu(menu);

    }


}
