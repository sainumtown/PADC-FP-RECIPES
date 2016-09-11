package com.padc.recipes.fragments;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.padc.recipes.adapters.FoodAdapter;
import com.padc.recipes.adapters.RestaurantAdapter;
import com.padc.recipes.adapters.TownshipAdapter;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_restaurant_search: {
                showSearchDialog();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);

    }

    private void showSearchDialog() {
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.restaurant_search_dialog);
        dialog.setTitle(getString(R.string.restaurnat_search_dialog_title));

        // set the custom dialog components - spinner and button
        // for data spinner township
        String[] townShipArrayList = getResources().getStringArray(R.array.township_list);
        List<String> townshipList = new ArrayList<>(Arrays.asList(townShipArrayList));
        mTownshipAdapter = new TownshipAdapter(townshipList);

        // for data spinner food
        String[] foodArrayList = getResources().getStringArray(R.array.food_list);
        List<String> foodList = new ArrayList<>(Arrays.asList(foodArrayList));
        mFoodAdapter = new FoodAdapter(foodList);

        // connect spinner township with adapter
        Spinner spnTownShip = (Spinner) dialog.findViewById(R.id.sp_township);
        spnTownShip.setAdapter(mTownshipAdapter);

        // connect spinner food with adapter
        Spinner spnFood = (Spinner) dialog.findViewById(R.id.sp_food);
        spnFood.setAdapter(mFoodAdapter);

        Button btnSearch = (Button) dialog.findViewById(R.id.btn_search);

        // if search button click , show the search data to the restaurant list
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
