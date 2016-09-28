package com.padc.recipes.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.adapters.FavouriteAdapter;
import com.padc.recipes.adapters.RecipeAdapter;
import com.padc.recipes.adapters.RestaurantAdapter;
import com.padc.recipes.data.models.RecipeModel;
import com.padc.recipes.data.persistence.RecipeContract;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.utils.RecipeAppConstants;
import com.padc.recipes.views.holders.FavouriteViewHolder;
import com.padc.recipes.views.holders.RecipeViewHolder;
import com.padc.recipes.views.holders.RestaurntViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>  {
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

        // get recipe data
        RecipeModel.getInstance().loadRecipes();
        List<RecipeVO> recipeList = new ArrayList<RecipeVO>();

        mFavouriteAdapter = new FavouriteAdapter(mControllerFavouriteItem, recipeList);
        rvFavourite.setAdapter(mFavouriteAdapter);

        int gridColumnSpanCount = 1;
        rvFavourite.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(RecipeAppConstants.FAVOURITE_LIST_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String selection =RecipeContract.RecipeEntry.COLUMN_IS_FAVOURITE+"=?";
        String[] selectionArgs =new String[] {"1"};
        return new CursorLoader(getContext(),
                RecipeContract.RecipeEntry.CONTENT_URI,
                null,
                selection,
                selectionArgs,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<RecipeVO> recipeList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                RecipeVO recipe = RecipeVO.parseFromCursor(data);
                // set photo
                recipe.setPhotos(RecipeVO.loadRecipeImagesByTitle(recipe.getRecipe_title()));
                // set category
                recipe.setCategory(RecipeVO.loadCategoryByCategoryId(String.valueOf(recipe.getCategory().getCategory_id())));
                // set presenter
                if (recipe.getPresenter().getPresenter_id() != null) {
                    recipe.setPresenter(RecipeVO.loadPresenterByPresenterId(String.valueOf(recipe.getPresenter().getPresenter_id())));
                }
                // set ingredients
                recipe.setIngredients(RecipeVO.loadRecipeIngredientsByRecipeId(String.valueOf(recipe.getRecipe_id())));

                // set instructions
                recipe.setInstructions(RecipeVO.loadRecipeInstructionsByRecipeId(String.valueOf(recipe.getRecipe_id())));

                recipeList.add(recipe);
            } while (data.moveToNext());
        }

        Log.d(RecipesApp.TAG, "Retrieved recipes  : " + recipeList.size());
        mFavouriteAdapter.setNewData(recipeList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    protected void onSendScreenHit() {

    }
}
