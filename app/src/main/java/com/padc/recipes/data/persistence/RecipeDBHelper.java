package com.padc.recipes.data.persistence;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.padc.recipes.data.persistence.RecipeContract.RecipeEntry;
import com.padc.recipes.data.persistence.RecipeContract.RecipeImageEntry;
import com.padc.recipes.data.persistence.RecipeContract.CategoryEntry;
import com.padc.recipes.data.persistence.RecipeContract.PresenterEntry;


/**
 * Created by sainumtown on 9/36/16.
 */
public class RecipeDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "recipes.db";

    private static final String SQL_CREATE_RECIPE_TABLE = "CREATE TABLE " + RecipeContract.RecipeEntry.TABLE_NAME + " (" +
            RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RecipeEntry.COLUMN_ID + " TEXT NOT NULL, " +
            RecipeEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            RecipeEntry.COLUMN_NOTE + " TEXT NOT NULL, " +
            RecipeEntry.COLUMN_VIDEO + " TEXT, " +
            RecipeEntry.COLUMN_CATEGORY_ID + " TEXT, " +
            RecipeEntry.COLUMN_PRESENTER_ID + " TEXT, " +

            " UNIQUE (" + RecipeEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_RECIPE_IMAGE_TABLE = "CREATE TABLE " + RecipeImageEntry.TABLE_NAME + " (" +
            RecipeImageEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RecipeImageEntry.COLUMN_RECIPE_TITLE + " TEXT NOT NULL, " +
            RecipeImageEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +

            " UNIQUE (" + RecipeImageEntry.COLUMN_RECIPE_TITLE + ", " +
            RecipeImageEntry.COLUMN_IMAGE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " + RecipeContract.CategoryEntry.TABLE_NAME + " (" +
            CategoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CategoryEntry.COLUMN_CATEGORY_ID + " TEXT NOT NULL, " +
            CategoryEntry.COLUMN_CATEGORY_NAME + " TEXT NOT NULL, " +
            CategoryEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
            CategoryEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +


            " UNIQUE (" + CategoryEntry.COLUMN_CATEGORY_NAME + ") ON CONFLICT REPLACE" +
            " );";

    private static final String SQL_CREATE_PRESENTER_TABLE = "CREATE TABLE " + RecipeContract.PresenterEntry.TABLE_NAME + " (" +
            PresenterEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PresenterEntry.COLUMN_PRESENTER_ID + " TEXT NOT NULL, " +
            PresenterEntry.COLUMN_PRESENTER_NAME + " TEXT NOT NULL, " +

            " UNIQUE (" + PresenterEntry.COLUMN_PRESENTER_NAME + ") ON CONFLICT REPLACE" +
            " );";

    public RecipeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_RECIPE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_RECIPE_IMAGE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORY_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PRESENTER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipeImageEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CategoryEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PresenterEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
