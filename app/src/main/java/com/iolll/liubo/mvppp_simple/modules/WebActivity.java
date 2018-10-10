package com.iolll.liubo.mvppp_simple.modules;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.iolll.liubo.mvppp_simple.R;
import com.iolll.liubo.mvppp_simple.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * H5 交互测试
 */
public class WebActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        WebSettings webSettings = webView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        webView.addJavascriptInterface(new AndroidtoJs(), "test");//AndroidtoJS类对象映射到js的test对象
        webView.loadUrl("http://192.168.1.118:8080/");
    }
    public class AndroidtoJs extends Object {

        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public String showToast(String msg) {
            Utils.toast("JS调用了Android的showToast方法");
            return "返回值测试";
        }
    }

}
