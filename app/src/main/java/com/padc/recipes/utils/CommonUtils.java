package com.padc.recipes.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by sainumtown on 9/24/16.
 */
public class CommonUtils {

    static DateFormat dateFormat = new SimpleDateFormat(RecipeAppConstants.TIME_FORMAT);

    public static String getTime(String time) {
        String formattedDate = dateFormat.format(time).toString();
        return formattedDate;
    }


}
