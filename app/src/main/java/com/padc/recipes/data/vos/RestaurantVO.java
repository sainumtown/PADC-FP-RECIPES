package com.padc.recipes.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.data.persistence.RecipeContract;
import com.padc.recipes.utils.RecipeAppConstants;

import java.util.ArrayList;
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

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public static void saveRestaurants(List<RestaurantVO> restaurantList) {
        Context context = RecipesApp.getContext();
        ContentValues[] restaurantCVs = new ContentValues[restaurantList.size()];
        for (int index = 0; index < restaurantList.size(); index++) {
            RestaurantVO restaurant = restaurantList.get(index);
            restaurantCVs[index] = restaurant.parseToContentValues();

            //Bulk insert into restaurant_images.
            RestaurantVO.saveRestaurantImages(restaurant.getRestaurant_id(), restaurant.getPhotos());
        }

        //Bulk insert into restaurants.
        int insertedCount = context.getContentResolver().bulkInsert(RecipeContract.RestaurantEntry.CONTENT_URI, restaurantCVs);

        Log.d(RecipesApp.TAG, "Bulk inserted into restaurant table : " + insertedCount);

    }

    private ContentValues parseToContentValues() {

        ContentValues cv = new ContentValues();
        cv.put(RecipeContract.RestaurantEntry.COLUMN_RESTAURANT_ID, restaurant_id);
        cv.put(RecipeContract.RestaurantEntry.COLUMN_RESTAURANT_NAME, restaurant_name);
        cv.put(RecipeContract.RestaurantEntry.COLUMN_BRANCH_NAME, branch_name);
        cv.put(RecipeContract.RestaurantEntry.COLUMN_ADDRESS, address);
        cv.put(RecipeContract.RestaurantEntry.COLUMN_FACEBOOK, facebook);

        return cv;
    }

    public static RestaurantVO parseFromCursor(Cursor data) {

        RestaurantVO restaurant = new RestaurantVO();
        restaurant.restaurant_id = Integer.parseInt(data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_RESTAURANT_ID)));
        restaurant.restaurant_name = data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_RESTAURANT_NAME));
        restaurant.branch_name = data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_BRANCH_NAME));
        restaurant.address =data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_ADDRESS));
        restaurant.facebook = data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_FACEBOOK));

        return restaurant;
    }

    private static void saveRestaurantImages(int restaurant_id, String[] images) {
        ContentValues[] restaurantImagesCVs = new ContentValues[images.length];
        for (int index = 0; index < images.length; index++) {
            String image = images[index];

            ContentValues cv = new ContentValues();
            cv.put(RecipeContract.RestaurantImageEntry.COLUMN_RESTAURANT_ID, restaurant_id);
            cv.put(RecipeContract.RestaurantImageEntry.COLUMN_IMAGE, image);

            restaurantImagesCVs[index] = cv;
        }

        Context context = RecipesApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(RecipeContract.RestaurantImageEntry.CONTENT_URI, restaurantImagesCVs);
        Log.d(RecipesApp.TAG, "Bulk inserted into restaurant_images table : " + insertCount);


    }

    public String[] loadRestaurantImagesByRestaurantId(int restaurant_id) {
        Context context = RecipesApp.getContext();
        ArrayList<String> images = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(RecipeContract.RestaurantImageEntry.buildRestaurantImageUriWithId(String.valueOf(restaurant_id)),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                images.add(cursor.getString(cursor.getColumnIndex(RecipeContract.RestaurantImageEntry.COLUMN_IMAGE)));
            } while (cursor.moveToNext());
        }

        String[] imageArray = new String[images.size()];
        images.toArray(imageArray);
        return imageArray;
    }
}
