package com.padc.recipes.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sainumtown on 9/24/16.
 */
public class RecipeVO {

    @SerializedName("recipe-id")
    private int recipe_id;

    @SerializedName("recipe-title")
    private String recipe_title;

    @SerializedName("note")
    private String note;

    @SerializedName("photos")
    private String[] photos;

    @SerializedName("video")
    private String video;

    @SerializedName("category")
    private CategoryVO category;

    @SerializedName("presenter")
    private PresenterVO presenter;

    @SerializedName("tags")
    private List<TagVO> tags;

    @SerializedName("ingredients")
    private List<IngredientVO> ingredients;


    @SerializedName("instructions")
    private List<InstructionVO> instructions;

    @SerializedName("measurement-units")
    private List<MeasurementUnitVO> measurement_units;

    @SerializedName("available-restaurants")
    private List<AvailableRestaurantVO> available_restaurants;


    public int getRecipe_id() {
        return recipe_id;
    }

    public String getRecipe_title() {
        return recipe_title;
    }

    public String getNote() {
        return note;
    }

    public String[] getPhotos() {
        return photos;
    }

    public String getVideo() {
        return video;
    }

    public CategoryVO getCategory() {
        return category;
    }

    public PresenterVO getPresenter() {
        return presenter;
    }

    public List<TagVO> getTags() {
        return tags;
    }

    public List<IngredientVO> getIngredients() {
        return ingredients;
    }

    public List<InstructionVO> getInstructions() {
        return instructions;
    }

    public List<MeasurementUnitVO> getMeasurement_units() {
        return measurement_units;
    }

    public List<AvailableRestaurantVO> getAvailable_restaurants() {
        return available_restaurants;
    }
}
