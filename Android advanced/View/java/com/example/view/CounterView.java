package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Guure on 2017/3/28.
 */


// 自绘控件
// 控件的布局在此类中通过canvas画出
public class CounterView extends View implements View.OnClickListener{

    private Paint mPaint;

    private Rect mBounds;

    private int mCount;

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画一个蓝色的框
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        // 画计时器的值
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(150);
        String text = String.valueOf(mCount);
        // 通过text，计算出mBounds的属性
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        // 画String，参数为[String, 左坐标，上坐标，画笔]
        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2, mPaint);
    }

    @Override
    public void onClick(View v) {
        mCount++;

        // 重绘视图
        invalidate();
    }
}
