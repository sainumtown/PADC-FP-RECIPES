package com.padc.recipes.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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

public class ShoppingListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_recipe_title)
    TextView tvRecipeTitle;

    private RecipeVO mRecipe;

    public ShoppingListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(RecipeVO recipeVO) {
        mRecipe = recipeVO;
        tvRecipeTitle.setText(recipeVO.getRecipe_title());
        materailCheckBoxSetting(itemView);
    }

    private void materailCheckBoxSetting(View view) {
        LinearLayout llShoppingList = (LinearLayout) view.findViewById(R.id.ll_tile_remove);
        /*final String[] materiaLArray = view.getResources().getStringArray(R.array.material_list);*/
        final List<IngredientVO> ingredients = mRecipe.getIngredients();

        for (int i = 0; i < ingredients.size(); i++) {
            MMCheckBox cbMaterial = new MMCheckBox(RecipesApp.getContext());
            cbMaterial.setTextColor(view.getResources().getColor(R.color.text_black_ish));
            cbMaterial.setButtonDrawable(R.drawable.checkbox);

            cbMaterial.setPadding(10, 10, 10, 10);
            String ingredietDescription = ingredients.get(i).getIngredient_name() + " " + ingredients.get(i).getMeasurement().toString();
            cbMaterial.setText(ingredietDescription);

            cbMaterial.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            final int finalI = i;
            cbMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Toast.makeText(RecipesApp.getContext(), ingredients.get(finalI).getIngredient_name().toString(), Toast.LENGTH_LONG).show();
                }
            });


            llShoppingList.addView(cbMaterial);
        }
    }

}
