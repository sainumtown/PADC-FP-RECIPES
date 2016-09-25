package com.padc.recipes.data.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.data.vos.RestaurantVO;

import java.util.ArrayList;

/**
 * Created by sainumtown on 9/24/16.
 */
public class RestaurantListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("restaurants")
    private ArrayList<RestaurantVO> restaurantList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<RestaurantVO> getRestaurantList() {
        return restaurantList;
    }
}
