package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sainumtown on 9/24/16.
 */
public class MostPopularRecipeVO {

    @SerializedName("recipe-id")
    private int recipe_id;

    @SerializedName("recipe-name")
    private String recipe_name;

    @SerializedName("photos")
    private String[] photos;

    public int getRecipe_id() {
        return recipe_id;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public String[] getPhotos() {
        return photos;
    }
}
