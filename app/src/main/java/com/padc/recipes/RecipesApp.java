package com.padc.recipes;

import android.app.Application;
import android.content.Context;

/**
 * Created by asus on 9/10/2016.
 */
public class RecipesApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}

