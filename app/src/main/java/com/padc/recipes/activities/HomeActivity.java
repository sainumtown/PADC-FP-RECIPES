package com.padc.recipes.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import com.padc.recipes.RecipesApp;
import com.padc.recipes.adapters.RecipeCategoryListAdapter;
import com.padc.recipes.data.models.RecipeModel;
import com.padc.recipes.data.models.RestaurantModel;
import com.padc.recipes.data.vos.AvailableRestaurantVO;
import com.padc.recipes.data.vos.IngredientVO;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.fragments.FavouriteFragment;
import com.padc.recipes.fragments.RecipeListFragment;
import com.padc.recipes.fragments.RestaurantListFragment;
import com.padc.recipes.fragments.ShoppingListFragment;
import com.padc.recipes.fragments.VideoFragment;
import com.padc.recipes.views.holders.AvailableRestaurantsViewHolder;
import com.padc.recipes.views.holders.FavouriteViewHolder;
import com.padc.recipes.views.holders.RecipeViewHolder;
import com.padc.recipes.views.holders.RestaurntViewHolder;
import com.padc.recipes.views.holders.ShoppingListViewHolder;
import com.padc.recipes.views.holders.VideoViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        , RestaurntViewHolder.ControllerRestaurantItem
        , RecipeViewHolder.ControllerRecipeItem
        , VideoViewHolder.ControllerVideoItem
        , FavouriteViewHolder.ControllerFavouriteItem
        , ShoppingListViewHolder.ControllerShoppingListItem {

    private static final String IE_FRAGMENT = "IE_FRAGMENT";
    public static final String FRAGMENT_FAVOURITE = "1";
    public static final String FRAGMENT_SHOPPING_LIST = "2";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.fab_search)
    FloatingActionButton fabSearch;

    Spinner spinnerRecipeCategoryFilter;

    View.OnClickListener mFavouriteOnClickListener;

    private RecipeCategoryListAdapter mRecipeCategoryListAdapter;
    private String mScreenID;

    public static Intent newIntent(String attractionName) {
        Intent intent = new Intent(RecipesApp.getContext(), HomeActivity.class);
        intent.putExtra(IE_FRAGMENT, attractionName);
        return intent;
    }

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

        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToSearch();
            }
        });

        mScreenID = getIntent().getStringExtra(IE_FRAGMENT);
        if (mScreenID != null) {
            switch (mScreenID) {
                case FRAGMENT_FAVOURITE:
                    navigateToFavourite();
                    break;
                case FRAGMENT_SHOPPING_LIST:
                    navigateToShoppingList();
                    break;
            }
        }

        RecipeModel.getInstance().loadRecipes();
        RestaurantModel.getInstance().loadRestaurants();
    }

    private void navigateToSearch() {
        Intent intent = SearchActivity.newIntent();
        startActivity(intent);
        fabSearch.hide();
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
            case R.id.shoppingList:
                navigateToShoppingList();
                return true;
            case R.id.videos:
                navaigateToVideos();
                return true;
            case R.id.favourite:
                navigateToFavourite();
                return true;

        }
        return false;
    }

    public void navigateToFavourite() {
        fabSearch.hide();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, FavouriteFragment.newInstance())
                .commit();
    }

    private void navigateToShoppingList() {
        fabSearch.hide();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, ShoppingListFragment.newInstance())
                .commit();
    }

    private void navaigateToVideos() {
        fabSearch.hide();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, VideoFragment.newInstance())

                .commit();
    }

    private void navigateToRecipes() {
        fabSearch.hide();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, RecipeListFragment.newInstance())
                .commit();
    }

    private void navigateToRestaurants() {
        fabSearch.hide();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, RestaurantListFragment.newInstance())
                .commit();
        /*spinnerRecipeCategoryFilter.setVisibility(View.GONE);*/
    }

    @Override
    public void onTapRestaurant(String restaurantId) {
        Intent intent = ResturantDetailScreenActivity.newIntent(restaurantId);
        startActivity(intent);
    }

    @Override
    public void onTapRecipe(String recipeId) {
        Intent intent = RecipesDetailScreenActivity.newIntent(recipeId);
        startActivity(intent);
    }

    @Override
    public void onTapFavourite(RecipeVO recipeVO) {
        // TODO add favourite recipe ID

        RecipeModel.getInstance().AddToFavourite(recipeVO);

        mFavouriteOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFavourite();
            }
        };

        Snackbar.make(findViewById(android.R.id.content), "Check Favourite List", Snackbar.LENGTH_LONG)
                .setAction("View", mFavouriteOnClickListener)
                .setActionTextColor(Color.WHITE)
                .show();
    }

    @Override
    public void onTapVideo() {
        Toast.makeText(getApplicationContext(), "Will go to detail page", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTapFavouriteItem(String recipeId) {
        //TODO to get the real id.
        Intent intent = RecipesDetailScreenActivity.newIntent(recipeId);
        startActivity(intent);
    }


    @Override
    public void onCheckShoppingListIngredient(String recipeId, String ingredientId, Boolean flagBought) {
        RecipeVO.UpdateBoughtIngredient(recipeId, ingredientId, flagBought);
    }

    @Override
    public void onClickRemoveAllIngredientsShoppingListByRecipeId(String recipeId) {
        RecipeVO.removeAllIngredientsShoppingListByRecipeId(recipeId);
    }

}

