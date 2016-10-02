package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sainumtown on 9/24/16.
 */
public class TownshipVO {

    @SerializedName("township-id")
    private int township_id;

    @SerializedName("township-name")
    private String township_name;

    public int getTownship_id() {
        return township_id;
    }

    public String getTownship_name() {
        return township_name;
    }

    public void setTownship_id(int township_id) {
        this.township_id = township_id;
    }

    public void setTownship_name(String township_name) {
        this.township_name = township_name;
    }
}
