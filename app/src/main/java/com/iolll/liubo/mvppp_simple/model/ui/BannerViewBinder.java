package com.iolll.liubo.mvppp_simple.model.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iolll.liubo.mvppp_simple.R;
import com.iolll.liubo.mvppp_simple.adapter.viewbinder.FuLiViewBinder;
import com.iolll.liubo.mvppp_simple.model.net.FuLi;
import com.iolll.liubo.mvppp_simple.widget.banner.BannerRecyclerView;
import com.iolll.liubo.mvppp_simple.widget.banner.BannerScaleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * Created by LiuBo on 2018/10/9.
 */
public class BannerViewBinder extends ItemViewBinder<Banner, BannerViewBinder.ViewHolder> {

    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private Items items = new Items();

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_banner, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Banner banner) {
        items.clear();
        items.addAll(banner.getFuLis());
        adapter.register(FuLi.class,new FuLiViewBinder());
        adapter.setItems(items);
        holder.bannerRecyclerView.setAdapter(adapter);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bannerRecyclerView)
        BannerRecyclerView bannerRecyclerView;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            bannerRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), HORIZONTAL,false));
            // mRecyclerView绑定scale效果
            BannerScaleHelper mBannerScaleHelper = new BannerScaleHelper();
            mBannerScaleHelper.setScale(1.0f);
            mBannerScaleHelper.attachToRecyclerView(bannerRecyclerView);
            mBannerScaleHelper.scrollToPosition(0);
//            SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
//            snapHelperStart.attachToRecyclerView(bannerRecyclerView);
        }
    }
}
