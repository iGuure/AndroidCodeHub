package com.example.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.FrameMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Guure on 2017/3/28.
 */

// 组合控件
// 控件的布局已在title.xml中设置好
public class TitleView extends FrameLayout {

    private Button leftButton;

    private TextView titleText;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        titleText = (TextView) findViewById(R.id.title_text);
        leftButton = (Button) findViewById(R.id.button_left);
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    public void setTitleText(String text) {
        titleText.setText(text);
    }

    public void setLeftButtonText(String text) {
        leftButton.setText(text);
    }

    public void setLeftButtonListener(OnClickListener l) {
        leftButton.setOnClickListener(l);
    }

}
