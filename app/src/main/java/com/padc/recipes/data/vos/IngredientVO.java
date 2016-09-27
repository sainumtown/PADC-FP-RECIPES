package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sainumtown on 9/24/16.
 */
public class IngredientVO {

    @SerializedName("ingredient-id")
    private int ingredient_id;

    @SerializedName("ingredient-name")
    private String ingredient_name;

    @SerializedName("note")
    private String note;

    @SerializedName("measurement")
    private String measurement;

    @SerializedName("image")
    private String image;

    public int getIngredient_id() {
        return ingredient_id;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public String getNote() {
        return note;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getImage() {
        return image;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
