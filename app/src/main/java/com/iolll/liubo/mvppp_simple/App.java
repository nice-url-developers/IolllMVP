package com.iolll.liubo.mvppp_simple;

import android.app.Application;

import com.iolll.liubo.mvppp_simple.utils.Utils;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

/**
 * Created by LiuBo on 2018/10/8.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(false)
                .setSupportSP(false)
                .setSupportSubunits(Subunits.MM);
    }
}
