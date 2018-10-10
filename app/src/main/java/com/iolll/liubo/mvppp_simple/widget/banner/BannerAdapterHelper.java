package com.iolll.liubo.mvppp_simple.widget.banner;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import static com.iolll.liubo.mvppp_simple.utils.DensityUtils.dp2px;
import static me.jessyan.autosize.utils.AutoSizeUtils.mm2px;

/**
 * Created by LiuBo on 2018/9/26.
 */
public class BannerAdapterHelper {
    public static int sPagePadding = 5;
    public static int sShowLeftCardWidth = 5;

    public void onCreateViewHolder(ViewGroup parent, View itemView) {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        lp.width = parent.getWidth() -mm2px(itemView.getContext(), 2 * (sPagePadding + sShowLeftCardWidth));
        itemView.setLayoutParams(lp);
    }

    public void onBindViewHolder(View itemView, final int position, int itemCount) {
        int padding = dp2px(itemView.getContext(), sPagePadding);
        itemView.setPadding(padding, 0, padding, 0);
        int leftMarin = position == 0 ? padding + mm2px(itemView.getContext(), sShowLeftCardWidth) : 0;
        int rightMarin = position == itemCount - 1 ? padding +mm2px(itemView.getContext(), sShowLeftCardWidth) : 0;
        setViewMargin(itemView, leftMarin, 0, rightMarin, 0);
    }

    private void setViewMargin(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right || lp.bottomMargin != bottom) {
            lp.setMargins(left, top, right, bottom);
            view.setLayoutParams(lp);
        }
    }

    public void setPagePadding(int pagePadding) {
        sPagePadding = pagePadding;
    }

    public void setShowLeftCardWidth(int showLeftCardWidth) {
        sShowLeftCardWidth = showLeftCardWidth;
    }
}
