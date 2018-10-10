package com.iolll.liubo.mvppp_simple.modules.main.v;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.iolll.liubo.mvppp_simple.R;
import com.iolll.liubo.mvppp_simple.base.iolll.BaseLazyFragment;
import com.iolll.liubo.mvppp_simple.model.net.FuLi;
import com.iolll.liubo.mvppp_simple.model.net.GankRoot;
import com.iolll.liubo.mvppp_simple.model.ui.Banner;
import com.iolll.liubo.mvppp_simple.model.ui.BannerViewBinder;
import com.iolll.liubo.mvppp_simple.net.DataManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


/**
 * Created by LiuBo on 2018/10/10.
 */
public class MainFragment extends BaseLazyFragment {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.mainList)
    RecyclerView mainList;
    Unbinder unbinder;


    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private Items items = new Items();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        items.add(new Banner(new ArrayList<FuLi>() {{
            add(new FuLi("2018-10-08", "http://img.hb.aicdn.com/10dd7b6eb9ca02a55e915a068924058e72f7b3353a40d-ZkO3ko_fw658"));
            add(new FuLi("2018-10-07", "http://img.hb.aicdn.com/10dd7b6eb9ca02a55e915a068924058e72f7b3353a40d-ZkO3ko_fw658"));
            add(new FuLi("2018-10-06", "http://img.hb.aicdn.com/10dd7b6eb9ca02a55e915a068924058e72f7b3353a40d-ZkO3ko_fw658"));
            add(new FuLi("2018-10-05", "http://img.hb.aicdn.com/10dd7b6eb9ca02a55e915a068924058e72f7b3353a40d-ZkO3ko_fw658"));
            add(new FuLi("2018-10-04", "http://img.hb.aicdn.com/10dd7b6eb9ca02a55e915a068924058e72f7b3353a40d-ZkO3ko_fw658"));
            add(new FuLi("2018-10-03", "http://img.hb.aicdn.com/10dd7b6eb9ca02a55e915a068924058e72f7b3353a40d-ZkO3ko_fw658"));
        }}));
        adapter.register(Banner.class, new BannerViewBinder());
        adapter.setItems(items);
        mainList.setLayoutManager(new LinearLayoutManager(getContext()));
        mainList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
       getDaily();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void dailySuccess(ArrayList<FuLi> daily) {
        items.clear();
        items.add(new Banner(daily));
        adapter.notifyDataSetChanged();
    }


    public void getDaily() {
        DataManager.getDaily().as(this.<GankRoot>bindLifecycle()).subscribe(new Observer<GankRoot>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GankRoot gankRoot) {
                dailySuccess(gankRoot.getResults());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
