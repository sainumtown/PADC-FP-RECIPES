package com.padc.recipes;

import android.app.Application;
import android.content.Context;

/**
 * Created by asus on 9/10/2016.
 */
public class RecipesApp extends Application {

    public static final String TAG = "RECIPE_APP";
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}