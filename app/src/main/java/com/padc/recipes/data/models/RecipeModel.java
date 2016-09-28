package com.padc.recipes.data.models;

import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.events.DataEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by sainumtown on 9/24/16.
 */
public class RecipeModel extends BaseModel {

    private static RecipeModel objInstance;

    private List<RecipeVO> mRecipeList;

    public static RecipeModel getInstance() {
        if (objInstance == null) {
            objInstance = new RecipeModel();
        }
        return objInstance;
    }

    public RecipeModel() {
        super();
        mRecipeList = new ArrayList<>();
        /*loadRecipes();*/
    }

    public void loadRecipes() {
        dataAgent.loadRecipes();
    }

    public List<RecipeVO> getRecipeList() {
        return mRecipeList;
    }

    public void notifyRecipesLoaded(List<RecipeVO> recipeList) {
        //Notify that the data is ready - using LocalBroadcast
        mRecipeList = recipeList;

        //keep the data in persistent layer.
        RecipeVO.saveRecipes(mRecipeList);

        // broadcastAttractionLoadedWithEventBus();
    }

    public void notifyErrorInLoadingRecipes(String message) {

    }

    private void broadcastAttractionLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.RecipeDataLoadedEvent("extra-in-broadcast", mRecipeList));
    }

    public void setStoredData(List<RecipeVO> recipeList) {
        mRecipeList = recipeList;
    }

    public List<RecipeVO> filterByCategory(String filterCategory) {
        List<RecipeVO> filterRecipes = new ArrayList<>();
        for (int i = 0; i < mRecipeList.size(); i++) {
            RecipeVO recipe = mRecipeList.get(i);
            if (recipe.getCategory().getCategory_name().equals(filterCategory)) {
                filterRecipes.add(recipe);
            }
        }
        return filterRecipes;
    }
}
