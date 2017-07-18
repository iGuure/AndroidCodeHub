package com.example.hsb.customletterspacing;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // test special fonts
        TextView textView = (TextView) findViewById(R.id.specialFont);
        Typeface font = Typeface.createFromAsset(getAssets(), "credit_limit_font.ttf");
        textView.setTypeface(font);
    }
}
