///*
//package com.iolll.liubo.mvppp_simple.base.modules;
//
//import android.graphics.Color;
//import android.support.v7.widget.LinearLayoutManager;
//import android.view.View;
//
//import com.iolll.liubo.mvppp_simple.R;
//import com.iolll.liubo.mvppp_simple.base.iolll.MvpActivity;
//import com.iolll.liubo.mvppp_simple.modules.plane.contrast.p_impl.PlaneFooterPresenterImpl;
//import com.iolll.liubo.mvppp_simple.modules.plane.contrast.p_impl.RouteCardBannerPresenterImpl;
//import com.iolll.liubo.mvppp_simple.modules.plane.v_contrast.PlaneAlterDetailContract;
//import com.iolll.liubo.mvppp_simple.utils.Utils;
//import com.iolll.liubo.mvppp_simple.widget.banner.BannerScaleHelper;
//
//
//*/
///**
// * Created by LiuBo on 2018/9/29.
// *//*
//
//public abstract class OrderDetailBaseActivity<V> extends MvpActivity<V> implements OrderDetailContract.V {
//
//
//    */
///**
//     * 订单页面轮播
//     * Presenter 来自RouteCardBannerPresenter
//     *//*
//
//    public BannerScaleHelper mBannerScaleHelper;
//    */
///**
//     * 底部按钮Presenter
//     *//*
//
//    public PlaneFooterPresenterImpl<PlaneAlterDetailContract.V> planeFooterPresenter;
//    */
///**
//     * 行程卡片Banner
//     *//*
//
//    public RouteCardBannerPresenterImpl routeCardBannerPresenter;
//    @Override
//    protected int getLayoutId() {
//        return 0;
//    }
//
//
//    @Override
//    protected void initData() {
//
//    }
//
//
//    @Override
//    protected void initListener() {
//        System.out.println( " initListener " + bannerRV);
//        super.initListener();
//    }
//
//    @Override
//    protected void setViews() {
//
//    }
//
//    @Override
//    protected void getData() {
//
//    }
//
//
//    @Override
//    public void inHand() {
//     hideAllBotton();
//    }
//
//    @Override
//    public void inApproval() {
//        planeAlterDetailBt1.setVisibility(View.GONE);
//        planeAlterDetailBt1.setText("");
//        planeAlterDetailBt2.setText(Utils.getString(R.string.plane_order_cancel_order));
//        planeAlterDetailBt2.setBackgroundColor(Color.parseColor("#e1e1e1"));
//        planeAlterDetailBt2.setTextColor(Color.parseColor("#333333"));
//        planeAlterDetailBt2.setVisibility(View.VISIBLE);
//        planeAlterDetailBt2.setOnClickListener(new OnMultiTypeListener() {
//            @Override
//            public void onOneClick(View v) {
//                super.onOneClick(v);
//                DialogUtil.showDialog(context, Utils.getString(R.string.base_dialog_title), Utils.getString(R.string.base_dialog_cancle), Utils.getString(R.string.base_dialog_confirm), Utils.getString(R.string.plane_order_dialog_info_confirm_cancel_order), new MyDialog.OnButtonClickListener() {
//                    @Override
//                    public void onLeftClick() {
//                    }
//
//                    @Override
//                    public void onRightClick() {
//                        planeFooterPresenter.cancleOrder();
//                        MobclickAgent.onEvent(context, EVENT_AIR_CANCEL_ORDER);
//                    }
//                });
//            }
//        });
//    }
//
//    @Override
//    public void approvalVeto() {
//        hideAllBotton();
//    }
//
//    @Override
//    public void canc() {
//        hideAllBotton();
//    }
//
//    @Override
//    public void waitConfirmationAlterTickets(boolean isMonthly) {
//        planeAlterDetailBt1.setVisibility(View.VISIBLE);
//        planeAlterDetailBt2.setVisibility(View.VISIBLE);
//        layoutApproveChose.setVisibility(View.GONE);
//        planeAlterDetailApproveinfo.setVisibility(View.GONE);
////        if (!TextUtils.isEmpty(presenter.getmBean().getPayType())) {
//            planeAlterDetailBt1.setText(isMonthly ? Utils.getString(R.string.place_after_confim_the_change) :
//                    Utils.getString(R.string.place_after_payment));//1表示月结
////        }
//        planeAlterDetailBt1.setBackgroundColor(Utils.getColor(R.color.btnSubmit));
//        planeAlterDetailBt2.setText(Utils.getString(R.string.place_after_cancel_the_change));
//        planeAlterDetailBt2.setBackgroundColor(Color.parseColor("#e1e1e1"));
//        planeAlterDetailBt2.setTextColor(Color.parseColor("#333333"));
//        planeAlterDetailBt1.setOnClickListener(new OnMultiTypeListener() {
//            @Override
//            public void onOneClick(View v) {
//                super.onOneClick(v);
//                planeFooterPresenter.confirmOutTicket();
//                MobclickAgent.onEvent(context, EVENT_AIR_TICKET);
//            }
//        });
//        planeAlterDetailBt2.setOnClickListener(new OnMultiTypeListener() {
//            @Override
//            public void onOneClick(View v) {
//                super.onOneClick(v);
//                DialogUtil.showDialog(context, Utils.getString(R.string.base_dialog_title), Utils.getString(R.string.base_dialog_cancle), Utils.getString(R.string.base_dialog_confirm), Utils.getString(R.string.plane_order_dialog_info_confirm_cancel_order), new MyDialog.OnButtonClickListener() {
//                    @Override
//                    public void onLeftClick() {
//                    }
//
//                    @Override
//                    public void onRightClick() {
//                        planeFooterPresenter.cancleOrder();
//                        MobclickAgent.onEvent(context, EVENT_AIR_CANCEL_ORDER);
//                    }
//                });
//            }
//        });
//
//    }
//
//    @Override
//    public void alteringTicket() {
//        hideAllBotton();
//    }
//
//
//
//    @Override
//    public void alteredTicket() {
//        planeAlterDetailBt1.setVisibility(View.VISIBLE);
//        planeAlterDetailBt2.setVisibility(View.VISIBLE);
//        layoutApproveChose.setVisibility(View.GONE);
//        planeAlterDetailApproveinfo.setVisibility(View.GONE);
//        planeAlterDetailBt1.setBackground(getResources().getDrawable(R.drawable.btn_primary_no_radius));
//
//
//        planeAlterDetailBt2.setTextColor(Color.WHITE);
//        planeAlterDetailBt2.setBackground(getResources().getDrawable(R.drawable.btn_info_no_radius));
//        planeAlterDetailBt1.setText(Utils.getString(R.string.place_after_refund_a_ticket));
//        planeAlterDetailBt1.setOnClickListener(new OnMultiTypeListener() {
//            @Override
//            public void onOneClick(View v) {
//                super.onOneClick(v);
//                if (planeFooterPresenter.isInternational) {
//                   showCallPhone(R.string.cantGqTpNotice);
//                }else {
//                    planeFooterPresenter.tuipiao();
//                    MobclickAgent.onEvent(context, EVENT_AIR_RETURN_TICKET);
//                }
//            }
//        });
//        planeAlterDetailBt2.setText(Utils.getString(R.string.place_after_ticket_changes));
//        planeAlterDetailBt2.setOnClickListener(new OnMultiTypeListener() {
//            @Override
//            public void onOneClick(View v) {
//                super.onOneClick(v);
//                if (planeFooterPresenter.isInternational) {
//                    showCallPhone(R.string.cantGqTpNotice);
//                }else {
//                    planeFooterPresenter.tuipiao();
//                    MobclickAgent.onEvent(context, EVENT_ARI_ALTER_TICKETS);
//                }
//            }
//        });
//    }
//
//    protected abstract void showCallPhone(int stringId);
//
//    @Override
//    public void hideAllBotton() {
//        planeAlterDetailBt1.setVisibility(View.GONE);
//        planeAlterDetailBt2.setVisibility(View.GONE);
//        layoutApproveChose.setVisibility(View.GONE);
//        planeAlterDetailApproveinfo.setVisibility(View.GONE);
//    }
//
//
//    @Override
//    public void initBanner() {
//        routeCardBannerPresenter.getBannerAdapter().register(RoutesBean.class, new RoutesBeanViewBinder());
//        routeCardBannerPresenter.getBannerAdapter().setItems(routeCardBannerPresenter.getRouteItems());
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        bannerRV.setLayoutManager(linearLayoutManager);
//        bannerRV.setAdapter(routeCardBannerPresenter.getBannerAdapter());
//        // mRecyclerView绑定scale效果
//        mBannerScaleHelper = new BannerScaleHelper();
////        mBannerScaleHelper.setFirstItemPos(items.size()*1000);
//        mBannerScaleHelper.attachToRecyclerView(bannerRV);
//        if (routeCardBannerPresenter.isInternational())
//            initRecyclerViewIndicator();
//    }
//
//    @Override
//    public void refreshRouteCards(boolean isInternational, ArrayList<RoutesBean> routesBeans) {
//        if (isInternational)
//            initRecyclerViewIndicator();
//        routeCardBannerPresenter.getRouteItems().clear();
//        routeCardBannerPresenter.getRouteItems().addAll(routesBeans);
//        routeCardBannerPresenter.getBannerAdapter().notifyDataSetChanged();
//        if (isInternational) {
//            if (isNull(routeCardBannerPresenter.getIndicatorAdapter()))
//                routeCardBannerPresenter.initIndicatorAdapter();
//            routeCardBannerPresenter.getIndicatorAdapter().setSize(routeCardBannerPresenter.getRouteItems().size());
//            indicatorContainer.setAdapter(routeCardBannerPresenter.getIndicatorAdapter());
//        }
//        refreshIndicator(0);
//    }
//}
//*/
