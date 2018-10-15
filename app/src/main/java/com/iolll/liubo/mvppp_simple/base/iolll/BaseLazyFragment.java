package com.iolll.liubo.mvppp_simple.base.iolll;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.iolll.liubo.mvppp_simple.R;
import com.iolll.liubo.mvppp_simple.utils.RxLifecycleUtils;
import com.iolll.liubo.mvppp_simple.utils.Utils;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 当使用viewpager加载Fragment，沉浸式的使用，原理懒加载
 * Created by geyifeng on 2017/4/7.
 * Changed by liubo on 2018/10/10.
 */
public abstract class BaseLazyFragment<V> extends Fragment implements IView {
    View systemBar;
    protected Activity mActivity;
    protected View mRootView;
    public ArrayList<? extends Presenter<V>> presenters;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsPrepare;

    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsImmersion;

    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, mRootView);
        if (isLazyLoad()) {
            mIsPrepare = true;
            mIsImmersion = true;
            onLazyLoad();
        } else {
            initData();
            if (isImmersionBarEnabled())
                initImmersionBar();
        }
        //创建presenter
        presenters = createPresenter();
        //绑定(presenter和View进行绑定)
        if (null != presenters) {
            for (Presenter<V> presenter : presenters) {
                presenter.attachView((V) this);
            }

        }

        initView();
        setListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        //绑定(presenter和View进行绑定)
        if (null != presenters)
            for (Presenter<V> presenter : presenters) {
                presenter.detachView();
            }

        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    /**
     * 是否懒加载
     *
     * @return the boolean
     */
    protected boolean isLazyLoad() {
        return true;
    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisible() {
        onLazyLoad();
    }

    private void onLazyLoad() {
        if (mIsVisible && mIsPrepare) {
            mIsPrepare = false;
            initData();
        }
        if (mIsVisible && mIsImmersion && isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    /**
     * Sets layout id.
     *
     * @return the layout id
     */
    protected abstract int setLayoutId();

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        System.out.println("  initImmersionBar ");
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度;
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
        systemBar = mRootView.findViewById(R.id.systemBar);
        if (null!=systemBar)
            ImmersionBar.setStatusBarView(getActivity(),systemBar);
        System.out.println(" initImmersionBar bar" + systemBar);
    }

    /**
     * view与数据绑定
     */
    protected void initView() {

    }

    /**
     * 设置监听
     */
    protected void setListener() {

    }

    /**
     * 用户不可见执行
     */
    protected void onInvisible() {

    }
    public  ArrayList<? extends Presenter<V>> createPresenter(){
        return null;
    }
    /**
     * 找到activity的控件
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findActivityViewById(@IdRes int id) {
        return (T) mActivity.findViewById(id);
    }





    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
    }
    @Override
    public void showToast(int msgId) {
        Utils.toast(msgId);
    }

    @Override
    public void showToast(String msg) {
        Utils.toast(msg);
    }
}
