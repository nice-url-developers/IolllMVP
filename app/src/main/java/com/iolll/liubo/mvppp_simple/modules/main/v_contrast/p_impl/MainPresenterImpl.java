package com.iolll.liubo.mvppp_simple.modules.main.v_contrast.p_impl;

import android.support.v4.app.FragmentManager;

import com.iolll.liubo.mvppp_simple.R;
import com.iolll.liubo.mvppp_simple.adapter.FragmentAdapter;
import com.iolll.liubo.mvppp_simple.base.iolll.BaseLazyFragment;
import com.iolll.liubo.mvppp_simple.base.iolll.PresenterImpl;
import com.iolll.liubo.mvppp_simple.model.net.FuLi;
import com.iolll.liubo.mvppp_simple.modules.main.v_contrast.MainContrast;

import java.util.ArrayList;

/**
 * Created by LiuBo on 2018/10/10.
 */
public class MainPresenterImpl extends PresenterImpl<MainContrast.V> implements MainContrast.P {
    public ArrayList<BaseLazyFragment> fragments;
    ArrayList<FuLi> titles =  new ArrayList<FuLi>() {{
        add(new FuLi("Home", "", R.drawable.ic_launcher_background));
        add(new FuLi("Setting", "",R.drawable.ic_launcher_foreground));
        add(new FuLi("Setting", "",R.drawable.ic_launcher_foreground));
        add(new FuLi("Setting", "",R.drawable.ic_launcher_foreground));
    }};
    public FragmentAdapter fragmentAdapter;

    @Override
    public void initFragmentAdapter(FragmentManager fragmentManager) {
        fragmentAdapter = new FragmentAdapter(fragmentManager,fragments,titles);
    }
}
