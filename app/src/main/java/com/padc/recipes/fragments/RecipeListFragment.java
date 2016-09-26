package com.padc.recipes.fragments;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.adapters.RecipeAdapter;
import com.padc.recipes.adapters.RecipeCategoryListAdapter;
import com.padc.recipes.data.models.RecipeModel;
import com.padc.recipes.data.persistence.RecipeContract;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.events.DataEvent;
import com.padc.recipes.utils.RecipeAppConstants;
import com.padc.recipes.views.holders.RecipeViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecipeListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>{



    @BindView(R.id.rv_recipes)
    RecyclerView rvRecipes;

    private RecipeAdapter mRecipeAdapter;
    RecipeViewHolder.ControllerRecipeItem mControllerRecipeItem;

    @BindView(R.id.spn_filter_category)
    Spinner spinnerRecipeCategoryFilter;

    private RecipeCategoryListAdapter mRecipeCategoryListAdapter;

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

        // title
        getActivity().setTitle(R.string.home_screen_title);

        // get recipe data
        RecipeModel.getInstance().loadRecipes();
        List<RecipeVO> recipeList = new ArrayList<RecipeVO>();

        mRecipeAdapter = new RecipeAdapter(mControllerRecipeItem,recipeList);
        rvRecipes.setAdapter(mRecipeAdapter);

        // spinner category filter
        filterCategorySetting();

        // grid setting
        int gridColumnSpanCount = 2;
        rvRecipes.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }

    private void filterCategorySetting() {
        // for spinner recipe category filter
        String[] recipesCategoryListArray = getResources().getStringArray(R.array.recipes_category);
        List<String> recipesCategoryList = new ArrayList<>(Arrays.asList(recipesCategoryListArray));
        recipesCategoryList.add(getString(R.string.choose_food));

        mRecipeCategoryListAdapter = new RecipeCategoryListAdapter(recipesCategoryList);
        spinnerRecipeCategoryFilter.setAdapter(mRecipeCategoryListAdapter);
        spinnerRecipeCategoryFilter.setSelection(mRecipeCategoryListAdapter.getCount());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(RecipeAppConstants.RECIPE_LIST_LOADER, null, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        /*menu.findItem(R.id.spinner_filter_category).setVisible(true);*/
        super.onPrepareOptionsMenu(menu);

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
    public void onStop() {
        super.onStop();

        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    public void onEventMainThread(DataEvent.RecipeDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();


        List<RecipeVO> newRecipeList = event.getRecipeList();
        mRecipeAdapter.setNewData(newRecipeList);
    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                RecipeContract.RecipeEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<RecipeVO> recipeList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                RecipeVO recipe = RecipeVO.parseFromCursor(data);
                /*recipe.setImages(AttractionVO.loadAttractionImagesByTitle(attraction.getTitle()));*/
                recipeList.add(recipe);
            } while (data.moveToNext());
        }

        Log.d(RecipesApp.TAG, "Retrieved recipes  : " + recipeList.size());
        mRecipeAdapter.setNewData(recipeList);

        RecipeModel.getInstance().setStoredData(recipeList);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
    @Override
    protected void onSendScreenHit() {

    }
}
