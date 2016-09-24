package com.padc.recipes.utils;

import com.google.gson.Gson;

/**
 * Created by sainumtown on 23/09/16.
 */
public class CommonInstances {

    private static Gson gson = new Gson();

    public static Gson getGsonInstance() {
        return gson;
    }
}
