package com.padc.recipes.data.models;

import com.padc.recipes.data.models.BaseModel;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.data.vos.RestaurantVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sainumtown on 9/24/16.
 */
public class RestaurantModel extends BaseModel{
    private static RestaurantModel objInstance;

    private List<RestaurantVO> mRestaurantList;

    public static RestaurantModel getInstance() {
        if (objInstance == null) {
            objInstance = new RestaurantModel();
        }
        return objInstance;
    }

    public RestaurantModel() {
        super();
        mRestaurantList = new ArrayList<>();
    }

    public void loadRestaurants() {
        dataAgent.loadRestaurants();
    }

    public List<RestaurantVO> getRestaurantList() {
        return mRestaurantList;
    }

    public void notifyRestaurantLoaded(List<RestaurantVO> restaurantList) {
        //Notify that the data is ready - using LocalBroadcast
        mRestaurantList = restaurantList;

        //keep the data in persistent layer.
        // RecipeVO.saveAttractions(mRecipeList);

        //broadcastAttractionLoadedWithEventBus();
    }
}
