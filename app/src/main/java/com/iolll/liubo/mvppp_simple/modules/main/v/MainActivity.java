package com.iolll.liubo.mvppp_simple.modules.main.v;

import android.support.design.widget.TabLayout;

import com.iolll.liubo.mvppp_simple.R;
import com.iolll.liubo.mvppp_simple.base.iolll.BaseLazyFragment;
import com.iolll.liubo.mvppp_simple.base.iolll.MvpActivity;
import com.iolll.liubo.mvppp_simple.base.iolll.Presenter;
import com.iolll.liubo.mvppp_simple.modules.main.v_contrast.MainContrast;
import com.iolll.liubo.mvppp_simple.modules.main.v_contrast.p_impl.MainPresenterImpl;
import com.iolll.liubo.mvppp_simple.utils.Utils;
import com.iolll.liubo.mvppp_simple.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends MvpActivity implements MainContrast.V {


    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private MainPresenterImpl presenter;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public ArrayList<? extends Presenter> createPresenter() {
        return new ArrayList<Presenter<MainContrast.V>>() {{
            presenter = new MainPresenterImpl();
            add(presenter);
            // 暂且寄人篱下
            presenter.fragments = new ArrayList<BaseLazyFragment>() {{
                add(new MainFragment());
                add(new MainFragment());
                add(new MainFragment());
                add(new MainFragment());
            }};
        }};

    }

    @Override
    protected void initView() {
        super.initView();
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Utils.getColor(R.color.colorTextInfo2),Utils.getColor(R.color.colorTextAccent));
        tabLayout.setSelectedTabIndicatorColor(Utils.getColor(R.color.transparent));
        viewPager.setOffscreenPageLimit(3);
        presenter.initFragmentAdapter(getSupportFragmentManager());

//        tabLayout.setupWithViewPager(viewPager);
        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //使用自定义TAB
        for (int i = 0; i < presenter.fragments.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(presenter.fragmentAdapter.getPageTitle(i)).setIcon(presenter.fragmentAdapter.getTitles(i).getIcon()));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println(tab.getPosition());
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setAdapter(presenter.fragmentAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void getData() {

    }


}
