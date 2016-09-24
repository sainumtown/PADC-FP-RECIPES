package com.padc.recipes.events;

import com.padc.recipes.data.vos.RecipeVO;

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
}
