package com.cuisanzhang.mincreafting;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class ActivityTutorial extends AppCompatActivity {

    WebView mWebview;
    WebSettings mWebSettings;
    private String uri = "file:///android_asset/html/day1.html";

//打开本包内asset目录下的index.html文件
//wView.loadUrl(" file:///android_asset/index.html ");
//
////打开本地sd卡内的index.html文件
//wView.loadUrl("content://com.android.htmlfileprovider/sdcard/index.html");
//
////打开指定URL的html文件
//wView.loadUrl(" http://m.oschina.net");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = Utils.ChangeTheme.getTheme(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview_tutorial);

        initActionBar();

        mWebview = (WebView) findViewById(R.id.webView);
        mWebSettings = mWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setDomStorageEnabled(true);


        mWebview.loadUrl(uri);


        //设置可自由缩放网页test.html
//        mWebview.getSettings().setSupportZoom(true);
//        mWebview.getSettings().setBuiltInZoomControls(true);

        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                view.loadUrl(uri);
                return super.shouldOverrideUrlLoading(view, request);
            }
            //            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(MyFeedbackWeb);
//                return true;
//            }
        });

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
