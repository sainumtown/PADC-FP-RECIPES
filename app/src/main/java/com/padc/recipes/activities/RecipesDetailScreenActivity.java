package com.padc.recipes.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.recipes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesDetailScreenActivity extends AppCompatActivity {

    @BindView(R.id.iv_attraction)
    ImageView ivAttraction;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tv_ingredients)
    TextView tvIngredients;

    @BindView (R.id.chk1)
    CheckBox chk1;

    @BindView (R.id.chk2)
    CheckBox chk2;

    @BindView (R.id.chk3)
    CheckBox chk3;

    @BindView (R.id.chk4)
    CheckBox chk4;

    @BindView (R.id.chk5)
    CheckBox chk5;

    @BindView (R.id.chk6)
    CheckBox chk6;

    @BindView (R.id.chk7)
    CheckBox chk7;

    @BindView(R.id.tv_instructions)
    TextView tvInstructions;

    @BindView(R.id.tv_step1)
    TextView tvStep1;

    @BindView(R.id.tv_stepdec1)
    TextView tvStepDec1;

    @BindView(R.id.tv_step2)
    TextView tvStep2;

    @BindView(R.id.tv_stepdec2)
    TextView tvStepDec2;

    @BindView(R.id.tv_step3)
    TextView tvStep3;

    @BindView(R.id.tv_stepdec3)
    TextView tvStepDec3;

    @BindView(R.id.tv_step4)
    TextView tvStep4;

    @BindView(R.id.tv_stepdec4)
    TextView tvStepDec4;

    @BindView(R.id.tv_availableshop)
    TextView tvAvailableShop;

    @BindView(R.id.iv_ykko)
    ImageView ivYKKO;

    @BindView(R.id.iv_yangon)
    ImageView ivYangon;

    @BindView(R.id.iv_taipot)
    ImageView ivTaipot;

    @BindView(R.id.tv_ykko)
    TextView tvYkko;

    @BindView(R.id.tv_taipot)
    TextView tvTaipot;

    @BindView(R.id.tv_yangongrill)
    TextView tvYangon;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_detail_screen);
        ButterKnife.bind(this, this);


        Glide.with(ivAttraction.getContext())
                .load(R.drawable.drawable_background)
                .centerCrop()
                .into(ivAttraction);


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



    }
}
