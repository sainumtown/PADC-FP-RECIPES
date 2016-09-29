package com.padc.recipes.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.components.MMTextView;
import com.padc.recipes.data.models.RecipeModel;
import com.padc.recipes.data.persistence.RecipeContract;
import com.padc.recipes.data.vos.IngredientVO;
import com.padc.recipes.data.vos.InstructionVO;
import com.padc.recipes.data.vos.RecipeVO;
import com.padc.recipes.utils.RecipeAppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesDetailScreenActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>
        , AppBarLayout.OnOffsetChangedListener {

    private static final String IE_RECIPE_ID = "recipe_id";
    @BindView(R.id.iv_recipe)
    ImageView ivRecipe;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tv_ingredients)
    TextView tvIngredients;

    @BindView(R.id.tv_instructions)
    TextView tvInstructions;

    @BindView(R.id.tv_availableshop)
    TextView tvAvailableShop;

    @BindView(R.id.iv_ykko)
    ImageView ivYKKO;

    @BindView(R.id.iv_yangon)
    ImageView ivYangon;

    @BindView(R.id.iv_yangon1)
    ImageView ivYangon1;

    @BindView(R.id.iv_taipot)
    ImageView ivTaipot;

    @BindView(R.id.tv_ykko)
    TextView tvYkko;

    @BindView(R.id.tv_taipot)
    TextView tvTaipot;

    @BindView(R.id.tv_yangongrill)
    TextView tvYangon;

    @BindView(R.id.tv_yangongrill1)
    TextView tvYangon1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btn_add_to_shopping_list)
    Button btnAddToShoppingList;


    private String mRecipeId;
    RecipeVO mRecipe;
    private View.OnClickListener mClickListner;

    ColorStateList cslBeforeCheck = new ColorStateList(
            new int[][]{{android.R.attr.state_checkable}, {}},
            new int[]{Color.GREEN, Color.RED});

    ColorStateList cslAfterCheck = new ColorStateList(
            new int[][]{{android.R.attr.state_checkable}, {}},
            new int[]{Color.RED, Color.GREEN});


    public static Intent newIntent(String attractionName) {
        Intent intent = new Intent(RecipesApp.getContext(), RecipesDetailScreenActivity.class);
        intent.putExtra(IE_RECIPE_ID, attractionName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_detail_screen);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        Glide.with(ivYKKO.getContext())
                .load(R.drawable.ykko)
                .centerCrop()
                .into(ivYKKO);

        Glide.with(ivTaipot.getContext())
                .load(R.drawable.taipot)
                .centerCrop()
                .into(ivTaipot);


        Glide.with(ivYangon.getContext())
                .load(R.drawable.yangon)
                .centerCrop()
                .into(ivYangon);

        Glide.with(ivYangon1.getContext())
                .load(R.drawable.yangon)
                .centerCrop()
                .into(ivYangon1);


        // Favourite click process
        favourite();
        addToShoppingList();
        removeFromShoppingList();

        mRecipeId = getIntent().getStringExtra(IE_RECIPE_ID);
        getSupportLoaderManager().initLoader(RecipeAppConstants.RECIPE_DETAIL_LOADER, null, this);
    }

    private void removeFromShoppingList() {

    }

    private void addToShoppingList() {
        mClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = HomeActivity.newIntent(HomeActivity.FRAGMENT_SHOPPING_LIST);
                startActivity(intent);
            }
        };

        btnAddToShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeVO.SaveToShoppingList(mRecipe);

                Snackbar.make(findViewById(android.R.id.content), "Check Shopping List", Snackbar.LENGTH_LONG)
                        .setAction("View", mClickListner)
                        .setActionTextColor(Color.WHITE)
                        .show();
            }
        });

    }

    private void favourite() {

        mClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = HomeActivity.newIntent(HomeActivity.FRAGMENT_FAVOURITE);
                startActivity(intent);
            }
        };

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeModel.getInstance().AddToFavourite(mRecipeId);
                fab.setBackgroundTintList(cslBeforeCheck);

                Snackbar.make(findViewById(android.R.id.content), "Check Favourite List", Snackbar.LENGTH_LONG)
                        .setAction("View", mClickListner)
                        .setActionTextColor(Color.WHITE)
                        .show();
            }
        });
    }

    private void materailSetting() {
        LinearLayout llIngredients = (LinearLayout) findViewById(R.id.ll_ingredietns);
        /*final String[] materiaLArray = getResources().getStringArray(R.array.material_list);*/


        for (int i = 0; i < mRecipe.getIngredients().size(); i++) {
            IngredientVO ingredient = mRecipe.getIngredients().get(i);
            MMTextView tvIngredients = (MMTextView) new MMTextView(RecipesApp.getContext());
            tvIngredients.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tvIngredients.setTextColor(getResources().getColor(R.color.text_black_ish));

            tvIngredients.setPadding(40, 10, 0, 10);
            tvIngredients.setText("- " + ingredient.getIngredient_name() + " " + ingredient.getMeasurement());

            llIngredients.addView(tvIngredients);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                RecipeContract.RecipeEntry.buildRecipeUriWithId(mRecipeId),
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            mRecipe = RecipeVO.parseFromCursor(data);
            // set photo
            mRecipe.setPhotos(RecipeVO.loadRecipeImagesByTitle(mRecipe.getRecipe_title()));
            // set category
            mRecipe.setCategory(RecipeVO.loadCategoryByCategoryId(String.valueOf(mRecipe.getCategory().getCategory_id())));
            // set presenter
            if (mRecipe.getPresenter().getPresenter_id() != null) {
                mRecipe.setPresenter(RecipeVO.loadPresenterByPresenterId(String.valueOf(mRecipe.getPresenter().getPresenter_id())));
            }
            // set ingredients
            mRecipe.setIngredients(RecipeVO.loadRecipeIngredientsByRecipeId(String.valueOf(mRecipe.getRecipe_id())));

            // set instructions
            mRecipe.setInstructions(RecipeVO.loadRecipeInstructionsByRecipeId(String.valueOf(mRecipe.getRecipe_id())));

            bindData(mRecipe);
        }
    }

    private void bindData(RecipeVO mRecipe) {
        collapsingToolbar.setTitle(mRecipe.getRecipe_title());

        final Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Zawgyi.ttf");
        collapsingToolbar.setCollapsedTitleTypeface(tf);
        collapsingToolbar.setExpandedTitleTypeface(tf);

        Glide.with(ivRecipe.getContext())
                .load(mRecipe.getPhotos()[0])
                .centerCrop()
                .placeholder(R.drawable.drawable_background)
                .error(R.drawable.drawable_background)
                .into(ivRecipe);

        // ingredients
        materailSetting();

        // step by step
        stepByStepSetting();

        if(mRecipe.isNotFavourite()){
            fab.setBackgroundTintList(cslBeforeCheck);
        }else {
            fab.setBackgroundTintList(cslAfterCheck);
        }
    }

    private void stepByStepSetting() {

        LinearLayout llStepByStep = (LinearLayout) findViewById(R.id.ll_step_by_step);

        for (int i = 0; i < mRecipe.getInstructions().size(); i++) {
            InstructionVO instruction = mRecipe.getInstructions().get(i);
            MMTextView tvInstruction = (MMTextView) new MMTextView(RecipesApp.getContext());
            tvInstruction.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            tvInstruction.setLineSpacing(1f, 1.2f);
            tvInstruction.setTextColor(getResources().getColor(R.color.text_black_ish));
            tvIngredients.setPadding(40, 0, 0, 10);
            tvInstruction.setText("\n" + instruction.getInstruction_desc());

            llStepByStep.addView(tvInstruction);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
}
