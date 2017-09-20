package com.cuisanzhang.mincreafting;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hesuxiang on 17/3/20.
 */

public class ActivityWebViewFeedback extends  AppCompatActivity {
    private String MyFeedbackWeb = "https://www.wenjuan.com/s/QbMfEr4/";
    WebView mWebview;
    WebSettings mWebSettings;
    TextView beginLoading, endLoading, loading, mtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = Utils.ChangeTheme.getTheme(getApplicationContext());
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview_feedback);
        initActionBar();

        mWebview = (WebView) findViewById(R.id.webView);

        mWebSettings = mWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);

        mWebview.loadUrl(MyFeedbackWeb);


        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                view.loadUrl(MyFeedbackWeb);
                return super.shouldOverrideUrlLoading(view, request);
            }
            //            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(MyFeedbackWeb);
//                return true;
//            }
        });

        //设置WebChromeClient类
        mWebview.setWebChromeClient(new WebChromeClient() {
            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
            }
            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }
        });
      
        //点击后退按钮,让WebView后退一页(也可以覆写Activity的onKeyDown方法)  
        mWebview.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {  //表示按返回键时的操作
                        mWebview.goBack();   //后退

                        //webview.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });

    }

        //销毁Webview
        @Override
        protected void onDestroy () {
            if (mWebview != null) {
                mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
                mWebview.clearHistory();

                ((ViewGroup) mWebview.getParent()).removeView(mWebview);
                mWebview.destroy();
                mWebview = null;
            }
            super.onDestroy();
        }

    @Override
    protected void onResume() {

        mWebSettings.setJavaScriptEnabled(true);
        super.onResume();
    }

    @Override
    protected void onPause() {

        mWebSettings.setJavaScriptEnabled(false);
        super.onPause();
    }

    public void initActionBar() {
        ImageView imageViewMenu = (ImageView)findViewById(R.id.imageViewToolbar_menu);
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        Toolbar toolbar = (Toolbar) findViewById(R.id.webView_toolbar);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
    }

}
