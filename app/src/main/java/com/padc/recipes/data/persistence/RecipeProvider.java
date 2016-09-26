package com.padc.recipes.data.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by aung on 7/10/16.
 */
public class RecipeProvider extends ContentProvider {

    public static final int RECIPE = 100;
    public static final int RECIPE_IMAGES = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private RecipeDBHelper mRecipeDBHelper;

    private static final String sRecipetitleSelection = RecipeContract.RecipeEntry.COLUMN_TITLE + " = ?";
    private static final String sRecipeImageSelectionWithTitle = RecipeContract.RecipeImageEntry.COLUMN_RECIPE_TITLE + " = ?";

    @Override
    public boolean onCreate() {
        mRecipeDBHelper = new RecipeDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor queryCursor;
        int matchUri = sUriMatcher.match(uri);

        switch (matchUri) {
            case RECIPE:
                String recipeTitle = RecipeContract.RecipeEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(recipeTitle)) {
                    selection = sRecipetitleSelection;
                    selectionArgs = new String[]{recipeTitle};
                }
                queryCursor = mRecipeDBHelper.getReadableDatabase().query(RecipeContract.RecipeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;
            case RECIPE_IMAGES:
                String title = RecipeContract.RecipeImageEntry.getRecipeTitleFromParam(uri);
                if (title != null) {
                    selection = sRecipeImageSelectionWithTitle;
                    selectionArgs = new String[]{title};
                }
                queryCursor = mRecipeDBHelper.getReadableDatabase().query(RecipeContract.RecipeImageEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
        Context context = getContext();

        if (context != null) {
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }
        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case RECIPE:
                return RecipeContract.RecipeEntry.DIR_TYPE;
            case RECIPE_IMAGES:
                return RecipeContract.RecipeImageEntry.DIR_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mRecipeDBHelper.getWritableDatabase();
        final int matchUri = sUriMatcher.match(uri);
        Uri insertedUri;

        switch (matchUri) {
            case RECIPE: {
                long _id = db.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = RecipeContract.RecipeEntry.buildRecipeUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case RECIPE_IMAGES: {
                long _id = db.insert(RecipeContract.RecipeImageEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = RecipeContract.RecipeImageEntry.buildRecipeImageUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedUri;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mRecipeDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mRecipeDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mRecipeDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(RecipeContract.CONTENT_AUTHORITY, RecipeContract.PATH_RECIPES, RECIPE);
        uriMatcher.addURI(RecipeContract.CONTENT_AUTHORITY, RecipeContract.PATH_RECIPE_IMAGES, RECIPE_IMAGES);

        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);

        switch (matchUri) {
            case RECIPE:
                return RecipeContract.RecipeEntry.TABLE_NAME;
            case RECIPE_IMAGES:
                return RecipeContract.RecipeImageEntry.TABLE_NAME;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }
}
