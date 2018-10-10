//package com.iolll.liubo.mvppp_simple.modules.plane.v;
//
//
//import com.iolll.liubo.mvppp_simple.R;
//import com.iolll.liubo.mvppp_simple.base.iolll.Presenter;
//import com.iolll.liubo.mvppp_simple.base.modules.OrderDetailBaseActivity;
//import com.iolll.liubo.mvppp_simple.modules.plane.contrast.p_impl.PlaneFooterPresenterImpl;
//import com.iolll.liubo.mvppp_simple.modules.plane.contrast.p_impl.RouteCardBannerPresenterImpl;
//import com.iolll.liubo.mvppp_simple.modules.plane.v_contrast.PlaneAlterDetailContract;
//import com.iolll.liubo.mvppp_simple.modules.plane.v_contrast.p_impl.PlaneAlertDetailPresenterImpl;
//import com.iolll.liubo.mvppp_simple.utils.Utils;
//
//import java.util.ArrayList;
//
///**
// * Created by LiuBo on 2018/9/29.
// */
//public class PlaneAlterDetailActivity extends OrderDetailBaseActivity<PlaneAlterDetailContract.V> implements PlaneAlterDetailContract.V {
//
//
//    /**
//     * 业务主P
//     */
//    private PlaneAlertDetailPresenterImpl<PlaneAlterDetailContract.V> presenter;
//
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_plane_alter_detail;
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
////        presenter.setOrderNo(getIntent().getStringExtra("orderNo"));
//        presenter.setOrderNo("AG180928586360");
//    }
//
//    @Override
//    protected void getData() {
//        super.getData();
//        presenter.getOrderDetail();
//    }
//
//    @Override
//    protected void showCallPhone(int stringId) {
//        DialogUtil.showDialog(context, "提示", "取消", "拨打", context.getString(stringId), new MyDialog.OnButtonClickListener() {
//            @Override
//            public void onLeftClick() {
//
//            }
//
//            @Override
//            public void onRightClick() {
//                AppUtils.callPhone(context, Utils.getString(R.string.callPhone));
//            }
//        });
//    }
//
//    @Override
//    protected void setViews() {
//        super.setViews();
//        initBanner();
//    }
//
//    @Override
//    public ArrayList<? extends Presenter<PlaneAlterDetailContract.V>> createPresenter() {
//        System.out.println(" createPresenter " + bannerRV);
//        return new ArrayList<Presenter<PlaneAlterDetailContract.V>>() {{
//            presenter = new PlaneAlertDetailPresenterImpl<>();
//            planeFooterPresenter = new PlaneFooterPresenterImpl<>();
//            routeCardBannerPresenter = new RouteCardBannerPresenterImpl<>();
//            add(presenter);
//            add(planeFooterPresenter);
//            add(routeCardBannerPresenter);
//        }};
//    }
//
//
//
//
//    @Override
//    public void updateView(PlaneAlterDetailBean mBean) {
//        presenter.setRouteItems(mBean.getRoutesI());
//        routeCardBannerPresenter.refreshRouteCards(1 == mBean.getTickettype(), mBean.getRoutesI());
//        planeFooterPresenter.refreshFooterButton(1== mBean.getTickettype(),mBean.getStatus(), "1".equals(mBean.getPayType()));
//    }
//
//    @Override
//    public void initRecyclerViewIndicator() {
//
//    }
//
//    @Override
//    public void refreshIndicator(int index) {
//
//    }
//
//
//}
