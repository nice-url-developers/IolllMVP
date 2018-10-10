package com.iolll.liubo.mvppp_simple.base.iolll;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.iolll.liubo.mvppp_simple.utils.RxLifecycleUtils;
import com.iolll.liubo.mvppp_simple.utils.Utils;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Date：2018/2/28
 * Time：16:08
 * author： liu bo
 * 基于MVP模式封装的基类Activity
 */

public abstract class MvpActivity<V> extends AppCompatActivity implements View {
    protected Context context;
    public ArrayList<? extends Presenter<V>> presenters;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        context = MvpActivity.this;
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .init();
        //创建presenter
        presenters = createPresenter();
        //绑定(presenter和View进行绑定)
        if (null != presenters) {
            for (Presenter<V> presenter : presenters) {
                presenter.attachView((V) this);
            }

        } else {
            /*throw new NullPointerException("presenter can not be empty");*/
        }

        initLifecycleObserver(getLifecycle());

        //初始化控件
        iniViews();
    }

    private void iniViews() {
        initView();
        initData();//处理getIntent值
        initListener();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    protected abstract int getLayoutId();

    public abstract ArrayList<? extends Presenter<V>> createPresenter();

    protected abstract void initData();
    protected void initView(){

    }
    protected void initListener() {
    }

    protected abstract void getData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        //解绑
        if (null != presenters) {
            for (Presenter<V> presenter : presenters) {
                presenter.detachView();
            }
        }
    }

    @MainThread
    protected void initLifecycleObserver(Lifecycle lifecycle) {
        if (null != presenters)
            for (Presenter<V> presenter : presenters) {
                presenter.attachView((V) this);
                presenter.setLifecycleOwner(getLifecycleOwner());
                lifecycle.addObserver(presenter);
            }

    }

    public LifecycleOwner getLifecycleOwner() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
        getData();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
    }

    @Override
    public void showToast(String msg) {
        Utils.toast(msg);
    }

    @Override
    public void showToast(int msgId) {
        Utils.toast(msgId);
    }
}
