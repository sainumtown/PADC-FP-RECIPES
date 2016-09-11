package com.padc.recipes.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.padc.recipes.R;
import com.padc.recipes.adapters.RecipeAdapter;
import com.padc.recipes.views.holders.RecipeViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecipeListFragment extends Fragment {

    @BindView(R.id.rv_recipes)
    RecyclerView rvRecipes;

    private RecipeAdapter mRecipeAdapter;
    RecipeViewHolder.ControllerRecipeItem mControllerRecipeItem;

    public RecipeListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerRecipeItem = (RecipeViewHolder.ControllerRecipeItem) context;
    }

    public static RecipeListFragment newInstance() {
        Bundle args = new Bundle();

        RecipeListFragment fragment = new RecipeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);

        mRecipeAdapter = new RecipeAdapter(mControllerRecipeItem);
        rvRecipes.setAdapter(mRecipeAdapter);

        int gridColumnSpanCount = 1;
        rvRecipes.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.spinner_filter_category).setVisible(true);
        super.onPrepareOptionsMenu(menu);

    }
}
