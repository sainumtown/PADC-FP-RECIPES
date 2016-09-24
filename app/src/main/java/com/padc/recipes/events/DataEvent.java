package com.padc.recipes.events;

import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.data.vos.RestaurantVO;

import java.util.List;

/**
 * Created by sainumtown on 9/24/16.
 */
public class DataEvent {

    public static class RecipeDataLoadedEvent {

        private String extraMessage;
        private List<RecipeVO> recipeList;

        public RecipeDataLoadedEvent(String extraMessage, List<RecipeVO> recipeList) {
            this.extraMessage = extraMessage;
            this.recipeList = recipeList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<RecipeVO> getRecipeList() {
            return recipeList;
        }
    }

    public static class RestaurantDataLoadedEvent {
        private String extraMessage;
        private List<RestaurantVO> restaurantList;

        public RestaurantDataLoadedEvent(String message, List<RestaurantVO> mRestaurantList) {
            this.extraMessage = message;
            this.restaurantList = mRestaurantList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<RestaurantVO> getRestaurantList() {
            return restaurantList;
        }
    }
}
