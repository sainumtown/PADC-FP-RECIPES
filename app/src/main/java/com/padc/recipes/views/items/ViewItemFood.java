package com.padc.recipes.views.items;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.padc.recipes.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by asus on 7/15/16.
 */
public class ViewItemFood extends FrameLayout {

    @BindView(R.id.tv_food)
    TextView tvFood;

    public ViewItemFood(Context context) {
        super(context);
    }

    public ViewItemFood(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewItemFood(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setData(String recipeCategory) {
        tvFood.setText(recipeCategory);
    }
}
