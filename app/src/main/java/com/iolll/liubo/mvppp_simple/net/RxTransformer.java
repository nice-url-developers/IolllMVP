package com.iolll.liubo.mvppp_simple.net;


import android.annotation.SuppressLint;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * 处理Rx线程
 * Created by LiuBo on 2018/5/16.
 */
public class RxTransformer {
    private static final String TAG = "RxTransformer";

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> transformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

//    public static  <T> AutoDisposeConverter<T> autoDisposable(LifecycleOwner lifecycleOwner){
//        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner));
//    }

//    /**
//     * json to list
//     * 转换json 同时将非200情况转为服务器异常
//     *
//     * @param <T>
//     * @return
//     */
//    public static <T> Function<? super ResponseOuterBean, ? extends ObservableSource<BaseResponseBean<T>>> gsonFrom() {
//        return new Function<ResponseOuterBean, ObservableSource<BaseResponseBean<T>>>() {
//            @Override
//            public ObservableSource<BaseResponseBean<T>> apply(@NonNull ResponseOuterBean responseOuterBean) throws Exception {
//                if (responseOuterBean.getStatus() == 200) {
//                    if (!TextUtils.isEmpty(responseOuterBean.getData())) {
//                        Type typeToken = new TypeToken<T>() {
//                        }.getType();
//                        Gson gson = new Gson();
//                        T d = gson.fromJson(responseOuterBean.getData(), typeToken);
//                        return Observable.just(new BaseResponseBean<T>(responseOuterBean, d));
//                    }
//                    return Observable.error(new Exception("noData"));
//                }
//                throw new ServerException(responseOuterBean.getStatus(), responseOuterBean.getMsg());
//            }
//        };
//    }

//    /**
//     * json to Bean
//     * 转换json 同时将非200情况转为服务器异常
//     *
//     * @param beanClass
//     * @param <T>
//     * @return
//     */
//    public static <T> Function<? super ResponseOuterBean, ? extends ObservableSource<BaseResponseBean<T>>> gsonFrom(final Class beanClass) {
//        return new Function<ResponseOuterBean, ObservableSource<BaseResponseBean<T>>>() {
//            @Override
//            public ObservableSource<BaseResponseBean<T>> apply(@NonNull ResponseOuterBean responseOuterBean) throws Exception {
////                if (responseOuterBean.getStatus() == 200) {
////                    if (!TextUtils.isEmpty(responseOuterBean.getData())) {
//                Gson gson = new Gson();
//                T d = (T) gson.fromJson(responseOuterBean.getData(), beanClass);
//                return Observable.just(new BaseResponseBean<>(responseOuterBean, d));
////                    }
////                    return Observable.error(new Exception("noData"));
////                }
////                throw new ServerException(responseOuterBean.getStatus(), responseOuterBean.getMsg());
//            }
//        };
//    }
//
//    public static <T> Function<BaseResponseBean<T>, ObservableSource<? extends BaseResponseBean<T>>> observableTransformer() {
//        return new Function<BaseResponseBean<T>, ObservableSource<? extends BaseResponseBean<T>>>() {
//            @Override
//            public ObservableSource<? extends BaseResponseBean<T>> apply(@NonNull BaseResponseBean<T> tBaseResponseBean) throws Exception {
//                if (tBaseResponseBean.getStatus() == 200) {
//
//                    return Observable.just(tBaseResponseBean);
//
//                }
//                throw new ServerException(tBaseResponseBean.getStatus(), tBaseResponseBean.getMsg());
//            }
//        };
//    }
//
//    public static void zipResult2(Observable<BaseResponseBean<HotailRoot>> observable, Observable<BaseResponseBean<HotelDetailDTO>> observable1) {
//
//    }
//
//
//    public static class HttpResultFunc<T> implements Function<Throwable, Observable<T>> {
//        @Override
//        public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
//            return Observable.error(ExceptionEngine.handleException(throwable));
//        }
//    }

//    /**
//     * zip return 转换
//     *
//     * @param <T1>
//     * @param <T2>
//     * @return
//     */
//    public static <T1, T2> BiFunction<T1, T2, BiFunctionR<T1, T2>> zipResult() {
//        return new BiFunction<T1, T2, BiFunctionR<T1, T2>>() {
//            @Override
//            public BiFunctionR<T1, T2> apply(@NonNull T1 t1, @NonNull T2 t2) throws Exception {
//                return new BiFunctionR<>(t1, t2);
//            }
//        };
//    }
//

    /**
     * 网络异常重试机制
     * 默认为
     *
     * @return
     */
    public static Function<Observable<? extends Throwable>, Observable<?>> retryDelay() {
        final int maxRetries = 3;
        final int retryDelayMillis = 3000;
        final int[] retryCount = {0};

        return new Function<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> apply(@NonNull Observable<? extends Throwable> observable) throws Exception {
                return observable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {

                        if (throwable instanceof IOException) {
                            if (++retryCount[0] < maxRetries) {
                                // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).

                                Log.e(TAG, "apply: 😈 发生异常 等待 " + retryDelayMillis
                                        + " 毫秒后准备第  " + retryCount[0] + " 次重试 ", null);
                                return Observable.timer(retryDelayMillis,
                                        TimeUnit.MILLISECONDS);
                            } else if (retryCount[0] == maxRetries) {
                                Log.e(TAG, "apply: 😈 发生异常 等待 " + retryDelayMillis
                                        + " 毫秒后最后一次重试！ ", null);
                                return Observable.timer(retryDelayMillis,
                                        TimeUnit.MILLISECONDS);
                            }
                        }
                        // Max retries hit. Just pass the error along.
                        return Observable.error(throwable);

                    }
                });
            }
        };

    }


    /**
     * 统一线程处理 绑定生命周期
     * @param tLifecycleTransformer
     * @param <T>
     * @return
     */
//    public static <T> ObservableTransformer<T, T> transformer(LifecycleTransformer<T> tLifecycleTransformer) {
//        return new ObservableTransformer<T,T>(){
//            @Override
//            public ObservableSource<T> apply(Observable<T> upstream) {
//                return upstream.subscribeOn(Schedulers.io())
//                        .unsubscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .compose(tLifecycleTransformer);
//            }
//        };
//    }


    /**
     * 创建 subject
     *
     * @param observables
     * @param <T>
     * @return
     */
    @SuppressLint("CheckResult")
    public static <T> PublishSubject<T> fromArray(final Observable<T>... observables) {
        final PublishSubject<T> subject = PublishSubject.create();
        final int[] onNexts = {0};
        for (Observable<T> o : observables) {
            o.subscribe(new Consumer<T>() {
                @Override
                public void accept(T t) throws Exception {
                    subject.onNext(t);
                    onNexts[0]++;
                    if (onNexts[0] == observables.length) subject.onComplete();
                }
            });
        }
//        subject.filter(new Predicate<BaseResponseBean<PlaneAlterDetailBean>>() {
//            @Override
//            public boolean test(BaseResponseBean<PlaneAlterDetailBean> s) throws Exception {
//                if (isEmpty(s.getMsg()))
//                    return true;
//                return false;
//            }
//        });

        return subject;
    }
}

