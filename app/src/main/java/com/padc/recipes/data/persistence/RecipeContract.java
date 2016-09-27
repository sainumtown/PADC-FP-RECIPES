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
        public static String getIngredientIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_RECIPE_ID);
        }

    }
}
