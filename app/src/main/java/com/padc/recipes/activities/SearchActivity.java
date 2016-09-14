package com.padc.recipes.activities;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.adapters.FoodAdapter;
import com.padc.recipes.adapters.TownshipAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {


    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.backdrop)
    ImageView ivBackDrop;


    @BindView(R.id.sp_township)
    Spinner spnTownShip;

    @BindView(R.id.sp_food)
    Spinner spnFood;

    private TownshipAdapter mTownshipAdapter;
    private FoodAdapter mFoodAdapter;

    public static Intent newIntent() {
        Intent intent = new Intent(RecipesApp.getContext(), SearchActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this, this);
        collapsingToolbar.setTitle(" ");

        /*initCollapsingToolbar();*/
        initialize();

        try {
            Glide.with(this).load(R.drawable.sample_recipe_thumbnail).into(ivBackDrop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        // set the custom dialog components - spinner and button
        // for data spinner township
        String[] townShipArrayList = getResources().getStringArray(R.array.township_list);
        List<String> townshipList = new ArrayList<>(Arrays.asList(townShipArrayList));
        // for initial state
        townshipList.add(getString(R.string.choose_township));
        mTownshipAdapter = new TownshipAdapter(townshipList);

        // for data spinner food
        String[] foodArrayList = getResources().getStringArray(R.array.food_list);
        List<String> foodList = new ArrayList<>(Arrays.asList(foodArrayList));
        foodList.add(getString(R.string.choose_food));
        mFoodAdapter = new FoodAdapter(foodList);

        // connect spinner township with adapter
        spnTownShip.setAdapter(mTownshipAdapter);
        spnTownShip.setSelection(spnTownShip.getCount());

        // connect spinner food with adapter
        spnFood.setAdapter(mFoodAdapter);
        spnFood.setSelection(spnFood.getCount());
    }

    private void initCollapsingToolbar() {
        collapsingToolbar.setTitle(" ");
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.lbl_search));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return true;
    }
}
