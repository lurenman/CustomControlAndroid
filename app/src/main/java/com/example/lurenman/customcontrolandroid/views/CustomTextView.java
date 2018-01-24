package com.example.lurenman.customcontrolandroid.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.lurenman.customcontrolandroid.R;
import com.example.lurenman.customcontrolandroid.utils.DensityUtil;

/**
 * @author: baiyang.
 * Created on 2017/12/20.
 * 参考
 * http://blog.csdn.net/xmxkf/article/details/51454685
 * 自定义属性可以参考
 * http://blog.csdn.net/xmxkf/article/details/51468648
 */

public class CustomTextView extends View {
    private static final String TAG = "CustomTextView";
    /**
     * 需要绘制的文字
     */
    private String mText;
    /**
     * 文本的颜色
     */
    private int mTextColor;
    /**
     * 文本的大小
     */
    private int mTextSize;
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public CustomTextView(Context context) {
        // super(context);
        this(context, null);

    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        //super(context, attrs);
        this(context, attrs, 0);

    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        mText = "海贼王";
//        mTextColor = Color.BLACK;
//        mTextSize = DensityUtil.sp2px(context, 17);
        //这块用自定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        String text = typedArray.getString(R.styleable.CustomTextView_mText);
        int color = typedArray.getColor(R.styleable.CustomTextView_mTextColor, Color.BLUE);
        float textSize = typedArray.getDimension(R.styleable.CustomTextView_mTextSize, DensityUtil.sp2px(context, 15));
        //注意回收
        typedArray.recycle();

        mText = text;
        mTextColor = color;
        mTextSize = (int) textSize;
        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        //获得绘制文本的宽和高
        mBound = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);
        // mPaint.setTextAlign(Paint.Align.CENTER);

    }

    /**
     * onMeasure方法调用了setMeasuredDimension(int measuredWidth, int measuredHeight)方法，
     * 而传入的参数已经是测量过的默认宽和高的值了；我们看看getDefaultSize 方法是怎么计算测量宽高的。
     * 根据父控件给予的约束，发现AT_MOST （相当于wrap_content ）和EXACTLY （相当于match_parent ）
     * 两种情况返回的测量宽高都是specSize，而这个specSize正是我们上面说的父控件剩余的宽高，所以默认onMeasure方法中wrap_content
     * 和match_parent 的效果是一样的，都是填充剩余的空间。
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //下面获取模式都是从xml文件中指定的
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        Log.e(TAG, "onMeasure widthmode:" + widthmode);
        Log.e(TAG, "onMeasure heightmode:" + heightmode);
        Log.e(TAG, "onMeasure widthsize:" + widthsize);
        Log.e(TAG, "onMeasure heightsize:" + heightsize);
        //由于默认的xml文件中指定wrap_parent 和match_parent都是铺满全屏的一样的效果，所以要做如下处理
        int width = 0;
        int height = 0;
        //下面就是根据Mode做相应的处理
        if (widthmode == MeasureSpec.EXACTLY) {
            //这个就相当于match_parent
            width = widthsize;
        } else {
            //这块就相当于wrap_parent 和那个没有指定的
            int textWith = mBound.width();
            width = getPaddingLeft() + textWith + getPaddingRight();
        }
        if (heightmode == MeasureSpec.EXACTLY) {
            height = heightsize;
        } else {
            int textHeight = mBound.height();
            height = getPaddingTop() + textHeight + getPaddingBottom();
        }
        //保存测量宽度和测量高度  看super源码可知也是调用这个方法
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        //绘制文字 难道y轴是以底部开始的？
        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
        //canvas.drawText(mText, 0, 100, mPaint);

    }
}
