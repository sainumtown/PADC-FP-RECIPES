package com.padc.recipes.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.components.MMCheckBox;
import com.padc.recipes.views.holders.ShoppingListViewHolder;


public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListViewHolder> {

    private LayoutInflater mInflater;


    public ShoppingListAdapter() {
        mInflater = LayoutInflater.from(RecipesApp.getContext());
    }

    @Override
    public ShoppingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_shopping_list, parent, false);

        materailCheckBoxSetting( itemView);

        return new ShoppingListViewHolder(itemView);
    }

    private void materailCheckBoxSetting(View view) {
        LinearLayout llShoppingList = (LinearLayout) view.findViewById(R.id.ll_shopping_list);
        final String[] materiaLArray = view.getResources().getStringArray(R.array.material_list);

        for (int i = 0; i < materiaLArray.length; i++) {
            MMCheckBox cbMaterial = new MMCheckBox(RecipesApp.getContext());
            cbMaterial.setTextColor(view.getResources().getColor(R.color.text_black_ish));
            cbMaterial.setButtonDrawable(R.drawable.checkbox);
             int p = R.dimen.padding_medium;
            cbMaterial.setPadding(10,10,50,10);
            cbMaterial.setText(materiaLArray[i]);

            final int finalI = i;
            cbMaterial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RecipesApp.getContext(),materiaLArray[finalI].toString(),Toast.LENGTH_LONG).show();
                }
            });
            llShoppingList.addView(cbMaterial);
        }
    }

    @Override
    public void onBindViewHolder(ShoppingListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }


}
