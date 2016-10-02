package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.components.MMCheckBox;
import com.padc.recipes.data.vos.IngredientVO;
import com.padc.recipes.data.vos.RecipeVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_recipe_title)
    TextView tvRecipeTitle;

    @BindView(R.id.btn_remove_from_shoppingList)
    Button btnRemoveFromShoppingList;

    private RecipeVO mRecipe;
    private ControllerShoppingListItem mController;

    public ShoppingListViewHolder(View itemView,ControllerShoppingListItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
        removeItemFromShoppingList();
    }

    public void bindData(RecipeVO recipeVO) {
        mRecipe = recipeVO;
        tvRecipeTitle.setText(mRecipe.getRecipe_title());
        materailCheckBoxSetting(itemView);

    }

    private void materailCheckBoxSetting(View view) {
        LinearLayout llShoppingList = (LinearLayout) view.findViewById(R.id.ll_tile_remove);
        llShoppingList.removeAllViewsInLayout();

        final List<IngredientVO> ingredients = mRecipe.getIngredients();

        for (int i = 0; i < ingredients.size(); i++) {
            final MMCheckBox cbMaterial = new MMCheckBox(RecipesApp.getContext());
            cbMaterial.setTextColor(view.getResources().getColor(R.color.text_black_ish));
            cbMaterial.setButtonDrawable(R.drawable.checkbox);

            cbMaterial.setPadding(10, 10, 10, 10);
            String ingredietDescription = ingredients.get(i).getIngredient_name() + " " + ingredients.get(i).getMeasurement().toString();
            cbMaterial.setText(ingredietDescription);

            // bought or not
            if(ingredients.get(i).isBought()){
                cbMaterial.setChecked(true);
            }else{
                cbMaterial.setChecked(false);
            }

            cbMaterial.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            final int finalI = i;
            cbMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    cbMaterial.setPaintFlags(1);
                    mController.onCheckShoppingListIngredient(String.valueOf(mRecipe.getRecipe_id())
                            , String.valueOf(ingredients.get(finalI).getIngredient_id())
                            ,ingredients.get(finalI).isBought());
                }
            });

            llShoppingList.addView(cbMaterial);
        }
    }

    private void removeItemFromShoppingList() {
        btnRemoveFromShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.onClickRemoveAllIngredientsShoppingListByRecipeId(String.valueOf(mRecipe.getRecipe_id()));
            }
        });
    }

    @Override
    public void onClick(View v) {

    }


    public interface ControllerShoppingListItem {
        void onCheckShoppingListIngredient(String recipeId, String ingredientId,Boolean flagBought);
        void onClickRemoveAllIngredientsShoppingListByRecipeId(String recipeId);

    }

}
