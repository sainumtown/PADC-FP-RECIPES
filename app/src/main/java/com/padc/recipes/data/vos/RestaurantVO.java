package com.padc.recipes.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.data.persistence.RecipeContract;
import com.padc.recipes.utils.RecipeAppConstants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    @SerializedName("description")
    private String description;

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

    public void setTownship(TownshipVO township) {
        this.township = township;
    }

    public void setService_time(ServiceTimeVO service_time) {
        this.service_time = service_time;
    }

    public void setMost_popular_recipes(List<MostPopularRecipeVO> most_popular_recipes) {
        this.most_popular_recipes = most_popular_recipes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void saveRestaurants(List<RestaurantVO> restaurantList) {
        Context context = RecipesApp.getContext();
        ContentValues[] restaurantCVs = new ContentValues[restaurantList.size()];
        for (int index = 0; index < restaurantList.size(); index++) {
            RestaurantVO restaurant = restaurantList.get(index);
            restaurantCVs[index] = restaurant.parseToContentValues();

            //Bulk insert into restaurant_images.
            RestaurantVO.saveRestaurantImages(restaurant.getRestaurant_id(), restaurant.getPhotos());

            // insert into township
            if (restaurant.getTownship() != null) {
                restaurant.saveTownship(restaurant.township);
            }

            // insert into service times
            RestaurantVO.saveRestaurantServiceTime(restaurant);

            // insert into recommended foods
            RestaurantVO.saveRestaurantRecommendedFoods(restaurant);
        }

        //Bulk insert into restaurants.
        int insertedCount = context.getContentResolver().bulkInsert(RecipeContract.RestaurantEntry.CONTENT_URI, restaurantCVs);

        Log.d(RecipesApp.TAG, "Bulk inserted into restaurant table : " + insertedCount);

    }

    private static void saveRestaurantRecommendedFoods(RestaurantVO restaurant) {
        ContentValues[] restaurantReommendedFoodsCVs = new ContentValues[restaurant.getMost_popular_recipes().size()];
        for (int index = 0; index < restaurant.getMost_popular_recipes().size(); index++) {
            MostPopularRecipeVO recipe = restaurant.getMost_popular_recipes().get(index);

            ContentValues cv = new ContentValues();
            cv.put(RecipeContract.RestaurantRecommendedFoodEntry.COLUMN_RESTAURANT_ID, restaurant.getRestaurant_id());
            cv.put(RecipeContract.RestaurantRecommendedFoodEntry.COLUMN_RECIPE_NAME, recipe.getRecipe_name());
            cv.put(RecipeContract.RestaurantRecommendedFoodEntry.COLUMN_RECIPE_ID, recipe.getRecipe_id());
            cv.put(RecipeContract.RestaurantRecommendedFoodEntry.COLUMN_PHOTO, recipe.getPhotos()[0]);

            restaurantReommendedFoodsCVs[index] = cv;
        }

        Context context = RecipesApp.getContext();
        int insertedCount = context.getContentResolver().bulkInsert(RecipeContract.RestaurantRecommendedFoodEntry.CONTENT_URI, restaurantReommendedFoodsCVs);
        Log.d(RecipesApp.TAG, "Bulk inserted into restaurants_recommended_foods table : " + insertedCount);
    }

    private static void saveRestaurantServiceTime(RestaurantVO restaurant) {
        ContentValues restaurantServiceTimeCV = new ContentValues();


        restaurantServiceTimeCV.put(RecipeContract.RestaurantServiceTimeEntry.COLUMN_RESTAURANT_ID, restaurant.getRestaurant_id());
        restaurantServiceTimeCV.put(RecipeContract.RestaurantServiceTimeEntry.COLUMN_START, restaurant.getService_time().getStart());
        restaurantServiceTimeCV.put(RecipeContract.RestaurantServiceTimeEntry.COLUMN_FINISH, restaurant.getService_time().getFinish());

        Context context = RecipesApp.getContext();
        Uri insertedUri = context.getContentResolver().insert(RecipeContract.RestaurantServiceTimeEntry.CONTENT_URI, restaurantServiceTimeCV);

        Log.d(RecipesApp.TAG, "Township Inserted Uri : " + insertedUri);
    }

    private ContentValues parseToContentValues() {

        ContentValues cv = new ContentValues();
        cv.put(RecipeContract.RestaurantEntry.COLUMN_RESTAURANT_ID, restaurant_id);
        cv.put(RecipeContract.RestaurantEntry.COLUMN_RESTAURANT_NAME, restaurant_name);
        String phoneNumber = Arrays.toString(phone_number).substring(1,Arrays.toString(phone_number).length()-1);
        cv.put(RecipeContract.RestaurantEntry.COLUMN_PHONES, phoneNumber);
        cv.put(RecipeContract.RestaurantEntry.COLUMN_BRANCH_NAME, branch_name);
        cv.put(RecipeContract.RestaurantEntry.COLUMN_ADDRESS, address);
        cv.put(RecipeContract.RestaurantEntry.COLUMN_FACEBOOK, facebook);
        cv.put(RecipeContract.RestaurantEntry.COLUMN_TOWNSHIP_ID, getTownship().getTownship_id());
        cv.put(RecipeContract.RestaurantEntry.COLUMN_DESCRIPTION, description);

        return cv;
    }

    public static RestaurantVO parseFromCursor(Cursor data) {

        RestaurantVO restaurant = new RestaurantVO();
        restaurant.restaurant_id = Integer.parseInt(data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_RESTAURANT_ID)));
        restaurant.restaurant_name = data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_RESTAURANT_NAME));
        String phone_numbers =(data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_PHONES)));
        if(phone_numbers.contains(",")){
            restaurant.phone_number = phone_numbers.split(",");
        }
        else {
            restaurant.phone_number =new String[1];
            restaurant.phone_number[0] = phone_numbers;
        }

        restaurant.branch_name = data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_BRANCH_NAME));
        restaurant.address = data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_ADDRESS));
        restaurant.facebook = data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_FACEBOOK));

        int townshipId = Integer.parseInt(data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_TOWNSHIP_ID)));
        TownshipVO township = new TownshipVO();
        township.setTownship_id(townshipId);
        restaurant.township = township;

        restaurant.description = data.getString(data.getColumnIndex(RecipeContract.RestaurantEntry.COLUMN_DESCRIPTION));;

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

    private void saveTownship(TownshipVO township) {
        ContentValues townshipCV = new ContentValues();

        townshipCV.put(RecipeContract.TownshipEntry.COLUMN_TOWNSHIP_ID, township.getTownship_id());
        townshipCV.put(RecipeContract.TownshipEntry.COLUMN_TOWNSHIP_NAME, township.getTownship_name());

        Context context = RecipesApp.getContext();
        Uri insertedUri = context.getContentResolver().insert(RecipeContract.TownshipEntry.CONTENT_URI, townshipCV);

        Log.d(RecipesApp.TAG, "Township Inserted Uri : " + insertedUri);

    }

    public TownshipVO loadTownshipByTownshipId(String townshipId) {
        Context context = RecipesApp.getContext();
        TownshipVO township = new TownshipVO();

        Cursor cursor = context.getContentResolver().query(RecipeContract.TownshipEntry.buildTownshipUriWithId(townshipId),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                township.setTownship_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(RecipeContract.TownshipEntry.COLUMN_TOWNSHIP_ID))));
                township.setTownship_name(cursor.getString(cursor.getColumnIndex(RecipeContract.TownshipEntry.COLUMN_TOWNSHIP_NAME)));

            } while (cursor.moveToNext());
        }

        return township;
    }

    public ServiceTimeVO loadRestaurantServiceTimeByRestaurantId(int restaurant_id) {

        Context context = RecipesApp.getContext();
        ServiceTimeVO serviceTime = new ServiceTimeVO();

        Cursor cursor = context.getContentResolver().query(RecipeContract.RestaurantServiceTimeEntry.buildRestaurantServiceTimesUriWithId(String.valueOf(restaurant_id)),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                serviceTime.setStart(cursor.getString(cursor.getColumnIndex(RecipeContract.RestaurantServiceTimeEntry.COLUMN_START)));
                serviceTime.setFinish(cursor.getString(cursor.getColumnIndex(RecipeContract.RestaurantServiceTimeEntry.COLUMN_FINISH)));

            } while (cursor.moveToNext());
        }

        return serviceTime;
    }

    public List<MostPopularRecipeVO> loadRestaurantRecommendedFoodByRestaurantId(int restaurant_id) {
        Context context = RecipesApp.getContext();
        ArrayList<MostPopularRecipeVO> mostPopularRecipes = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(RecipeContract.RestaurantRecommendedFoodEntry.buildRestaurantRecommendedFoodUriWithId(String.valueOf(restaurant_id)),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                MostPopularRecipeVO mostPopularRecipe = new MostPopularRecipeVO();
                mostPopularRecipe.setRecipe_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(RecipeContract.RestaurantRecommendedFoodEntry.COLUMN_RECIPE_ID))));
                mostPopularRecipe.setRecipe_name(cursor.getString(cursor.getColumnIndex(RecipeContract.RestaurantRecommendedFoodEntry.COLUMN_RECIPE_NAME)));

                String photo = cursor.getString(cursor.getColumnIndex(RecipeContract.RestaurantRecommendedFoodEntry.COLUMN_PHOTO));
                String[] photos = {photo};
                mostPopularRecipe.setPhotos(photos);

                mostPopularRecipes.add(mostPopularRecipe);

            } while (cursor.moveToNext());
        }

        return mostPopularRecipes;
    }
}
