package com.padc.recipes.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.padc.recipes.R;
import com.padc.recipes.adapters.FoodAdapter;
import com.padc.recipes.adapters.TownshipAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by asus on 9/11/2016.
 */
public class ShareDialog extends DialogFragment {
    private TownshipAdapter mTownshipAdapter;
    private FoodAdapter mFoodAdapter;

    public ShareDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.restaurant_search_dialog, container, false);
        getDialog().setTitle(getString(R.string.restaurnat_search_dialog_title));

        // set the custom dialog components - spinner and button
        // for data spinner township
        String[] townShipArrayList = getResources().getStringArray(R.array.township_list);
        List<String> townshipList = new ArrayList<>(Arrays.asList(townShipArrayList));
        mTownshipAdapter = new TownshipAdapter(townshipList);

        // for data spinner food
        String[] foodArrayList = getResources().getStringArray(R.array.food_list);
        List<String> foodList = new ArrayList<>(Arrays.asList(foodArrayList));
        mFoodAdapter = new FoodAdapter(foodList);

        // connect spinner township with adapter
        Spinner spnTownShip = (Spinner) v.findViewById(R.id.sp_township);
        spnTownShip.setAdapter(mTownshipAdapter);

        // connect spinner food with adapter
        Spinner spnFood = (Spinner) v.findViewById(R.id.sp_food);
        spnFood.setAdapter(mFoodAdapter);

        // if search button click , show the search data to the restaurant list
        Button btnSearch = (Button) v.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return v;
    }
}
