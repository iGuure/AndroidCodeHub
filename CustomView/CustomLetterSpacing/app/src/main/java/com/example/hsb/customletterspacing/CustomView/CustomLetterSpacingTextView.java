package com.example.hsb.customletterspacing.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;

import com.example.hsb.customletterspacing.R;
import com.example.hsb.customletterspacing.Utils.PxDipConversion;

/**
 * Created by hsb on 2017/7/17.
 */

public class CustomLetterSpacingTextView extends android.support.v7.widget.AppCompatTextView {

    private float mTextSize;
    private float mCustomPadding;
    private TextPaint mTextPaint;

    public CustomLetterSpacingTextView(Context context) {
        super(context);
    }

    public CustomLetterSpacingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLetterSpacingTextView);
        mCustomPadding = PxDipConversion.dip2px(typedArray.getDimension(R.styleable.CustomLetterSpacingTextView_spacing, 0));
        typedArray.recycle();
        init();
    }

    private void init() {
        mTextPaint = getPaint();
        mTextPaint.setColor(getCurrentTextColor());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mTextSize = getPaint().measureText(getText().toString());

        int desiredWidth = (int) (mTextSize + mCustomPadding * getText().length() * 2);
        int desiredHeight =  getLineHeight() * 2 - getBaseline();

        // for some reason
        getTotalPaddingTop();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        CharSequence textString = getText();

        for (int i = 0; i < textString.length(); i++) {
            canvas.drawText(String.valueOf(textString.charAt(i)) , mCustomPadding + (mTextSize / getText().length() + mCustomPadding * 2) * i, getTextSize(), mTextPaint);
        }

    }

}
