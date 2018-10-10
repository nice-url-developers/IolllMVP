package com.iolll.liubo.mvppp_simple.net;


import com.iolll.liubo.mvppp_simple.model.Result;
import com.iolll.liubo.mvppp_simple.model.net.GankRoot;
import com.iolll.liubo.mvppp_simple.model.net.User;

import io.reactivex.Observable;

/**
 * 数据管理器
 */
public class DataManager {

    private static Object daily;

    public static Observable<Result<User>> login(User user) {
        return RxRetrofitManager.getInstance().getApiService().login(user.getUserName(),user.getPassWord())
                .compose(RxTransformer.<Result<User>>transformer());//线程切换;

    }

    public static  Observable<GankRoot> getDaily() {
        return RxRetrofitManager.getInstance().getApiService().getDaily()
                .compose(RxTransformer.<GankRoot>transformer());//线程切换;
    }


//
//    public static Observable<BaseResponseBean<PlaneAlterDetailBean>> getPlaneAlterDetail(String orderNo) {
//        Map<String, String> map = new HashMap<>();
//        map.put("gqorderno", orderNo);
//        map.put("cid", "180");
//        String data = AppUtils.getJson(map);
//        return RxRetrofitManager.getInstance().getApiService()
//                .getPlaneAlterDetail(data, Constant.APPKEY, CommonRequestParams.getSign(data))
//                .concatMap(RxTransformer.<PlaneAlterDetailBean>gsonFrom(PlaneAlterDetailBean.class))//json 转换  非200 异常转换
//                .onErrorResumeNext(new RxTransformer.HttpResultFunc<BaseResponseBean<PlaneAlterDetailBean>>())//异常拦截 转换
//                .compose(RxTransformer.<BaseResponseBean<PlaneAlterDetailBean>>transformer());//线程切换
//
//    }
//
//    /**
//     * 数据多级缓存 Rx实现 请求一处三处提供数据
//     * 但最终结果以网络为主 （未做）
//     * P层处理结果方式以及ui也应该做出改变，即服务端失败但用户也可以查看从缓存获取的数据，但要在界面上展示服务端无响应的情况以供用户发起重试
//     * loadSir 这种将页面全覆盖的情况便不太符合 当前的需求了 ，但也有三方都无数据的情况 需要loadSir 进行展示
//     * 像这种情况，对于onNext来的数据，在ui上是否需要真切展示进度 即 内存数据返回成功，文件缓存数据返回成功，然后有个常驻ui前台的显示区域 以供显示数据来源
//     * 以及 用户发起重试！
//     * 网络数据返回成功   按理说网络数据一旦返回会将数据同步至所有缓存模块，然后结束该请求的请求状态。
//     *
//     * @param orderNo
//     * @return
//     */
//    public static PublishSubject<BaseResponseBean<PlaneAlterDetailBean>> getPlaneAlterDetailNoUse(String orderNo) {
//        return RxTransformer.fromArray(Observable.just(new BaseResponseBean<PlaneAlterDetailBean>()),
//                getPlaneAlterDetail(orderNo)
//        );
//    }
}
