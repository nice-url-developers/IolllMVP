//package com.iolll.liubo.mvppp_simple.modules.plane.contrast.p_impl;
//
//import com.auvgo.tmc.base.iolll.PresenterImpl;
//import com.auvgo.tmc.plane.bean.RoutesBean;
//import com.auvgo.tmc.views.recyclerBanner.IndicatorAdapter;
//import com.iolll.liubo.mvppp_simple.modules.plane.contrast.RouteCardBannerContract;
//
//import java.util.ArrayList;
//
//import me.drakeet.multitype.Items;
//import me.drakeet.multitype.MultiTypeAdapter;
//
///**
// * Created by LiuBo on 2018/9/28.
// */
//public class RouteCardBannerPresenterImpl<V extends RouteCardBannerContract.V> extends PresenterImpl<V> implements RouteCardBannerContract.P {
//    private Items routeItems = new Items();
//    private MultiTypeAdapter bannerAdapter = new MultiTypeAdapter();
//    private IndicatorAdapter indicatorAdapter;//此处不初始化因为不知道这个页面是否要用到它
//
//    public IndicatorAdapter getIndicatorAdapter() {
//        return indicatorAdapter;
//    }
//
//    public void initIndicatorAdapter() {
//        indicatorAdapter = new IndicatorAdapter();
//    }
//
//
//    public MultiTypeAdapter getBannerAdapter() {
//        return bannerAdapter;
//    }
//
//    public void setBannerAdapter(MultiTypeAdapter bannerAdapter) {
//        this.bannerAdapter = bannerAdapter;
//    }
//
//    public Items getRouteItems() {
//        return routeItems;
//    }
//
//    public void setRouteItems(ArrayList<RoutesBean> routeItems) {
//        this.routeItems.clear();
//        this.routeItems.addAll(routeItems);
//    }
//
//    private int bannerIndex = 0;
//
//    public int getBannerIndex() {
//        return bannerIndex;
//    }
//
//    public void setBannerIndex(int bannerIndex) {
//        this.bannerIndex = bannerIndex;
//        v.refreshIndicator(bannerIndex);
//    }
//
//    private String orderNo;
//
//    public String getOrderNo() {
//        return orderNo;
//    }
//
//    public void setOrderNo(String orderNo) {
//        this.orderNo = orderNo;
//    }
//
//    private boolean isInternational = false;
//
//    public boolean isInternational() {
//        return isInternational;
//    }
//
//    public void setInternational(boolean international) {
//        isInternational = international;
//    }
//
//    public void refreshRouteCards(boolean isInternational, ArrayList<RoutesBean> routes) {
//        this.isInternational = isInternational;
//        v.refreshRouteCards(isInternational, routes);
//    }
//
//
//}