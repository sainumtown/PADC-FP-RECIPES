package com.padc.recipes.fragments;

import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        onSendScreenHit();
    }

    protected abstract void onSendScreenHit();
}