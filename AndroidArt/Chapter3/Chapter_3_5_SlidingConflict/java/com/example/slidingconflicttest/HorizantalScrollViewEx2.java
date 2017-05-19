package com.example.slidingconflicttest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.HorizontalScrollView;
import android.widget.Scroller;

/**
 * Created by Guure on 2017/5/19.
 */

public class HorizantalScrollViewEx2 extends HorizontalScrollView {

    private static final String TAG = "HorizontalScrollViewEx2";

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    // 分别记录上次滑动的坐标(onInterceptTouchEvent)
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    private void init() {
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
    }

    public HorizantalScrollViewEx2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        int action = ev.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            mLastX = x;
            mLastY = y;
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mVelocityTracker.addMovement(ev);
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }

            // 只处理ACTION_MOVE的横向移动事件
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                scrollBy(-deltaX, 0);
                break;
            }

            case MotionEvent.ACTION_UP: {
                break;
            }

            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }

}
