package com.iolll.liubo.mvppp_simple.modules.main.v_contrast;

import android.support.v4.app.FragmentManager;

import com.iolll.liubo.mvppp_simple.base.iolll.IView;

/**
 * Created by LiuBo on 2018/10/10.
 */
public class MainContrast {
    public interface V extends IView {

    }
    public interface P {

        void initFragmentAdapter(FragmentManager fragmentManager);
    }
}
