package com.padc.recipes.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;
import com.padc.recipes.data.persistence.RecipeContract;
import com.padc.recipes.data.vos.RestaurantVO;
import com.padc.recipes.utils.RecipeAppConstants;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResturantDetailScreenActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final String IE_RESTAURANT_ID = "restaurant_id";
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.iv_attraction)
    ImageView ivAttraction;


    @BindView(R.id.iv_ykko1)
    ImageView ivYKKO1;

    @BindView(R.id.iv_ykko2)
    ImageView ivYKKO2;

    @BindView(R.id.iv_ykko3)
    ImageView ivYKKO3;

    @BindView(R.id.iv_ykko4)
    ImageView ivYKKO4;

    @BindView(R.id.tv_subtitle)
    TextView tvSubTilte;

    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.tv_service)
    TextView tvService;

    @BindView(R.id.tv_ykko1)
    TextView tvYKKO1;

    @BindView(R.id.tv_ykko2)
    TextView tvYKKO2;


    @BindView(R.id.tv_ykko3)
    TextView tvYKKO3;


    @BindView(R.id.tv_ykko4)
    TextView tvYKKO4;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String mRestaurnatId;
    private RestaurantVO mRestaurant;

    public static Intent newIntent(String attractionName) {
        Intent intent = new Intent(RecipesApp.getContext(), ResturantDetailScreenActivity.class);
        intent.putExtra(IE_RESTAURANT_ID, attractionName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_detail_screen);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        
        Glide.with(ivAttraction.getContext())
                .load(R.drawable.ykko_resturant)
                .centerCrop()
                .into(ivAttraction);
        Glide.with(ivYKKO1.getContext())
                .load(R.drawable.pork_kyay_oh_sichet)
                .centerCrop()
                .into(ivYKKO1);
        Glide.with(ivYKKO2.getContext())
                .load(R.drawable.grill_chicken)
                .centerCrop()
                .into(ivYKKO2);

        Glide.with(ivYKKO3.getContext())
                .load(R.drawable.pork_rib_kyay_oh)
                .centerCrop()
                .into(ivYKKO3);

        Glide.with(ivYKKO4.getContext())
                .load(R.drawable.grill_chicken_sausage)
                .centerCrop()
                .into(ivYKKO4);


        mRestaurnatId = getIntent().getStringExtra(IE_RESTAURANT_ID);
        getSupportLoaderManager().initLoader(RecipeAppConstants.RESTAURANT_DETAIL_LOADER, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                RecipeContract.RestaurantEntry.buildRestaurantWithRestaurantId(mRestaurnatId),
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            do {
                 mRestaurant = RestaurantVO.parseFromCursor(data);
                mRestaurant.setPhotos(mRestaurant.loadRestaurantImagesByRestaurantId(mRestaurant.getRestaurant_id()));
                // set township
                if (mRestaurant.getTownship() != null) {
                    mRestaurant.setTownship(mRestaurant.loadTownshipByTownshipId(String.valueOf(mRestaurant.getTownship().getTownship_id())));
                }
                // set servicetime
                mRestaurant.setService_time(mRestaurant.loadRestaurantServiceTimeByRestaurantId(mRestaurant.getRestaurant_id()));

                // set Recommended foods
                mRestaurant.setMost_popular_recipes(mRestaurant.loadRestaurantRecommendedFoodByRestaurantId(mRestaurant.getRestaurant_id()));

            } while (data.moveToNext());
        }
        bindData(mRestaurant);
    }

    private void bindData(RestaurantVO mRestaurant) {
        collapsingToolbar.setTitle(mRestaurant.getRestaurant_name());

        final Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Zawgyi.ttf");
        collapsingToolbar.setCollapsedTitleTypeface(tf);
        collapsingToolbar.setExpandedTitleTypeface(tf);

        tvSubTilte.setText(mRestaurant.getTownship().getTownship_name());
        tvAddress.setText(mRestaurant.getAddress());

        Glide.with(ivAttraction.getContext())
                .load(mRestaurant.getPhotos()[0])
                .centerCrop()
                .placeholder(R.drawable.drawable_background)
                .error(R.drawable.drawable_background)
                .into(ivAttraction);

        if (mRestaurant.getService_time() != null) {
            String serviceTime = mRestaurant.getService_time().getStart() + " "
                    + RecipesApp.getContext().getString(R.string.lbl_from) + " " +  mRestaurant.getService_time().getFinish();
            tvTime.setText(serviceTime);
        }

        if(mRestaurant.getPhone_number() !=null ){
            String phoneNumber = Arrays.toString(mRestaurant.getPhone_number()).substring(1,Arrays.toString(mRestaurant.getPhone_number()).length()-1);
            tvPhone.setText(phoneNumber);
        }

        tvService.setText(mRestaurant.getDescription());
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
