package com.padc.recipes.data.models;

import com.padc.recipes.data.vos.RecipeVO;

import java.util.ArrayList;
import java.util.List;

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
        // RecipeVO.saveAttractions(mRecipeList);

        //broadcastAttractionLoadedWithEventBus();
    }
}
