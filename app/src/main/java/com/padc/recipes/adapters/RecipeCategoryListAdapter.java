package com.padc.recipes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.views.items.ViewItemRecipeCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 9/11/2016.
 */
public class RecipeCategoryListAdapter extends BaseAdapter {

    private List<String> mRecipeCategoryList;
    private LayoutInflater mInflater;

    public RecipeCategoryListAdapter(List<String> recipeCategoryList) {
        if (recipeCategoryList != null) {
            this.mRecipeCategoryList = recipeCategoryList;
        } else {
            this.mRecipeCategoryList = new ArrayList<>();
        }
        mInflater = LayoutInflater.from(RecipesApp.getContext());
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = mRecipeCategoryList.size();
        return count > 0 ? count - 1 : count;
    }

    @Override
    public String getItem(int position) {
        return mRecipeCategoryList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mInflater.inflate(R.layout.view_item_recipe_category, viewGroup, false);
        }

        if (view instanceof ViewItemRecipeCategory) {
            ViewItemRecipeCategory viewItemRecipeCategory = (ViewItemRecipeCategory) view;
            viewItemRecipeCategory.setData(getItem(i));
        }
        return view;
    }
}
