//package com.iolll.liubo.mvppp_simple.modules.plane.v_contrast.p_impl;
//
//
//import com.iolll.liubo.mvppp_simple.adapter.IndicatorAdapter;
//import com.iolll.liubo.mvppp_simple.base.iolll.PresenterImpl;
//import com.iolll.liubo.mvppp_simple.modules.plane.v_contrast.PlaneAlterDetailContract;
//
//import java.util.ArrayList;
//
//import io.reactivex.Observer;
//import io.reactivex.disposables.Disposable;
//import me.drakeet.multitype.Items;
//import me.drakeet.multitype.MultiTypeAdapter;
//
//
//
///**
// * Created by LiuBo on 2018/9/28.
// */
//public class PlaneAlertDetailPresenterImpl<V extends PlaneAlterDetailContract.V> extends PresenterImpl<V> implements PlaneAlterDetailContract.P {
//    private PlaneAlterDetailBean mBean;
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
//    public PlaneAlterDetailBean getmBean() {
//        return mBean;
//    }
//
//    public void setmBean(PlaneAlterDetailBean mBean) {
//        this.mBean = mBean;
//    }
//
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
//    public void getOrderDetail() {
//        DataManager.getPlaneAlterDetail(orderNo)
//                .as(this.<BaseResponseBean<PlaneAlterDetailBean>>bindLifecycle())
//                .subscribe(new Observer<BaseResponseBean<PlaneAlterDetailBean>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(BaseResponseBean<PlaneAlterDetailBean> planeAlterDetailBeanBaseResponseBean) {
//                        if (planeAlterDetailBeanBaseResponseBean.getStatus() == 200)
//                            if (isNotNull(planeAlterDetailBeanBaseResponseBean.getData()))
//                                v.updateView(planeAlterDetailBeanBaseResponseBean.getData());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//    private boolean isInternational =false;
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
//        v.refreshRouteCards(isInternational,routes);
//    }
//}