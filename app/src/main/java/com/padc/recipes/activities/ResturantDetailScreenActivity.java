package com.padc.recipes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.recipes.R;
import com.padc.recipes.RecipesApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResturantDetailScreenActivity extends AppCompatActivity {


    private static final String IE_RESTAURANT_ID = "restaurant_id";
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


    @BindView(R.id.tv_title)
    TextView tvTilte;

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


    }
}
