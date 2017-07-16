package com.example.customletterspacing.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Guure on 2017/7/16.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    CustomTextView(Context context) {
        super(context);
    }

    CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        TextPaint textPaint = getPaint();
        textPaint.drawableState = getDrawableState();
//        textPaint.setColor(getTextColors().getDefaultColor());
        String textString = (String) getText();

        for (int i = 0; i < textString.length(); i++) {
            canvas.drawText(textString.charAt(i) + "", i * getTextSize(), getTextSize(), textPaint);
            Log.e("Guu", getTextSize() + "");
            Log.e("Guu", i + "");
        }

    }

}
