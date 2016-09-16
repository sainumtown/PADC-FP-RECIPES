package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.recipes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingListViewHolder extends RecyclerView.ViewHolder {


    public ShoppingListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }
}
