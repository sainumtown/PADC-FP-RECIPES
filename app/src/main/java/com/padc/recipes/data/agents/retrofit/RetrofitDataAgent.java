package com.padc.recipes.data.agents.retrofit;

import com.padc.recipes.data.agents.RecipeDataAgent;
import com.padc.recipes.data.models.RecipeModel;
import com.padc.recipes.data.models.RestaurantModel;
import com.padc.recipes.data.responses.RecipeListResponse;
import com.padc.recipes.data.responses.RestaurantListResponse;
import com.padc.recipes.utils.CommonInstances;
import com.padc.recipes.utils.RecipeAppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by sainumtown on 9/24/16.
 */
public class RetrofitDataAgent implements RecipeDataAgent {

    private static RetrofitDataAgent objInstance;

    private final RecipeApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecipeAppConstants.ATTRACTION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(RecipeApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadRecipes(){
        Call<RecipeListResponse> loadRecipeCall = theApi.loadRecipes(RecipeAppConstants.ACCESS_TOKEN);
        loadRecipeCall.enqueue(new Callback<RecipeListResponse>() {
            @Override
            public void onResponse(Call<RecipeListResponse> call, Response<RecipeListResponse> response) {
                RecipeListResponse recipeListResponse = response.body();
                if (recipeListResponse == null) {
                    RecipeModel.getInstance().notifyErrorInLoadingRecipes(response.message());
                } else {
                    RecipeModel.getInstance().notifyRecipesLoaded(recipeListResponse.getRecipeList());
                }
            }

            @Override
            public void onFailure(Call<RecipeListResponse> call, Throwable throwable) {
                RecipeModel.getInstance().notifyErrorInLoadingRecipes(throwable.getMessage());
            }
        });
    }

    @Override
    public void loadRestaurants() {
        Call<RestaurantListResponse> loadRestaurantCall = theApi.loadRestaurants(RecipeAppConstants.ACCESS_TOKEN);
        loadRestaurantCall.enqueue(new Callback<RestaurantListResponse>() {
            @Override
            public void onResponse(Call<RestaurantListResponse> call, Response<RestaurantListResponse> response) {
                RestaurantListResponse restaurantListResponse = response.body();
                if(restaurantListResponse == null){
                    RestaurantModel.getInstance().notifyErrorInLoadingRestaurant(response.message());
                }else {
                    RestaurantModel.getInstance().notifyRestaurantLoaded(restaurantListResponse.getRestaurantList());
                }
            }

            @Override
            public void onFailure(Call<RestaurantListResponse> call, Throwable t) {
                RestaurantModel.getInstance().notifyErrorInLoadingRestaurant(t.getMessage());
            }
        });

    }
}
