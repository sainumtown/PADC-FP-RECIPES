package com.padc.recipes.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
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

    private boolean notFavourite;

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

    public boolean isNotFavourite() {
        return notFavourite;
    }

    public void setNotFavourite(boolean notFavourite) {
        this.notFavourite = notFavourite;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public void setCategory(CategoryVO category) {
        this.category = category;
    }

    public void setPresenter(PresenterVO presenter) {
        this.presenter = presenter;
    }

    public void setTags(List<TagVO> tags) {
        this.tags = tags;
    }

    public void setIngredients(List<IngredientVO> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(List<InstructionVO> instructions) {
        this.instructions = instructions;
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

            // insert into presenter
            if (recipe.getPresenter().getPresenter_id() != null &&
                    !TextUtils.isEmpty(recipe.getPresenter().getPresenter_id())) {
                RecipeVO.savePresenter(recipe.presenter);
            }

            // insert into ingredients
            //Bulk insert into recipes_ingredients.
            RecipeVO.saveRecipeIngredients(recipe.recipe_id, recipe.getIngredients());

            // insert into instructions
            //Bulk insert into recipes_instructions.
            RecipeVO.saveRecipeInstructions(recipe.recipe_id, recipe.getInstructions());

        }

        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(RecipeContract.RecipeEntry.CONTENT_URI, recipeCVs);

        Log.d(RecipesApp.TAG, "Bulk inserted into recipe table : " + insertedCount);
    }

    private static void savePresenter(PresenterVO presenter) {
        ContentValues presenterCV = new ContentValues();
        presenterCV.put(RecipeContract.PresenterEntry.COLUMN_PRESENTER_ID, presenter.getPresenter_id());
        presenterCV.put(RecipeContract.PresenterEntry.COLUMN_PRESENTER_NAME, presenter.getPresenter_name());

        Context context = RecipesApp.getContext();
        Uri insertedUri = context.getContentResolver().insert(RecipeContract.PresenterEntry.CONTENT_URI, presenterCV);

        Log.d(RecipesApp.TAG, "Presenter Inserted Uri : " + insertedUri);

    }

    private static void saveCategory(CategoryVO category) {
        ContentValues categoryCV = new ContentValues();
        categoryCV.put(RecipeContract.CategoryEntry.COLUMN_CATEGORY_ID, category.getCategory_id());
        categoryCV.put(RecipeContract.CategoryEntry.COLUMN_CATEGORY_NAME, category.getCategory_name());
        categoryCV.put(RecipeContract.CategoryEntry.COLUMN_DESCRIPTION, category.getDescription());
        categoryCV.put(RecipeContract.CategoryEntry.COLUMN_IMAGE, category.getImage());

        Context context = RecipesApp.getContext();
        Uri insertedUri = context.getContentResolver().insert(RecipeContract.CategoryEntry.CONTENT_URI, categoryCV);

        Log.d(RecipesApp.TAG, "Category Inserted Uri : " + insertedUri);

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
        cv.put(RecipeContract.RecipeEntry.COLUMN_PRESENTER_ID, presenter.getPresenter_id());

        return cv;
    }

    public static RecipeVO parseFromCursor(Cursor data) {
        RecipeVO recipe = new RecipeVO();
        recipe.recipe_id = Integer.parseInt(data.getString(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_ID)));
        recipe.recipe_title = data.getString(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_TITLE));
        recipe.note = data.getString(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_NOTE));
        recipe.video = data.getString(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_VIDEO));
        recipe.notFavourite = (data.getInt(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_IS_FAVOURITE)) == 1);

        String category_id = data.getString(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_CATEGORY_ID));
        CategoryVO category = new CategoryVO();
        category.setCategory_id(Integer.parseInt(category_id));
        recipe.category = category;

        String presenter_id = data.getString(data.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_PRESENTER_ID));
        PresenterVO presenter = new PresenterVO();
        presenter.setPresenter_id(presenter_id);
        recipe.presenter = presenter;

        return recipe;
    }

    public static RecipeVO parseRecipeIdAndTitleFromCursor(Cursor data){
        RecipeVO recipe = new RecipeVO();
        recipe.recipe_id = Integer.parseInt(data.getString(data.getColumnIndex(RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_RECIPE_ID)));
        recipe.recipe_title =data.getString(data.getColumnIndex(RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_RECIPE_TITLE));
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

    public static CategoryVO loadCategoryByCategoryId(String categoryId) {

        Context context = RecipesApp.getContext();
        CategoryVO category = new CategoryVO();

        Cursor cursor = context.getContentResolver().query(RecipeContract.CategoryEntry.buildCategoryUriWithId(categoryId),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                category.setCategory_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(RecipeContract.CategoryEntry.COLUMN_CATEGORY_ID))));
                category.setCategory_name(cursor.getString(cursor.getColumnIndex(RecipeContract.CategoryEntry.COLUMN_CATEGORY_NAME)));
                category.setDescription(cursor.getString(cursor.getColumnIndex(RecipeContract.CategoryEntry.COLUMN_DESCRIPTION)));
                category.setImage(cursor.getString(cursor.getColumnIndex(RecipeContract.CategoryEntry.COLUMN_IMAGE)));
            } while (cursor.moveToNext());
        }

        return category;
    }

    public static PresenterVO loadPresenterByPresenterId(String presenterId) {

        Context context = RecipesApp.getContext();
        PresenterVO presenter = new PresenterVO();

        Cursor cursor = context.getContentResolver().query(RecipeContract.PresenterEntry.buildPresenteriWithId(presenterId),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {

                presenter.setPresenter_id(String.valueOf(Integer.parseInt(cursor.getString(cursor.getColumnIndex(RecipeContract.PresenterEntry.COLUMN_PRESENTER_ID)))));
                presenter.setPresenter_name(cursor.getString(cursor.getColumnIndex(RecipeContract.PresenterEntry.COLUMN_PRESENTER_NAME)));

            } while (cursor.moveToNext());
        }


        return presenter;
    }

    private static void saveRecipeIngredients(int recipe_id, List<IngredientVO> ingredients) {
        ContentValues[] recipeIngredientCVs = new ContentValues[ingredients.size()];
        for (int index = 0; index < ingredients.size(); index++) {
            IngredientVO ingredient = ingredients.get(index);

            ContentValues cv = new ContentValues();
            cv.put(RecipeContract.IngredientEntry.COLUMN_RECIPE_ID, recipe_id);
            cv.put(RecipeContract.IngredientEntry.COLUMN_INGREDIENT_ID, ingredient.getIngredient_id());
            cv.put(RecipeContract.IngredientEntry.COLUMN_INGREDIENT_NAME, ingredient.getIngredient_name());
            cv.put(RecipeContract.IngredientEntry.COLUMN_NOTE, ingredient.getNote());
            cv.put(RecipeContract.IngredientEntry.COLUMN_MEASUREMENT, ingredient.getMeasurement());
            cv.put(RecipeContract.IngredientEntry.COLUMN_IMAGE_URL, ingredient.getImage());

            recipeIngredientCVs[index] = cv;
        }

        Context context = RecipesApp.getContext();
        int insertedCount = context.getContentResolver().bulkInsert(RecipeContract.IngredientEntry.CONTENT_URI, recipeIngredientCVs);
        Log.d(RecipesApp.TAG, "Bulk inserted into recipes_ingredients table : " + insertedCount);

    }

    public static List<IngredientVO> loadRecipeIngredientsByRecipeId(String recipe_id) {
        Context context = RecipesApp.getContext();
        ArrayList<IngredientVO> ingredients = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(RecipeContract.IngredientEntry.buildIngredientWithRecipeId(recipe_id),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                IngredientVO ingredient = new IngredientVO();
                ingredient.setIngredient_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(RecipeContract.IngredientEntry.COLUMN_INGREDIENT_ID))));
                ingredient.setIngredient_name(cursor.getString(cursor.getColumnIndex(RecipeContract.IngredientEntry.COLUMN_INGREDIENT_NAME)));
                ingredient.setNote(cursor.getString(cursor.getColumnIndex(RecipeContract.IngredientEntry.COLUMN_NOTE)));
                ingredient.setMeasurement(cursor.getString(cursor.getColumnIndex(RecipeContract.IngredientEntry.COLUMN_MEASUREMENT)));
                ingredient.setImage(cursor.getString(cursor.getColumnIndex(RecipeContract.IngredientEntry.COLUMN_IMAGE_URL)));
                ingredients.add(ingredient);

            } while (cursor.moveToNext());
        }

        return ingredients;
    }

    private static void saveRecipeInstructions(int recipe_id, List<InstructionVO> instructions) {
        ContentValues[] recipeInstructionCVs = new ContentValues[instructions.size()];
        for (int index = 0; index < instructions.size(); index++) {
            InstructionVO instruction = instructions.get(index);

            ContentValues cv = new ContentValues();
            cv.put(RecipeContract.InstructionEntry.COLUMN_RECIPE_ID, recipe_id);
            cv.put(RecipeContract.InstructionEntry.COLUMN_INSTRUCTION_DESC, instruction.getInstruction_desc());
            cv.put(RecipeContract.InstructionEntry.COLUMN_SORT_ORDER, instruction.getSort_order());
            cv.put(RecipeContract.InstructionEntry.COLUMN_INSTRUCTION_IMAGE, instruction.getInstruction_image());

            recipeInstructionCVs[index] = cv;
        }

        Context context = RecipesApp.getContext();
        int insertedCount = context.getContentResolver().bulkInsert(RecipeContract.InstructionEntry.CONTENT_URI, recipeInstructionCVs);
        Log.d(RecipesApp.TAG, "Bulk inserted into recipes_instructions table : " + insertedCount);
    }

    public static List<InstructionVO> loadRecipeInstructionsByRecipeId(String recipe_id) {
        Context context = RecipesApp.getContext();
        ArrayList<InstructionVO> instructions = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(RecipeContract.InstructionEntry.buildInstructionWithRecipeId(recipe_id),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                InstructionVO instruction = new InstructionVO();
                instruction.setInstruction_desc(cursor.getString(cursor.getColumnIndex(RecipeContract.InstructionEntry.COLUMN_INSTRUCTION_DESC)));
                instruction.setInstruction_image(cursor.getString(cursor.getColumnIndex(RecipeContract.InstructionEntry.COLUMN_INSTRUCTION_IMAGE)));
                instruction.setSort_order(Integer.parseInt(cursor.getString(cursor.getColumnIndex(RecipeContract.InstructionEntry.COLUMN_SORT_ORDER))));

                instructions.add(instruction);

            } while (cursor.moveToNext());
        }

        return instructions;
    }

    public static void saveOrRemoveFavourite(RecipeVO recipe) {
        ContentValues presenterCV = new ContentValues();
        if(!recipe.isNotFavourite()) {
            presenterCV.put(RecipeContract.RecipeEntry.COLUMN_IS_FAVOURITE, 1);
        }else {
            presenterCV.put(RecipeContract.RecipeEntry.COLUMN_IS_FAVOURITE, 0);
        }

        String selection =RecipeContract.RecipeEntry.COLUMN_ID+"=?";
        String[] selectionArgs =new String[] {String.valueOf(recipe.getRecipe_id())};

        Context context = RecipesApp.getContext();
        int updated = context.getContentResolver().update(RecipeContract.RecipeEntry.CONTENT_URI, presenterCV,selection,selectionArgs);

        Log.d(RecipesApp.TAG, "Favourite recipe updated status : " + updated);


    }

    public static void SaveToShoppingList(RecipeVO mRecipe) {
        ContentValues[] shoppingListRecipeIngredientCVs = new ContentValues[mRecipe.getIngredients().size()];
        for (int index = 0; index < mRecipe.getIngredients().size(); index++) {
            IngredientVO ingredient = mRecipe.getIngredients().get(index);

            ContentValues cv = new ContentValues();
            cv.put(RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_RECIPE_ID, mRecipe.getRecipe_id());
            cv.put(RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_RECIPE_TITLE, mRecipe.getRecipe_title());
            cv.put(RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_INGREDIENT_ID, ingredient.getIngredient_id());
            cv.put(RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_INGREDIENT_NAME, ingredient.getIngredient_name());
            cv.put(RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_MEASUREMENT, ingredient.getMeasurement());

            shoppingListRecipeIngredientCVs[index] = cv;
        }

        Context context = RecipesApp.getContext();
        int insertedCount = context.getContentResolver().bulkInsert(RecipeContract.ShoppingRecipeIngredientEntry.CONTENT_URI, shoppingListRecipeIngredientCVs);
        Log.d(RecipesApp.TAG, "Bulk inserted into shopping_list_recipes_ingredients table : " + insertedCount);

    }

    public static List<IngredientVO> loadShoppingListRecipeIngredientsByRecipeId(String recipe_id) {
        Context context = RecipesApp.getContext();
        ArrayList<IngredientVO> ingredients = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(RecipeContract.ShoppingRecipeIngredientEntry.buildShoppingUriWithRecipeId(recipe_id),
                null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                IngredientVO ingredient = new IngredientVO();
                ingredient.setIngredient_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(RecipeContract.IngredientEntry.COLUMN_INGREDIENT_ID))));
                ingredient.setIngredient_name(cursor.getString(cursor.getColumnIndex(RecipeContract.IngredientEntry.COLUMN_INGREDIENT_NAME)));
                ingredient.setMeasurement(cursor.getString(cursor.getColumnIndex(RecipeContract.IngredientEntry.COLUMN_MEASUREMENT)));
                ingredient.setBought((cursor.getInt(cursor
                        .getColumnIndex(RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_BOUGHT)) == 1));
                ingredients.add(ingredient);

            } while (cursor.moveToNext());
        }
        return ingredients;
    }

    public static void UpdateBoughtIngredient(String recipeId, String ingredientId, boolean bought) {
        ContentValues recipeCV = new ContentValues();
        if(bought) {
            recipeCV.put(RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_BOUGHT, 0);
        }else{
            recipeCV.put(RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_BOUGHT, 1);
        }

        String selection =RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_RECIPE_ID+"=? AND "+
                RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_INGREDIENT_ID +"=?";
        String[] selectionArgs =new String[] {recipeId,ingredientId};

        Context context = RecipesApp.getContext();
        int updated = context.getContentResolver().update(RecipeContract.ShoppingRecipeIngredientEntry.CONTENT_URI, recipeCV,selection,selectionArgs);

        Log.d(RecipesApp.TAG, "Favourite recipe updated status : " + updated);
    }

    public static void removeAllIngredientsShoppingListByRecipeId(String recipeId) {

        String selection =RecipeContract.ShoppingRecipeIngredientEntry.COLUMN_RECIPE_ID+"=?";
        String[] selectionArgs =new String[] {recipeId};

        Context context = RecipesApp.getContext();
        int deleted = context.getContentResolver().delete(RecipeContract.ShoppingRecipeIngredientEntry.CONTENT_URI,selection,selectionArgs);
        Log.d(RecipesApp.TAG, "Shopping List recipe ingreidents deleted status : " + deleted);
    }
}
