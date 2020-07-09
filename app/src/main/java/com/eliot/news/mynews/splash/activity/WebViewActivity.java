package com.eliot.news.mynews.splash.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.eliot.news.mynews.R;
import com.eliot.news.mynews.splash.bean.Action;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.splash.activity
 * @ClassName: WebViewActivity
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/9 11:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 11:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WebViewActivity extends Activity {
    public static final String ACTION_NAME = "action";
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Action action = (Action)getIntent().getSerializableExtra(ACTION_NAME);
        setContentView(R.layout.activity_webview);
        webview = findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.loadUrl(action.getLink_url());
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack())
        {
            webview.goBack();
            return;
        }
        super.onBackPressed();
    }
}
