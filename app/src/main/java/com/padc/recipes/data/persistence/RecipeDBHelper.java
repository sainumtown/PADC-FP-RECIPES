package com.padc.recipes.data.persistence;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.padc.recipes.data.persistence.RecipeContract.RecipeEntry;


/**
 * Created by sainumtown on 9/36/16.
 */
public class RecipeDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "recipes.db";

    private static final String SQL_CREATE_RECIPE_TABLE = "CREATE TABLE " + RecipeContract.RecipeEntry.TABLE_NAME + " (" +
            RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RecipeEntry.COLUMN_ID + " TEXT NOT NULL, " +
            RecipeEntry.COLUMN_TITLE+ " TEXT NOT NULL, " +
            RecipeEntry.COLUMN_NOTE + " TEXT NOT NULL, " +
            RecipeEntry.COLUMN_VIDEO + " TEXT, " +
            RecipeEntry.COLUMN_CATEGORY_ID + " TEXT, " +
            RecipeEntry.COLUMN_PRESENTER_ID + " TEXT, " +

            " UNIQUE (" + RecipeEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    public RecipeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_RECIPE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipeEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
