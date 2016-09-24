package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sainumtown on 9/24/16.
 */
public class TagVO {

    @SerializedName("tag-id")
    private int tag_id;

    @SerializedName("tag-name")
    private String category_name;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image;

    public int getTag_id() {
        return tag_id;
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
