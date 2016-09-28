package com.padc.recipes.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.activities.SearchActivity;
import com.padc.recipes.adapters.FoodAdapter;
import com.padc.recipes.adapters.RestaurantAdapter;
import com.padc.recipes.adapters.TownshipAdapter;
import com.padc.recipes.data.models.RecipeModel;
import com.padc.recipes.data.models.RestaurantModel;
import com.padc.recipes.data.persistence.RecipeContract;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.data.vos.RestaurantVO;
import com.padc.recipes.dialogs.ShareDialog;
import com.padc.recipes.events.DataEvent;
import com.padc.recipes.utils.RecipeAppConstants;
import com.padc.recipes.views.holders.RestaurntViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

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

        // title
        getActivity().setTitle(R.string.Resturants);

        // get restaurant data
        RestaurantModel.getInstance().loadRestaurants();
        List<RestaurantVO> restaurantList = new ArrayList<>();

        mRestaurantAdapter = new RestaurantAdapter(mControllerRestaurantItem, restaurantList);
        rvRestaurant.setAdapter(mRestaurantAdapter);

        int gridColumnSpanCount = 1;
        rvRestaurant.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    protected void onSendScreenHit() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(RecipeAppConstants.RESTAURANT_LIST_LOADER, null, this);
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    public void onEventMainThread(DataEvent.RestaurantDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();


        List<RestaurantVO> newRestaurantList = event.getRestaurantList();
        mRestaurantAdapter.setNewData(newRestaurantList);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                RecipeContract.RestaurantEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<RestaurantVO> restaurantList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                RestaurantVO restaurant = RestaurantVO.parseFromCursor(data);
                restaurant.setPhotos(restaurant.loadRestaurantImagesByRestaurantId(restaurant.getRestaurant_id()));
                // set township
                if (restaurant.getTownship() != null) {
                    restaurant.setTownship(restaurant.loadTownshipByTownshipId(String.valueOf(restaurant.getTownship().getTownship_id())));
                }
                // set servicetime
                restaurant.setService_time(restaurant.loadRestaurantServiceTimeByRestaurantId(restaurant.getRestaurant_id()));

                // set Recommended foods
                restaurant.setMost_popular_recipes(restaurant.loadRestaurantRecommendedFoodByRestaurantId(restaurant.getRestaurant_id()));

                restaurantList.add(restaurant);
            } while (data.moveToNext());
        }

        Log.d(RecipesApp.TAG, "Retrieved restaurants : " + restaurantList.size());
        mRestaurantAdapter.setNewData(restaurantList);

        RestaurantModel.getInstance().setStoredData(restaurantList);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}


