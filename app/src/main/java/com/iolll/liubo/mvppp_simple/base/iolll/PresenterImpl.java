package com.iolll.liubo.mvppp_simple.base.iolll;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import com.iolll.liubo.mvppp_simple.utils.RxLifecycleUtils;
import com.uber.autodispose.AutoDisposeConverter;

/**
 * Created by LiuBo on 2018/9/28.
 */
public class PresenterImpl<V> implements Presenter<V> {
    public V v;
    @Override
    public void attachView(V v) {
        this.v = v;
    }

    @Override
    public void detachView() {
        v= null;
    }

    @Override
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public void onCreate(LifecycleOwner owner) {

    }

    @Override
    public void onStart(LifecycleOwner owner) {

    }

    @Override
    public void onResume(LifecycleOwner owner) {

    }

    @Override
    public void onPause(LifecycleOwner owner) {

    }

    @Override
    public void onStop(LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        detachView();
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {

    }


    private LifecycleOwner lifecycleOwner;

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        if (null == lifecycleOwner)
            throw new NullPointerException("lifecycleOwner == null");
        return RxLifecycleUtils.bindLifecycle(lifecycleOwner);
    }


}
