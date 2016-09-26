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

    public static final String PATH_RECIPES= "recipes";
    public static final String PATH_RECIPE_IMAGES = "recipe_images";


    public static final class RecipeEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECIPES;

        public static final String TABLE_NAME = "recipes";

        public static final String COLUMN_ID    = "recipe_id";
        public static final String COLUMN_TITLE = "recipe_title";
        public static final String COLUMN_NOTE = "note";
        public static final String COLUMN_VIDEO ="video";
        public static final String COLUMN_CATEGORY_ID ="category_id";
        public static final String COLUMN_PRESENTER_ID ="presenter_id";

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
}
