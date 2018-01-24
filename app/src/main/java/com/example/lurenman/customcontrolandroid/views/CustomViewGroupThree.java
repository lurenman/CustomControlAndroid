package com.example.lurenman.customcontrolandroid.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.lurenman.customcontrolandroid.params.CustomLayoutParams;

/**
 * @author: baiyang.
 * Created on 2018/1/24.
 * 简单的实现就像相对布局一样
 */

public class CustomViewGroupThree extends ViewGroup {
    public CustomViewGroupThree(Context context) {
        super(context);
    }

    public CustomViewGroupThree(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroupThree(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //得到真实的像素大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //要给容器设置的宽高
        int layoutWidth = 0;
        int layoutHeight = 0;
        //测量childView这样下面才能得到具体的像素大小
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        //宽度模式处理
        if (widthMode == MeasureSpec.EXACTLY) {
            //这个相当于match_parent 或者是具体的Size
            layoutWidth = widthSize;

        } else {
            //相当于wrap_content MeasureSpec.AT_MOST
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                int measuredChlidWidth = childAt.getMeasuredWidth();//childView的宽度
                layoutWidth = measuredChlidWidth > layoutWidth ? measuredChlidWidth : layoutWidth;
            }

        }
        //高度模式处理
        if (heightMode == MeasureSpec.EXACTLY) {
            layoutHeight = heightSize;
        } else {
            //相当于wrap_content
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                int measuredChildHeight = childAt.getMeasuredHeight();
                layoutHeight = measuredChildHeight > layoutHeight ? measuredChildHeight : layoutHeight;
            }
        }


        //super.onMeasure(layoutWidth, layoutHeight);
        // 测量并保存layout的宽高
        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //这个目的也就是要怎么摆放子View
        int childCount = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childMeasureWidth = childView.getMeasuredWidth();
            childMeasureHeight = childView.getMeasuredHeight();
            CustomLayoutParams layoutParams = (CustomLayoutParams) childView.getLayoutParams();
            switch (layoutParams.position) {
                case CustomLayoutParams.POSITION_MIDDLE:    // 中间
                    left = (getWidth() - childMeasureWidth) / 2;
                    top = (getHeight() - childMeasureHeight) / 2;
                    break;
                case CustomLayoutParams.POSITION_LEFT:      // 左上方
                    left = 0;
                    top = 0;
                    break;
                case CustomLayoutParams.POSITION_RIGHT:     // 右上方
                    left = getWidth() - childMeasureWidth;
                    top = 0;
                    break;
                case CustomLayoutParams.POSITION_BOTTOM:    // 左下角
                    left = 0;
                    top = getHeight() - childMeasureHeight;
                    break;
                case CustomLayoutParams.POSITION_RIGHTANDBOTTOM:// 右下角
                    left = getWidth() - childMeasureWidth;
                    top = getHeight() - childMeasureHeight;
                    break;
                default:
                    break;
            }
            // 确定子控件的位置，四个参数分别代表（左上右下）点的坐标值
            childView.layout(left, top, left + childMeasureWidth, top + childMeasureHeight);
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }
}
