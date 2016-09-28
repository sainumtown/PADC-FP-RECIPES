package com.padc.recipes.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.padc.recipes.RecipesApp;


/**
 * Created by aung on 7/9/16.
 */
public class RecipeContract {

    public static final String CONTENT_AUTHORITY = RecipesApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RECIPES = "recipes";
    public static final String PATH_RECIPE_IMAGES = "recipe_images";
    public static final String PATH_CATEGORIES = "categories";
    public static final String PATH_PRESENTERS = "presenters";
    public static final String PATH_INGREDIENTS = "ingredients";
    public static final String PATH_RECIPE_INSTRUCTIONS = "recipe_instructions";

    public static final String PATH_RESTAURANTS = "restaurants";
    public static final String PATH_RESTAURANT_IMAGES = "restaurant_images";
    public static final String PATH_TOWNSHIP = "townships";
    public static final String PATH_RESTAURANT_SERVICE_TIME = "restaurant_service_times";
    public static final String PATH_RESTAURANT_RECOMMENDED_FOODS = "restaurant_recommended_foods";


    public static final class RecipeEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPES;

        public static final String TABLE_NAME = "recipes";

        public static final String COLUMN_ID = "recipe_id";
        public static final String COLUMN_TITLE = "recipe_title";
        public static final String COLUMN_NOTE = "note";
        public static final String COLUMN_VIDEO = "video";
        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_PRESENTER_ID = "presenter_id";
        public static final String COLUMN_IS_FAVOURITE = "is_favourite";

        public static Uri buildRecipeUri(long id) {
            //content://com.padc.recipes/recipes/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRecipeUriWithTitle(String recipeTitle) {
            //content://com.padc.recipes/recipes?title="Something"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TITLE, recipeTitle)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }
    }

    public static final class RecipeImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPE_IMAGES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPE_IMAGES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPE_IMAGES;

        public static final String TABLE_NAME = "recipe_images";

        public static final String COLUMN_RECIPE_TITLE = "recipe_title";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildRecipeImageUri(long id) {
            // content://com.padc.recipes/recipe_images/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRecipeImageUriWithTitle(String recipeTitle) {
            //content://com.padc.recipes/recipe_images/?recipe_title=something
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_RECIPE_TITLE, recipeTitle)
                    .build();
        }

        public static String getRecipeTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_RECIPE_TITLE);
        }

    }

    public static final class CategoryEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CATEGORIES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGORIES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGORIES;

        public static final String TABLE_NAME = "categories";

        public static final String COLUMN_CATEGORY_ID = "category_id";
        public static final String COLUMN_CATEGORY_NAME = "category_name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildCategoryUri(long id) {
            // content://com.padc.recipes/category_id/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildCategoryUriWithId(String categoryId) {
            //content://com.padc.recipes/categories/?category_id=2
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_CATEGORY_ID, categoryId)
                    .build();
        }


        public static String getCategoryIDFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_CATEGORY_ID);
        }

    }

    // Presenter
    public static final class PresenterEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PRESENTERS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRESENTERS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRESENTERS;

        public static final String TABLE_NAME = "presenters";

        public static final String COLUMN_PRESENTER_ID = "presenter_id";
        public static final String COLUMN_PRESENTER_NAME = "presenter_name";

        public static Uri builPresenterUri(long id) {
            // content://com.padc.recipes/presenters_id/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildPresenteriWithId(String presenter_id) {
            //content://com.padc.recipes/presnters/?presenter_id=2
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_PRESENTER_ID, presenter_id)
                    .build();
        }

        public static String getPresenterIDFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_PRESENTER_ID);
        }
    }

    //  1. ingredients
    public static final class IngredientEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_INGREDIENTS).build();

        // return type many
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INGREDIENTS;

        // return type one
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INGREDIENTS;

        // 2.  Table name and Column
        // table name is ingredients
        public static final String TABLE_NAME = "recipes_ingredients";

        public static final String COLUMN_RECIPE_ID = "recipe_id";                      // recipe id
        public static final String COLUMN_INGREDIENT_ID = "ingredient_id";              // id
        public static final String COLUMN_INGREDIENT_NAME = "ingredient_name";          // name
        public static final String COLUMN_MEASUREMENT = "measurement";                  // measurement
        public static final String COLUMN_NOTE = "note";                                // note
        public static final String COLUMN_IMAGE_URL = "image_url";                      // image url

        // 3 . build for parameter with content uri
        // generate the contentUri plus table name
        public static Uri buildIngredientUri(long id) {
            // content://com.padc.recipes/ingredients/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildIngredientWithRecipeId(String recipeId) {
            //content://com.padc.recipes/ingredients/?recipe_id=2
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_RECIPE_ID, recipeId)
                    .build();
        }

        // content://com.padc.recipes/ingredients/?recipe_id=2
        // get the parameter from the uri ( parameter is 2)
        public static String getRecipeIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_RECIPE_ID);
        }

    }

    //  1. ingredients
    public static final class InstructionEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPE_INSTRUCTIONS).build();

        // return type many
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPE_INSTRUCTIONS;

        // return type one
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPE_INSTRUCTIONS;

        // 2.  Table name and Column
        // table name is recipes_instructions
        public static final String TABLE_NAME = "recipes_instructions";

        public static final String COLUMN_RECIPE_ID = "recipe_id";                          // recipe id
        public static final String COLUMN_INSTRUCTION_DESC = "instruction_desc";            // instruction
        public static final String COLUMN_INSTRUCTION_IMAGE = "image_url";                  // image url
        public static final String COLUMN_SORT_ORDER= "sort_order";                         // sort order

        // 3 . build for parameter with content uri
        // generate the contentUri plus table name
        public static Uri buildInstructionUri(long id) {
            // content://com.padc.recipes/instructions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildInstructionWithRecipeId(String recipeId) {
            //content://com.padc.recipes/instruction/?recipe_id=2
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_RECIPE_ID, recipeId)
                    .build();
        }

        // content://com.padc.recipes/instructions/?recipe_id=2
        // get the parameter from the uri ( parameter is 2)
        public static String getRecipeIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_RECIPE_ID);
        }
    }

    //  Restaurants
    public static final class RestaurantEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANTS).build();

        // return type many
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        // return type one
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        // 2.  Table name and Column
        // table name is restaurant
        public static final String TABLE_NAME = "restaurants";

        public static final String COLUMN_RESTAURANT_ID = "restaurant_id";                          // restaurant id
        public static final String COLUMN_RESTAURANT_NAME = "restaurant_name";                      // name
        public static final String COLUMN_PHONES = "phones";                                        // phones
        public static final String COLUMN_BRANCH_NAME = "branch_ame";                               // branch name
        public static final String COLUMN_ADDRESS= "address";                                       // address
        public static final String COLUMN_FACEBOOK= "facebook";                                     // facebook
        public static final String COLUMN_TOWNSHIP_ID= "township_id";                               // townshipId

        // 3 . build for parameter with content uri
        // generate the contentUri plus table name
        public static Uri buildRestaurantUri(long id) {
            // content://com.padc.recipes/restaurants/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRestaurantWithRestaurantId(String restaurantId) {
            //content://com.padc.recipes/restaurant/?restaurant_id=2
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_RESTAURANT_ID, restaurantId)
                    .build();
        }

        // content://com.padc.recipes/restaurant/?restaurant_id=2
        // get the parameter from the uri ( parameter is 2)
        public static String getRestaurantIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_RESTAURANT_ID);
        }
    }

    public static final class RestaurantImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANT_IMAGES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_IMAGES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_IMAGES;

        public static final String TABLE_NAME = "restaurant_images";

        public static final String COLUMN_RESTAURANT_ID = "restaurant_id";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildRestaurantImageUri(long id) {
            // content://com.padc.recipes/restaurant_images/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRestaurantImageUriWithId(String restaurantId) {
            //content://com.padc.recipes/restaurant_images/?restaurant_id=1
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_RESTAURANT_ID, restaurantId)
                    .build();
        }

        public static String getRestaurantIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_RESTAURANT_ID);
        }

    }

    public static final class TownshipEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TOWNSHIP).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TOWNSHIP;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TOWNSHIP;

        public static final String TABLE_NAME = "townships";

        public static final String COLUMN_TOWNSHIP_ID = "township_id";
        public static final String COLUMN_TOWNSHIP_NAME = "township_name";

        public static Uri buildTownshipUri(long id) {
            // content://com.padc.recipes/townships/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildTownshipUriWithId(String categoryId) {
            //content://com.padc.recipes/townships/?township=2
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TOWNSHIP_ID, categoryId)
                    .build();
        }

        public static String getTownshipIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TOWNSHIP_ID);
        }

    }

    public static final class RestaurantServiceTimeEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANT_SERVICE_TIME).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_SERVICE_TIME;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_SERVICE_TIME;

        public static final String TABLE_NAME = "restaurant_serviceTimes";

        public static final String COLUMN_RESTAURANT_ID = "restaurant_id";
        public static final String COLUMN_START = "start";
        public static final String COLUMN_FINISH = "finish";
        public static final String COLUMN_OFF_DAYS = "off_days";

        public static Uri buildRestaurantServiceTimeUri(long id) {
            // content://com.padc.recipes/restaurant_serviceTimes/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRestaurantServiceTimesUriWithId(String restaurantId) {
            //content://com.padc.recipes/restaurant_serviceTimes/?restaurantId=2
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_RESTAURANT_ID, restaurantId)
                    .build();
        }

        public static String getRestaurantIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_RESTAURANT_ID);
        }

    }

    public static final class RestaurantRecommendedFoodEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESTAURANT_RECOMMENDED_FOODS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_RECOMMENDED_FOODS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANT_RECOMMENDED_FOODS;

        public static final String TABLE_NAME = "restaurant_recommended_foods";

        public static final String COLUMN_RESTAURANT_ID = "restaurant_id";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_RECIPE_NAME = "recipe_name";
        public static final String COLUMN_PHOTO = "photo";

        public static Uri buildRestaurantRecommendedFoodUri(long id) {
            // content://com.padc.recipes/restaurant_recommended_foods/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildRestaurantRecommendedFoodUriWithId(String restaurantId) {
            //content://com.padc.recipes/restaurant_recommended_foods/?resaurantId=2
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_RESTAURANT_ID, restaurantId)
                    .build();
        }

        public static String getRestaurantRecommendedFoodIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_RESTAURANT_ID);
        }

    }
}
