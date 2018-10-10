package com.iolll.liubo.mvppp_simple.modules.personal.v_contrast.p_impl;

import com.iolll.liubo.mvppp_simple.base.iolll.PresenterImpl;
import com.iolll.liubo.mvppp_simple.model.Result;
import com.iolll.liubo.mvppp_simple.model.net.User;
import com.iolll.liubo.mvppp_simple.modules.personal.v_contrast.LoginContract;
import com.iolll.liubo.mvppp_simple.net.DataManager;
import com.iolll.liubo.mvppp_simple.utils.SpUtil;
import com.iolll.liubo.mvppp_simple.utils.Utils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by LiuBo on 2018/10/9.
 */
public class LoginPresenterImpl extends PresenterImpl<LoginContract.V> implements LoginContract.P {
    @Override
    public void doLoin(final User user) {
        DataManager.login(user).as(this.<Result<User>>bindLifecycle())
        .subscribe(new Observer<Result<User>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result<User> userResult) {
                if (userResult.getCode()==200) {
                    SpUtil.putObject(Utils.getContext(),user);
                    v.loginComplete();
                }else{
                    v.showToast(userResult.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                v.showToast("请求错误！");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
