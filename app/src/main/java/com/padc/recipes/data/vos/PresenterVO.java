package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sainumtown on 9/24/16.
 */
public class PresenterVO {

    @SerializedName("persenter-id")
    private String presenter_id;

    @SerializedName("persenter-name")
    private String presenter_name;

    public String getPresenter_id() {
        return presenter_id;
    }

    public String getPresenter_name() {
        return presenter_name;
    }
}
