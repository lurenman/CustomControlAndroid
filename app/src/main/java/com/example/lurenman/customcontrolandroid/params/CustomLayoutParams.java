package com.example.lurenman.customcontrolandroid.params;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.example.lurenman.customcontrolandroid.R;

/**
 * @author: baiyang.
 * Created on 2018/1/24.
 */

public class CustomLayoutParams extends ViewGroup.MarginLayoutParams {
    public static final int POSITION_MIDDLE = 0; // 中间
    public static final int POSITION_LEFT = 1; // 左上方
    public static final int POSITION_RIGHT = 2; // 右上方
    public static final int POSITION_BOTTOM = 3; // 左下角
    public static final int POSITION_RIGHTANDBOTTOM = 4; // 右下角

    public int position = POSITION_LEFT;  // 默认我们的位置就是左上角

    public CustomLayoutParams(Context c, AttributeSet attrs) {
        super(c, attrs);
        TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.CustomViewGroupThree);
        position = typedArray.getInt(R.styleable.CustomViewGroupThree_layout_position, position);
        typedArray.recycle();//回收

    }

    public CustomLayoutParams(int width, int height) {
        super(width, height);
    }

    public CustomLayoutParams(ViewGroup.MarginLayoutParams source) {
        super(source);
    }

    public CustomLayoutParams(ViewGroup.LayoutParams source) {
        super(source);
    }

}
