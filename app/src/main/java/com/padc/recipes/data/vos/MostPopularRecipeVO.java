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

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }
}
