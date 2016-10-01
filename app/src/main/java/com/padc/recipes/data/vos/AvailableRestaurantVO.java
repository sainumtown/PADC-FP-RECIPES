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

    public void setRestaurants_id(int restaurants_id) {
        this.restaurants_id = restaurants_id;
    }

    public void setRestaurants_name(String restaurants_name) {
        this.restaurants_name = restaurants_name;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
