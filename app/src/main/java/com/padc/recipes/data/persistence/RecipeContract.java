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

        public static final String COLUMN_DESC = "desc";

        public static Uri buildRecipeUri(long id) {
            //content://com.padc.recipes/recipes/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildAttractionUriWithTitle(String attractionTitle) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions?title="Yangon"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TITLE, attractionTitle)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }
    }

  /*  public static final class AttractionImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ATTRACTION_IMAGES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ATTRACTION_IMAGES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ATTRACTION_IMAGES;

        public static final String TABLE_NAME = "attraction_images";

        public static final String COLUMN_ATTRACTION_TITLE = "attraction_title";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildAttractionImageUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildAttractionImageUriWithTitle(String attractionTitle) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attraction_images?attraction_title=Yangon
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_ATTRACTION_TITLE, attractionTitle)
                    .build();
        }

        public static String getAttractionTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_ATTRACTION_TITLE);
        }
    }
*/

}
