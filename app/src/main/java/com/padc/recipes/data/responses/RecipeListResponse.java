package com.padc.recipes.data.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.recipes.data.vos.RecipeVO;

import java.util.ArrayList;

/**
 * Created by sainumtown on 9/24/16.
 */
public class RecipeListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("recipes")
    private ArrayList<RecipeVO> recipeList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<RecipeVO> getRecipeList() {
        return recipeList;
    }
}
