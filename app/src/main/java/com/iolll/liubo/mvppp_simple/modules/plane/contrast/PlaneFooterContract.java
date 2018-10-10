package com.iolll.liubo.mvppp_simple.modules.plane.contrast;

/**
 * Created by LiuBo on 2018/9/28.
 */
public class PlaneFooterContract {
    public interface V { // 输出到view
        /**
         * 处理中
         */
       void inHand();

        /**
         * 审批中
         */
        void inApproval();

        /**
         * 审批否决
         */
        void approvalVeto();

        /**
         * 已取消
         */
        void canc();

        /**
         * 待改签
         * 等待确认改签
         * 底部按钮可以显示确认改签 或者支付 根据 支付方式 isMonthly ？ "确认改签" ： 支付
         * @param isMonthly 是否月结
         */
        void waitConfirmationAlterTickets(boolean isMonthly);

        /**
         * 改签中
         */
        void alteringTicket();

        /**
         * 已改签
         */
        void alteredTicket();

        void hideAllBotton();//隐藏所有按钮
    }

    public interface P { // 从view获取事件
        /**
         * 0处理中 1审批中 2审批否决 3已取消 4待改签 5改签中 6已改签
         * @param status
         * @param isMonthly
         */
        void refreshFooterButton(boolean isInternational, int status, boolean isMonthly);
        void tuipiao();

        void cancleOrder();

        void confirmOutTicket();
    }
}
