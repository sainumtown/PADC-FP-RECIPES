package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sainumtown on 9/24/16.
 */
public class MeasurementUnitVO {

    @SerializedName("measurement-unit-id")
    private int measurement_unit_id;

    @SerializedName("measurement-unit-name")
    private String measurement_unit_name;

    public int getMeasurement_unit_id() {
        return measurement_unit_id;
    }

    public String getMeasurement_unit_name() {
        return measurement_unit_name;
    }
}
