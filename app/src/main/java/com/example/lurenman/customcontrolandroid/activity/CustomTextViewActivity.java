package com.example.lurenman.customcontrolandroid.activity;

import android.support.annotation.NonNull;

import com.example.lurenman.customcontrolandroid.R;

/**
 * @author: baiyang.
 * Created on 2017/12/20.
 */

public class CustomTextViewActivity extends BaseActivity {
    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "CustomTextView";
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_customtextview);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadData() {

    }
}
