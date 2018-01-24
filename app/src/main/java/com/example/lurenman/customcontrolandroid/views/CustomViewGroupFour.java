package com.example.lurenman.customcontrolandroid.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.lurenman.customcontrolandroid.params.CustomLayoutParams;

/**
 * @author: baiyang.
 * Created on 2018/1/24.
 */

public class CustomViewGroupFour extends ViewGroup {
    public CustomViewGroupFour(Context context) {
        super(context);
    }

    public CustomViewGroupFour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroupFour(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //得到真实的像素大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //要给容器设置的宽高
        int layoutWidth = 0;
        int layoutHeight = 0;
        int childCount = getChildCount();
        // 计算出所有的childView的宽和高
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0);
        }
        //宽度模式处理
        if (widthMode == MeasureSpec.EXACTLY) {
            //这个相当于match_parent 或者是具体的Size
            layoutWidth = widthSize;

        } else {
            //如果是未指定或者wrap_content，我们都按照包裹内容做，宽度方向上只需要拿到所有子控件中宽度做大的作为布局宽度
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                int childMeasuredWidth = child.getMeasuredWidth();
                CustomLayoutParams layoutParams = (CustomLayoutParams) child.getLayoutParams();
                //获取子控件宽度和左右边距之和，作为这个控件需要占据的宽度
                int marginWidth = childMeasuredWidth + layoutParams.leftMargin + layoutParams.rightMargin;
                layoutWidth = marginWidth > layoutWidth ? marginWidth : layoutWidth;
            }
        }
        //高度模式处理
        if (heightMode == MeasureSpec.EXACTLY) {
            layoutHeight = heightSize;
        } else {
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                int childMeasuredHeight = child.getMeasuredHeight();
                CustomLayoutParams layoutParams = (CustomLayoutParams) child.getLayoutParams();
                int marginHeight = childMeasuredHeight + layoutParams.topMargin + layoutParams.bottomMargin;
                layoutHeight = marginHeight > layoutHeight ? marginHeight : layoutHeight;
            }
        }

        // 测量并保存layout的宽高
        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        CustomLayoutParams params = null;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            // 注意此处不能使用getWidth和getHeight，这两个方法必须在onLayout执行完，才能正确获取宽高
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();
            params = (CustomLayoutParams) child.getLayoutParams();
            switch (params.position) {
                case CustomLayoutParams.POSITION_MIDDLE:    // 中间
                    left = (getWidth() - childMeasureWidth) / 2 - params.rightMargin + params.leftMargin;
                    top = (getHeight() - childMeasureHeight) / 2 + params.topMargin - params.bottomMargin;
                    break;
                case CustomLayoutParams.POSITION_LEFT:      // 左上方
                    left = 0 + params.leftMargin;
                    top = 0 + params.topMargin;
                    break;
                case CustomLayoutParams.POSITION_RIGHT:     // 右上方
                    left = getWidth() - childMeasureWidth - params.rightMargin;
                    top = 0 + params.topMargin;
                    break;
                case CustomLayoutParams.POSITION_BOTTOM:    // 左下角
                    left = 0 + params.leftMargin;
                    top = getHeight() - childMeasureHeight - params.bottomMargin;
                    break;
                case CustomLayoutParams.POSITION_RIGHTANDBOTTOM:// 右下角
                    left = getWidth() - childMeasureWidth - params.rightMargin;
                    top = getHeight() - childMeasureHeight - params.bottomMargin;
                    break;
                default:
                    break;
            }

            // 确定子控件的位置，四个参数分别代表（左上右下）点的坐标值
            child.layout(left, top, left + childMeasureWidth, top + childMeasureHeight);
        }

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
