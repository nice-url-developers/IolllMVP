package com.iolll.liubo.mvppp_simple.adapter.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iolll.liubo.mvppp_simple.GlideApp;
import com.iolll.liubo.mvppp_simple.R;
import com.iolll.liubo.mvppp_simple.model.net.FuLi;
import com.iolll.liubo.mvppp_simple.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by LiuBo on 2018/10/9.
 */
public class FuLiViewBinder extends ItemViewBinder<FuLi, FuLiViewBinder.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_fu_li, parent, false);
//        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) root.getLayoutParams();
//        lp.width = parent.getWidth() -mm2px(root.getContext(), 2 * (10 + 50));
//        root.setLayoutParams(lp);
        return new ViewHolder(root);
    }
    private void setViewMargin(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right || lp.bottomMargin != bottom) {
            lp.setMargins(left, top, right, bottom);
            view.setLayoutParams(lp);
        }
    }
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull FuLi fuLi) {
        holder.decs.setText(fuLi.getDesc());
        GlideApp.with(Utils.getContext())
                .load(fuLi.getUrl())
                .into(holder.img);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.decs)
        TextView decs;
        @BindView(R.id.cardView)
        CardView cardView;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
