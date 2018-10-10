package com.iolll.liubo.mvppp_simple.model.net;

import com.iolll.liubo.mvppp_simple.R;

/**
 * Created by LiuBo on 2018/10/9.
 */
public class FuLi   {
    private String desc;
    private String url;
    private int icon = R.drawable.ic_launcher_background;
    public FuLi(String desc, String url) {
        this.desc = desc;
        this.url = url;
    }

    public FuLi(String home, String s, int ic_launcher_background) {
        this(home,s);
        icon = ic_launcher_background;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
