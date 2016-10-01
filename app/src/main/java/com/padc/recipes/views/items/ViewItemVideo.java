package com.padc.recipes.views.items;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.padc.recipes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sainumtown on 9/16/16.
 */
public class ViewItemVideo extends FrameLayout {


    public ViewItemVideo(Context context) {
        super(context);
    }

    public ViewItemVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewItemVideo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }



}
