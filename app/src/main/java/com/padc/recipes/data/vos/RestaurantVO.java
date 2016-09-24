package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sainumtown on 9/24/16.
 */
public class RestaurantVO {

    @SerializedName("restaurant-id")
    private int restaurant_id;

    @SerializedName("restaurant-name")
    private String restaurant_name;

    @SerializedName("branch-name")
    private String branch_name;

    @SerializedName("address")
    private String address;

    @SerializedName("phone-number")
    private String[] phone_number;

    @SerializedName("facebook")
    private String facebook;

    @SerializedName("photos")
    private String[] photos;

    @SerializedName("township")
    private TownshipVO township;

    @SerializedName("map-data")
    private MapDataVO map_data;

    @SerializedName("service-time")
    private ServiceTimeVO service_time;

    @SerializedName("most-popular-recipes")
    private List<MostPopularRecipeVO> most_popular_recipes;

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public String getAddress() {
        return address;
    }

    public String[] getPhone_number() {
        return phone_number;
    }

    public String getFacebook() {
        return facebook;
    }

    public String[] getPhotos() {
        return photos;
    }

    public TownshipVO getTownship() {
        return township;
    }

    public MapDataVO getMap_data() {
        return map_data;
    }

    public ServiceTimeVO getService_time() {
        return service_time;
    }

    public List<MostPopularRecipeVO> getMost_popular_recipes() {
        return most_popular_recipes;
    }
}
