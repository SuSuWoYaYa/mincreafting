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
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class ActivityTutorialWebView extends AppCompatActivity {

    public static String EXTRA_URI = "file_name";
    public static String EXTRA_TUTORIAL_NAME = "tutorial_name";

    WebView mWebview;
    WebSettings mWebSettings;
    private String uri = "http://10minute.cn/2017/09/30/%E3%80%8A%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C%E3%80%8B%E6%A2%AF%E7%94%B0%E5%BD%A2%E5%8D%8A%E8%87%AA%E5%8A%A8%E6%94%B6%E5%89%B2%E6%9C%BA%E5%88%B6%E4%BD%9C%E5%9B%BE%E8%A7%A3/";
    private String tutorial_name = "错误页面!";

//打开本包内asset目录下的index.html文件
//wView.loadUrl(" file:///android_asset/index.html ");
//
////打开本地sd卡内的index.html文件
//wView.loadUrl("content://com.android.htmlfileprovider/sdcard/index.html");
//
////打开指定URL的html文件
//wView.loadUrl(" http://m.oschina.net");

    private boolean isNetworkConnected = false;
    private boolean isVip;
    private AdView mAdView;

    private  boolean showAd = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview_tutorial);


        Intent intent = getIntent();
        uri = intent.getStringExtra(EXTRA_URI);
        tutorial_name = intent.getStringExtra(EXTRA_TUTORIAL_NAME);


        initActionBar();

        isVip = SettingUtils.ChangeTheme.getVipState(ActivityTutorialWebView.this);

        isNetworkConnected = SettingUtils.isNetworkConnected(ActivityTutorialWebView.this);

        mAdView = (AdView) findViewById(R.id.adView);


        //3分之一的几率广告
        int random = new Random().nextInt(6);

        if(random < 2) {
            showAd = true;
        }

        if (!isVip && isNetworkConnected && showAd) {
////        google admob
            MobileAds.initialize(this, getString(R.string.admob_uni_id));
            mAdView.loadAd(new AdRequest.Builder().build());
//            //add admob in listView
//            LinearLayout tipEndViw = (LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.admob_native_layout, null);
//            NativeExpressAdView nativeExpressAdView = (NativeExpressAdView) tipEndViw.findViewById(R.id.nativeExpressAdView);
//
//
//            if (!isNetworkConnected) {
//                nativeExpressAdView.setVisibility(View.GONE);
//            } else {
//                nativeExpressAdView.loadAd(new AdRequest.Builder().build());
//            }
//            listView.addFooterView(tipEndViw);
        }else {
            if(mAdView != null){
               mAdView.setVisibility(View.GONE);
            }

        }

        mWebview = (WebView) findViewById(R.id.webView);
        mWebSettings = mWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setDomStorageEnabled(true);

        //缓存图片设置
        if( SettingUtils.getSwitchCacheSetting(ActivityTutorialWebView.this) ) {
            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            mWebSettings.setDomStorageEnabled(true);
            mWebSettings.setAppCacheEnabled(true);
        }else {
            //设置不缓存的时候清除缓存
            mWebview.clearCache(true);
        }

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
        TextView titleBar = (TextView) findViewById(R.id.titleBar);
        titleBar.setText(tutorial_name);
//        setSupportActionBar(toolbar);
        ImageView imageViewRefresh_menu = (ImageView)findViewById(R.id.imageViewRefresh_menu);
        imageViewRefresh_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebview.reload();
            }
        });
    }
}
