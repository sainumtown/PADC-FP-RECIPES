package com.padc.recipes.data.agents.retrofit;

import com.padc.recipes.data.responses.RecipeListResponse;
import com.padc.recipes.utils.RecipeAppConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sainumtown on 9/24/16.
 */
public interface RecipeApi {

    @FormUrlEncoded
    @POST(RecipeAppConstants.API_GET_RECIPE_LIST)
    Call<RecipeListResponse> loadRecipes(
            @Field(RecipeAppConstants.PARAM_ACCESS_TOKEN) String param);
}
