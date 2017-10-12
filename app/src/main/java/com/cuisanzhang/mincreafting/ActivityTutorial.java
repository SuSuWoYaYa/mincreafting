package com.cuisanzhang.mincreafting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class ActivityTutorial extends AppCompatActivity {

    public static String EXTRA_URI = "file_name";
    WebView mWebview;
    WebSettings mWebSettings;
    private String uri = "http://10minute.cn/2017/09/30/%E3%80%8A%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C%E3%80%8B%E6%A2%AF%E7%94%B0%E5%BD%A2%E5%8D%8A%E8%87%AA%E5%8A%A8%E6%94%B6%E5%89%B2%E6%9C%BA%E5%88%B6%E4%BD%9C%E5%9B%BE%E8%A7%A3/";
//    private String uri = "file:///android_asset/html/banzhidongkekedoushougeji.html";

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
        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview_tutorial);

        initActionBar();

        Intent intent = getIntent();
        uri = intent.getStringExtra(EXTRA_URI);

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
