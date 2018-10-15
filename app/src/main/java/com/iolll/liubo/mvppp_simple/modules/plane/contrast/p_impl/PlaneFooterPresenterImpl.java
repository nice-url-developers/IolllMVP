//package com.iolll.liubo.mvppp_simple.modules.plane.contrast.p_impl;
//
//import android.annotation.SuppressLint;
//import android.util.Log;
//
//import com.auvgo.tmc.base.iolll.PresenterImpl;
//import com.iolll.liubo.mvppp_simple.modules.plane.contrast.PlaneFooterContract;
//
///**
// * Created by LiuBo on 2018/9/28.
// */
//public class PlaneFooterPresenterImpl<V extends PlaneFooterContract.V> extends PresenterImpl<V> implements PlaneFooterContract.P{
//    private static final String TAG = "PlaneFooterPresenterImpl";
//    public boolean isInternational = false;
//
//    @Override
//    public void
//    refreshFooterButton(boolean isInternational,int status,boolean isMonthly) {
//        this.isInternational = isInternational;
//        if (isInternational) {
//            if (status == 6) {//已出票
//                v.alteredTicket();
////                button1_tv.setText(Utils.getString(R.string.place_after_refund_a_ticket));
////                button2_tv.setText(Utils.getString(R.string.place_after_ticket_changes));
////                button1_tv.setBackground(Utils.getDrawable(R.drawable.btn_primary_no_radius));
////                button2_tv.setBackground(Utils.getDrawable(R.drawable.btn_info_no_radius));
////                button2_tv.setTextColor(Color.parseColor("#ffffff"));
////                button1_tv.setVisibility(IView.VISIBLE);
////                button2_tv.setVisibility(IView.VISIBLE);
//            } else {
//                v.hideAllBotton();
//            }
//        }else {
//            switch (status) {
//                case 0:
//                    v.inHand();
//                    break;
//                case 1:
//                    v.inApproval();
//                    break;
//                case 2:
//                    v.approvalVeto();
//                    break;
//                case 3:
//                    v.waitConfirmationAlterTickets(isMonthly);
//                    break;
//                case 4:
//                    v.canc();
//                    break;
//                case 5:
//                    v.alteringTicket();
//                    break;
//                case 6:
//                    v.alteredTicket();
//                    break;
//                default:
//                    statusDefault();
//                    break;
//            }
//        }
//    }
//
//
//    @SuppressLint("LongLogTag")
//    private void statusDefault() {
//        Log.e(TAG, "status code not support ！ ");
//    }
//
//
//    @Override
//    public void tuipiao() {
//
//    }
//
//    @Override
//    public void cancleOrder() {
//
//    }
//
//    @Override
//    public void confirmOutTicket() {
//
//    }
//}
