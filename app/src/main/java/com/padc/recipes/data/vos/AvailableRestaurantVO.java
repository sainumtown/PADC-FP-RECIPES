package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sainumtown on 9/24/16.
 */
public class AvailableRestaurantVO {

    @SerializedName("restaurants-id")
    private int restaurants_id;

    @SerializedName("restaurants-name")
    private String restaurants_name;

    @SerializedName("image")
    private String image;

    public int getRestaurants_id() {
        return restaurants_id;
    }

    public String getRestaurants_name() {
        return restaurants_name;
    }

    public String getImage() {
        return image;
    }
}
