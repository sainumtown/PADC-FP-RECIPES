package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sainumtown on 9/24/16.
 */
public class ServiceTimeVO {
    @SerializedName("start")
    private String start;

    @SerializedName("finish")
    private String finish;

    @SerializedName("off-days")
    private String[] off_days;

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public String[] getOff_days() {
        return off_days;
    }
}


