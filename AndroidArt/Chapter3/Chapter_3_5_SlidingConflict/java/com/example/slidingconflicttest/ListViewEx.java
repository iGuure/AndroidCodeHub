package com.example.slidingconflicttest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Guure on 2017/5/19.
 */

public class ListViewEx extends ListView {

    private static final String TAG = "ListViewEx";

    private HorizantalScrollViewEx2 mHorizantalScrollViewEx2;

    private Main2Activity main2Activity;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    public ListViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 自定义View绑定Activity中控件的方法
        main2Activity = (Main2Activity) context;
    }

    @Override
    // 不能在构造方法中直接绑定，因为控件还没跟Activity链接（？）
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mHorizantalScrollViewEx2 = (HorizantalScrollViewEx2) main2Activity.findViewById(R.id.horizontalScrollViewEx2);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                // 每个事件序列开始的时候，父元素不能拦截，让子元素决定
                mHorizantalScrollViewEx2.requestDisallowInterceptTouchEvent(true);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                // 如果这个事件是横向移动事件（事件只能为横向移动或者竖向移动）
                // 那个接下来的事件都交给父元素来处理
                // 否则全部交给子元素来处理！
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    mHorizantalScrollViewEx2.requestDisallowInterceptTouchEvent(false);
                }
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
        return super.dispatchTouchEvent(ev);
    }
}
