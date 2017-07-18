package com.example.hsb.customletterspacing.Utils;

import android.content.res.Resources;

/**
 * Created by hsb on 2017/7/17.
 */

public class PxDipConversion {

    public static int px2dip(int pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static float dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }

}
