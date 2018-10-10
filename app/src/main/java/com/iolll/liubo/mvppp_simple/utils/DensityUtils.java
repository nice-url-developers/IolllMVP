package com.iolll.liubo.mvppp_simple.utils;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by chenjx on 2016/3/14.
 */
public class DensityUtils {

    /**
     * dp转px
     */
    public static int dp2px(Context ctx, float dp) {
        float density = ctx.getResources().getDisplayMetrics().density;
        int px = (int) (dp * density + 0.5f);// 4.9->5 4.4->4
        return px;
    }

    /**
     * dp转px
     */
    public static int dp2px( float dp) {
        float density = Utils.getContext().getResources().getDisplayMetrics().density;
        int px = (int) (dp * density + 0.5f);// 4.9->5 4.4->4
        return px;
    }

    public static float px2dp(Context ctx, int px) {
        float density = ctx.getResources().getDisplayMetrics().density;
        float dp = px / density;

        return dp;
    }
    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static ViewGroup.LayoutParams setHeight(ViewGroup.LayoutParams layoutParams, int height){
        layoutParams.height = dp2px(height);
        return  layoutParams;
    }

    public static RelativeLayout.LayoutParams setMargin(int height){
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
         layoutParams.setMargins(height, height, height, height);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        return  layoutParams;
    }
    public static RelativeLayout.LayoutParams setMargin(int left, int top, int right, int bottom){
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
         layoutParams.setMargins(left, top, right, bottom);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        return  layoutParams;
    }

}
