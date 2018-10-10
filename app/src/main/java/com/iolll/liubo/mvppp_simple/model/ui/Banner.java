package com.iolll.liubo.mvppp_simple.model.ui;

import com.iolll.liubo.mvppp_simple.model.net.FuLi;

import java.util.ArrayList;

/**
 * Created by LiuBo on 2018/10/9.
 */
public class Banner {
    ArrayList<FuLi> fuLis;

    public Banner(ArrayList<FuLi> fuLis) {
        setFuLis(fuLis);
    }

    public ArrayList<FuLi> getFuLis() {
        return fuLis;
    }

    public void setFuLis(ArrayList<FuLi> fuLis) {
        this.fuLis = fuLis;
    }
}