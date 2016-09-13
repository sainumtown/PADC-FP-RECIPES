package com.padc.recipes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.views.items.ViewItemFood;
import com.padc.recipes.views.items.ViewItemTownship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 9/11/2016.
 */
public class FoodAdapter extends BaseAdapter {

    private List<String> mFoodList;
    private LayoutInflater mInflater;

    public FoodAdapter(List<String> foodList) {
        if (foodList != null) {
            this.mFoodList = foodList;
        } else {
            this.mFoodList = new ArrayList<>();
        }
        mInflater = LayoutInflater.from(RecipesApp.getContext());
    }

    @Override
    public int getCount() {
        int count = mFoodList.size();
        return count > 0 ? count - 1 : count;
    }

    @Override
    public String getItem(int position) {
        return mFoodList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mInflater.inflate(R.layout.view_item_food, viewGroup, false);
        }

        if (view instanceof ViewItemFood) {
            ViewItemFood viewItemFood = (ViewItemFood) view;
            viewItemFood.setData(getItem(i));
        }
        return view;
    }
}
