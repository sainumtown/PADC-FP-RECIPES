package com.padc.recipes.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.recipes.R;
import com.padc.recipes.adapters.ShoppingListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingListFragment extends Fragment {

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

        mShoppingListAdapter = new ShoppingListAdapter();
        rvShoppingList.setAdapter(mShoppingListAdapter);

        int gridColumnSpanCount = 1;
        rvShoppingList.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }

}
