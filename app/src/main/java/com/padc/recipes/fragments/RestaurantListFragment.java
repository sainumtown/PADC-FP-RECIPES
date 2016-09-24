package com.padc.recipes.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.activities.SearchActivity;
import com.padc.recipes.adapters.FoodAdapter;
import com.padc.recipes.adapters.RestaurantAdapter;
import com.padc.recipes.adapters.TownshipAdapter;
import com.padc.recipes.data.models.RecipeModel;
import com.padc.recipes.data.models.RestaurantModel;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.data.vos.RestaurantVO;
import com.padc.recipes.dialogs.ShareDialog;
import com.padc.recipes.views.holders.RestaurntViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private TownshipAdapter mTownshipAdapter;
    private FoodAdapter mFoodAdapter;

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

        // get restaurant data
        RestaurantModel.getInstance().loadRestaurants();
        List<RestaurantVO> restaurantList = RestaurantModel.getInstance().getRestaurantList();

        mRestaurantAdapter = new RestaurantAdapter(mControllerRestaurantItem,restaurantList);
        rvRestaurant.setAdapter(mRestaurantAdapter);

        int gridColumnSpanCount = 1;
        rvRestaurant.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }

}
