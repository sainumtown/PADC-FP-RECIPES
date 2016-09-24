package com.padc.recipes.data.agents;

import com.google.gson.reflect.TypeToken;
import com.padc.recipes.data.models.RecipeModel;
import com.padc.recipes.data.models.RestaurantModel;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.data.vos.RestaurantVO;
import com.padc.recipes.utils.CommonInstances;
import com.padc.recipes.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by sainumtown on 9/24/16.
 */
public class OfflineDataAgent implements RecipeDataAgent {


    private static final String OFFLINE_RECIPE_LIST = "recipes.json";
    private static final String OFFLINE_RESTAURANT_LIST = "restaurants.json";

    private static OfflineDataAgent objInstance;

    private OfflineDataAgent() {

    }

    public static OfflineDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new OfflineDataAgent();
        }

        return objInstance;
    }
    @Override
    public void loadRecipes() {

        try {
            String recipes = JsonUtils.getInstance().loadDummyData(OFFLINE_RECIPE_LIST);
            Type listType = new TypeToken<List<RecipeVO>>() {
            }.getType();
            List<RecipeVO> recipeList = CommonInstances.getGsonInstance().fromJson(recipes, listType);

            RecipeModel.getInstance().notifyRecipesLoaded(recipeList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadRestaurants() {
        try {
            String restaurants = JsonUtils.getInstance().loadDummyData(OFFLINE_RESTAURANT_LIST);
            Type listType = new TypeToken<List<RestaurantVO>>() {
            }.getType();
            List<RestaurantVO> restaurantList = CommonInstances.getGsonInstance().fromJson(restaurants, listType);

            RestaurantModel.getInstance().notifyRestaurantLoaded(restaurantList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
