package com.iolll.liubo.mvppp_simple.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import static com.iolll.liubo.mvppp_simple.utils.DensityUtils.dp2px;


/**
 * 机票多程调用改adapter
 * 标示点适配器
 * Created by LiuBo on 2018/9/13.
 */
public class IndicatorAdapter extends RecyclerView.Adapter {
    private int indicatorMargin = dp2px(4); //指示器间距
    private Drawable mSelectedDrawable;
    private Drawable mUnselectedDrawable;
    public void setSize(int size) {
        this.size = size;
    }

    int currentPosition = 0;
    private int size;
    public void setPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public IndicatorAdapter() {
        if (mSelectedDrawable == null) {
            //绘制默认选中状态图形
            GradientDrawable selectedGradientDrawable = new GradientDrawable();
            selectedGradientDrawable.setShape(GradientDrawable.RECTANGLE);
            selectedGradientDrawable.setColor(Color.WHITE);
            selectedGradientDrawable.setSize(dp2px(14), dp2px(7));
            selectedGradientDrawable.setCornerRadius(dp2px(7) / 2);
            mSelectedDrawable = new LayerDrawable(new Drawable[]{selectedGradientDrawable});
        }
        if (mUnselectedDrawable == null) {
            //绘制默认未选中状态图形
            GradientDrawable unSelectedGradientDrawable = new GradientDrawable();
            unSelectedGradientDrawable.setShape(GradientDrawable.OVAL);
            unSelectedGradientDrawable.setColor(Color.WHITE);
            unSelectedGradientDrawable.setSize(dp2px(7), dp2px(7));
            unSelectedGradientDrawable.setCornerRadius(dp2px(7) / 2);
            mUnselectedDrawable = new LayerDrawable(new Drawable[]{unSelectedGradientDrawable});
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ImageView bannerPoint = new ImageView(parent.getContext());
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(indicatorMargin, indicatorMargin, indicatorMargin, indicatorMargin);
        bannerPoint.setLayoutParams(lp);
        return new RecyclerView.ViewHolder(bannerPoint) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageView bannerPoint = (ImageView) holder.itemView;
        System.out.println( " IndicatorAdapter " + currentPosition + " ： " + position);
        bannerPoint.setImageDrawable(currentPosition%size == position ? mSelectedDrawable : mUnselectedDrawable);

    }

    @Override
    public int getItemCount() {
        return size;
    }
}
