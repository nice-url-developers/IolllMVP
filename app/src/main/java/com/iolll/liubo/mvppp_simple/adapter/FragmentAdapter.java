package com.iolll.liubo.mvppp_simple.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.iolll.liubo.mvppp_simple.base.iolll.BaseLazyFragment;
import com.iolll.liubo.mvppp_simple.model.net.FuLi;

import java.util.ArrayList;

/**
 * Created by LiuBo on 2018/10/10.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseLazyFragment> fragments;
    private ArrayList<FuLi> titles;


    public FragmentAdapter(FragmentManager fm, ArrayList<BaseLazyFragment> fragments, ArrayList<FuLi> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getDesc();
    }

    public FuLi getTitles(int position) {
        return titles.get(position);
    }

    public void setTitles(ArrayList<FuLi> titles) {
        this.titles = titles;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
