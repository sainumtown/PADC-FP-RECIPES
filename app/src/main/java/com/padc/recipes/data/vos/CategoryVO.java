package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sainumtown on 9/24/16.
 */
public class CategoryVO {

    @SerializedName("category-id")
    private int category_id;

    @SerializedName("category-name")
    private String category_name;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image;

    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
