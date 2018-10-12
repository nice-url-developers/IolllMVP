package com.iolll.liubo.mvppp_simple.modules.personal.v_contrast;

import com.iolll.liubo.mvppp_simple.base.iolll.IView;
import com.iolll.liubo.mvppp_simple.model.net.User;

/**
 * Created by LiuBo on 2018/10/9.
 */
public class LoginContract {
    public interface V extends IView {
        void loginComplete();
    }
    public interface P {
        void doLoin(User user);
    }
}
