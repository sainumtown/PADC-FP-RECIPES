package com.padc.recipes.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.data.persistence.RecipeContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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


    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public static void saveRecipes(List<RecipeVO> recipeList) {
        Context context = RecipesApp.getContext();
        ContentValues[] recipeCVs = new ContentValues[recipeList.size()];
        for (int index = 0; index < recipeList.size(); index++) {
            RecipeVO recipe = recipeList.get(index);
            recipeCVs[index] = recipe.parseToContentValues();

            //Bulk insert into recipe_images.
            RecipeVO.saveRecipeImages(recipe.getRecipe_title(), recipe.getPhotos());

            // insert into category
            RecipeVO.saveCategory(recipe.category);
        }

        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(RecipeContract.RecipeEntry.CONTENT_URI, recipeCVs);

        Log.d(RecipesApp.TAG, "Bulk inserted into recipe table : " + insertedCount);
    }

    private static void saveCategory(CategoryVO category) {
        ContentValues cv =new ContentValues();
        cv.put(RecipeContract.CategoryEntry.COLUMN_CATEGORY_ID,category.getCategory_id());
        cv.put(RecipeContract.CategoryEntry.COLUMN_CATEGORY_NAME,category.getCategory_name());
        cv.put(RecipeContract.CategoryEntry.COLUMN_DESCRIPTION,category.getDescription());
        cv.put(RecipeContract.CategoryEntry.COLUMN_IMAGE,category.getImage());
    }

    private static void saveRecipeImages(String title, String[] images) {
        ContentValues[] attractionImagesCVs = new ContentValues[images.length];
        for (int index = 0; index < images.length; index++) {
            String image = images[index];

            ContentValues cv = new ContentValues();
            cv.put(RecipeContract.RecipeImageEntry.COLUMN_RECIPE_TITLE, title);
            cv.put(RecipeContract.RecipeImageEntry.COLUMN_IMAGE, image);

            attractionImagesCVs[index] = cv;
        }

        Context context = RecipesApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(RecipeContract.RecipeImageEntry.CONTENT_URI, attractionImagesCVs);

    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(RecipeContract.RecipeEntry.COLUMN_ID, recipe_id);
        cv.put(RecipeContract.RecipeEntry.COLUMN_TITLE, recipe_title);
        cv.put(RecipeContract.RecipeEntry.COLUMN_NOTE, note);
        cv.put(RecipeContract.RecipeEntry.COLUMN_VIDEO, video);
        cv.put(RecipeContract.RecipeEntry.COLUMN_CATEGORY_ID, category.getCategory_id());

        return cv;
    }

    public static RecipeVO parseFromCursor(Cursor data) {
        RecipeVO recipe = new RecipeVO();
        recipe.recipe_id = Integer.parseInt(data.getString(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_ID)));
        recipe.recipe_title = data.getString(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_TITLE));
        recipe.note = data.getString(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_NOTE));
        recipe.video = data.getString(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_VIDEO));

        String category_id = data.getString(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_CATEGORY_ID));
        CategoryVO category = new CategoryVO();
        category.setCategory_id(Integer.parseInt(category_id));
        recipe.category = category;

        return recipe;
    }

    public static String[] loadRecipeImagesByTitle(String title) {

        Context context = RecipesApp.getContext();
        ArrayList<String> images = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(RecipeContract.RecipeImageEntry.buildRecipeImageUriWithTitle(title),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                images.add(cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeImageEntry.COLUMN_IMAGE)));
            } while (cursor.moveToNext());
        }

        String[] imageArray = new String[images.size()];
        images.toArray(imageArray);
        return imageArray;
    }
}
