package com.example.lurenman.customcontrolandroid.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author: baiyang.
 * Created on 2017/12/21.
 */

public class CustomViewGroupOne extends ViewGroup {

    public CustomViewGroupOne(Context context) {
        super(context);
    }

    public CustomViewGroupOne(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroupOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //测量并保存layout的宽高(使用getDefaultSize时，wrap_content和match_perent都是填充屏幕)
        //稍后会重新写这个方法，能达到wrap_content的效果
//        setMeasuredDimension( getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
//                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    /**
     * 为所有的子控件摆放位置.
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int measuredChildWidth = childView.getMeasuredWidth();
            int measuredChildHeight = childView.getMeasuredHeight();
            //摆放的位置，参数自己体会
            childView.layout(0,0,measuredChildWidth,measuredChildHeight);
        }
    }
}
