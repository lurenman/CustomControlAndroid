package com.example.lurenman.customcontrolandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.customcontrolandroid.activity.CustomTextViewActivity;
import com.example.lurenman.customcontrolandroid.activity.CustomViewGroupActivity;

public class MainActivity extends AppCompatActivity {

    private Button bt_CustomTextView;
    private Button bt_CustomViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
    }

    private void initViews() {
        bt_CustomTextView = (Button) findViewById(R.id.bt_CustomTextView);
        bt_CustomViewGroup = (Button) findViewById(R.id.bt_CustomViewGroup);
    }

    private void initEvents() {
        bt_CustomTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomTextViewActivity.class);
                startActivity(intent);
            }
        });
        bt_CustomViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomViewGroupActivity.class);
                startActivity(intent);
            }
        });
    }
}
