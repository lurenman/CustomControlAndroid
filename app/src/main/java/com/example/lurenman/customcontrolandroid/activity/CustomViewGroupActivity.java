package com.example.lurenman.customcontrolandroid.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.customcontrolandroid.R;

/**
 * @author: baiyang.
 * Created on 2017/12/21.
 */

public class CustomViewGroupActivity extends BaseActivity {
    private Button bt_click_one;
    private Button bt_click_two;
    private Button bt_click_three;
    private Button bt_click_four;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "CustomViewGroup";
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_custom_viewgroup);
        bt_click_one = (Button) findViewById(R.id.bt_click_one);
        bt_click_two = (Button) findViewById(R.id.bt_click_two);
        bt_click_three = (Button) findViewById(R.id.bt_click_three);
        bt_click_four = (Button) findViewById(R.id.bt_click_four);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        bt_click_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewGroupExerciseOneActivity.class);
                startActivity(intent);
            }
        });
        bt_click_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewGroupExerciseTwoActivity.class);
                startActivity(intent);
            }
        });
        bt_click_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewGroupExerciseThreeActivity.class);
                startActivity(intent);
            }
        });
        bt_click_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewGroupExerciseFourActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
