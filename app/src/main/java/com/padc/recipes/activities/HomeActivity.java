package com.padc.recipes.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.padc.recipes.R;
import com.padc.recipes.adapters.RecipeCategoryListAdapter;
import com.padc.recipes.fragments.RecipeListFragment;
import com.padc.recipes.fragments.RestaurantListFragment;
import com.padc.recipes.views.holders.RecipeViewHolder;
import com.padc.recipes.views.holders.RestaurntViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        , RestaurntViewHolder.ControllerRestaurantItem
        , RecipeViewHolder.ControllerRecipeItem {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    Spinner spinnerRecipeCategoryFilter;

    private RecipeCategoryListAdapter mRecipeCategoryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_reorder_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Menu leftMenu = navigationView.getMenu();
       /* MMFontUtils.applyMMFontToMenu(leftMenu);*/
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            navigateToRecipes();
        }

        // for spinner recipe category filter
        String[] recipesCategoryListArray = getResources().getStringArray(R.array.recipes_category);
        List<String> recipesCategoryList = new ArrayList<>(Arrays.asList(recipesCategoryListArray));
        mRecipeCategoryListAdapter = new RecipeCategoryListAdapter(recipesCategoryList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem item = menu.findItem(R.id.spinner_filter_category);

        spinnerRecipeCategoryFilter = (Spinner) MenuItemCompat.getActionView(item);
        spinnerRecipeCategoryFilter.setAdapter(mRecipeCategoryListAdapter);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            /*case R.id.action_settings:*/
     /*           GAUtils.getInstance().sendAppAction(GAUtils.ACTION_TAP_SETTINGS);*/
              /*  return true;*/
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.recipes:
                navigateToRecipes();
                return true;
            case R.id.restaurants:
                navigateToRestaurants();
                return true;
        }
        return false;
    }

    private void navigateToRecipes() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, RecipeListFragment.newInstance())
                .commit();


    }

    private void navigateToRestaurants() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, RestaurantListFragment.newInstance())
                .commit();
        /*spinnerRecipeCategoryFilter.setVisibility(View.GONE);*/
    }

    @Override
    public void onTapRestaurant() {
        Toast.makeText(getApplicationContext(), "Will go to detail page", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTapRecipe() {
        Toast.makeText(getApplicationContext(), "Will go to detail page", Toast.LENGTH_SHORT).show();
    }
}
