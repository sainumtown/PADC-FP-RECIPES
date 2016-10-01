package com.padc.recipes.fragments;


import android.database.Cursor;
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
import com.padc.recipes.adapters.ShoppingListAdapter;
import com.padc.recipes.data.persistence.RecipeContract;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.utils.RecipeAppConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.rv_shopping_list)
    RecyclerView rvShoppingList;


    private ShoppingListAdapter mShoppingListAdapter;

    public ShoppingListFragment() {
        // Required empty public constructor
    }

    public static ShoppingListFragment newInstance() {

        Bundle args = new Bundle();

        ShoppingListFragment fragment = new ShoppingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        ButterKnife.bind(this, view);

        // title
        getActivity().setTitle(R.string.ShoppingList);

        List<RecipeVO> recipeList = new ArrayList<RecipeVO>();

        mShoppingListAdapter = new ShoppingListAdapter(recipeList);
        rvShoppingList.setAdapter(mShoppingListAdapter);

        int gridColumnSpanCount = 1;
        rvShoppingList.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(RecipeAppConstants.SHOPPING_LIST_LOADER, null, this);
    }

    @Override
    protected void onSendScreenHit() {

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        String[] distinct = {"DISTINCT " + RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_RECIPE_ID
            ,RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_RECIPE_TITLE};
        return new CursorLoader(getContext(),
                RecipeContract.ShoppingRecipeIngredientEntry.CONTENT_URI,
                distinct,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor data) {
        List<RecipeVO> recipeList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                RecipeVO recipe = RecipeVO.parseRecipeIdAndTitleFromCursor(data);
                // set ingredients
                recipe.setIngredients(RecipeVO.loadShoppingListRecipeIngredientsByRecipeId(String.valueOf(recipe.getRecipe_id())));

                recipeList.add(recipe);
            } while (data.moveToNext());
        }

        Log.d(RecipesApp.TAG, "Retrieved shopping list recipes ingredients  : " + recipeList.size());
        mShoppingListAdapter.setNewData(recipeList);


    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
