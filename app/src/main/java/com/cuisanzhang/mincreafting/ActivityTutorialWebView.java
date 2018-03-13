package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class ActivityTutorialWebView extends AppCompatActivity {

    public static String EXTRA_URI = "file_name";
    public static String EXTRA_TUTORIAL_NAME = "tutorial_name";

    private DiskLruCache mDiskLruCache = null;

    private WebView mWebview;
    WebSettings mWebSettings;
    private String url = "http://www.baidu.com/";
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

    private boolean showAd = false;

    private static final int DISK_CACHE_INDEX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview_tutorial);


        Intent intent = getIntent();
        url = intent.getStringExtra(EXTRA_URI);
        tutorial_name = intent.getStringExtra(EXTRA_TUTORIAL_NAME);


        initActionBar();

//        //初始化cachewebview
//        String cachedir = getExternalCacheDir(ActivityTutorialWebView.this, "imageCache");
//        if(cachedir == null)
//            cachedir = getCacheDir(ActivityTutorialWebView.this, "imageCache");
//
//        CacheWebView.getCacheConfig().init(this,getExternalCacheDir().getAbsolutePath(),1024*1024*100,1024*1024*10)
//                .enableDebug(true);//1000M 磁盘缓存空间,100M 内存缓存空间



        isVip = SettingUtils.ChangeTheme.getVipState(ActivityTutorialWebView.this);

        isNetworkConnected = SettingUtils.isNetworkConnected(ActivityTutorialWebView.this);

        mAdView = (AdView) findViewById(R.id.adView);


        //3分之一的几率广告
        int random = new Random().nextInt(6);

        if (random < 2) {
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
        } else {
            if (mAdView != null) {
                mAdView.setVisibility(View.GONE);
            }

        }

        mWebview = (WebView) findViewById(R.id.webView);
//        mWebview.setBlockNetworkImage(true);
        mWebSettings = mWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
//        mWebSettings.setDomStorageEnabled(true);

//        //缓存图片设置
        if (SettingUtils.getSwitchCacheSetting(ActivityTutorialWebView.this)) {
//            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//            //有时候网页需要自己保存一些关键数据,Android WebView 需要自己设置
//            mWebSettings.setDomStorageEnabled(true);
//            mWebSettings.setDatabaseEnabled(true);
//            mWebSettings.setAppCacheEnabled(true);
////            String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
////            Log.e("appCachePath=","appCachePath" );
////            mWebSettings.setAppCachePath(appCachePath);
////            mWebSettings.setAppCacheMaxSize();
//
        } else {
//            //设置不缓存的时候清除缓存
//            mWebview.clearCache(true);
        }

        mWebview.loadUrl(url);


        mWebview.setWebViewClient(new WebViewClientCache(ActivityTutorialWebView.this));


        mDiskLruCache = MyDiskLruCache.newInstance(ActivityTutorialWebView.this).getDiskLruCache();

        //设置可自由缩放网页test.html
//        mWebview.getSettings().setSupportZoom(true);
//        mWebview.getSettings().setBuiltInZoomControls(true);

//        设置不用系统浏览器打开,直接显示在当前Webview
//        mWebview.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, request);
//            }
//                    @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//
//
    }


//    public  class WebViewClientCache extends WebViewClient {
//
//        private static final int MAX_SIZE_CACHE = 1000 * 1024 * 1024;
//        private Context mContext;
//        private DiskLruCache mDiskLruCache = null;
//
//        WebViewClientCache(Context context){
//            mContext = context;
//            initDiskLruCache();
//        }
//
//        public void initDiskLruCache(){
//            if(mDiskLruCache == null || mDiskLruCache.isClosed()){
//                try{
//                    File cacheDir = getDiskCacheDir(mContext, "image");
//                    if (!cacheDir.exists()){
//                        cacheDir.mkdirs();
//                    }
//                    mDiskLruCache = DiskLruCache.open(cacheDir, 1, 1, MAX_SIZE_CACHE );
//                } catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        private InputStream getCache(String url){
//            String key = convertKey(url);
//            Log.e("ActivityTutorialWebView", "key="+key);
//
//            try {
//                DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
////                Log.e("ActivityTutorialWebView", "getCache(), snapshot != null");
////
////                if(snapshot != null) {
////                    InputStream in = snapshot.getInputStream(0);
//////                    return  new WebResourceResponse("image/jpeg","UTF-8", new FileInputStream());
////                    Log.e("ActivityTutorialWebView", "getCache(), snapshot.getInputStream(0)");
////
////                    if (in == null) {
////                        Log.e("ActivityTutorialWebView", "getCache(), snapshot.getInputStream(0)");
////                    } else {
////                        return in;
////                    }
////                }
////
////
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return  null;
//        }
//
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            view.loadUrl(url);
//            return super.shouldOverrideUrlLoading(view, request);
//        }
//
//
//        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//        @Override
//        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//
//            Log.e("ActivityTutorialWebView", "shouldInterceptRequest(request)");
//
//            String url = request.getUrl().toString();
////            try {
////
////                InputStream logo =  getAssets().open("logo.jpg");
////                return new WebResourceResponse("image/jpeg", "UTF-8", logo);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//
//
//
//
//            return super.shouldInterceptRequest(view, request);
//        }
//
//        @Override
//        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//            Log.e("ActivityTutorialWebView", "shouldInterceptRequest(url)");
////            Toast.makeText(MainActivity.this, "shouldInterceptRequest(url)",Toast.LENGTH_SHORT).show();
//
////            try {
////
////                InputStream in =  getAssets().open("logo.jpg");
////                return new WebResourceResponse(getMinetype(url), "UTF-8", in);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//
//            InputStream inputStream = getCache(url);
//
//            if(inputStream != null){
//                Log.e("ActivityTutorialWebView", "shouldInterceptRequest(url) inputStream != null return new WebResourceResponse(getMinetype(url), \"UTF-8\", inputStream)");
////                return new WebResourceResponse(getMinetype(url), "UTF-8", inputStream);
//            }
//
//            return super.shouldInterceptRequest(view, url);
//        }
//    }
//
//
//    public File getDiskCacheDir(Context context, String uniqueName) {
//        String cachePath;
//        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
//                || !Environment.isExternalStorageRemovable()){
//            cachePath = context.getExternalCacheDir().getPath();
//        }else {
//            cachePath = context.getCacheDir().getPath();
//        }
//
////        Log.e("getExternalFilesDirPath", context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath());
////        Log.e("getAbsolutePath", context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
////
////        Log.e("getFilesDirDirPath",  context.getFilesDir().getPath());
////        Log.e("getAbsolutePath",  context.getFilesDir().getAbsolutePath());
//
//
//        File cacheDir = new File(cachePath + File.separator + uniqueName);
//        if (!cacheDir.exists()){
//            cacheDir.mkdirs();
//        }
//        Log.e("cacheDir=",  cacheDir.getPath());
//        return  cacheDir;
//    }
//
//
//    //hash code
//    public String convertKey(String key) {
//        String cacheKey;
//
//        try {
//            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//            messageDigest.update(key.getBytes());
//            cacheKey = bytesToString(messageDigest.digest());
//        } catch (NoSuchAlgorithmException e) {
//            cacheKey = String.valueOf(key.hashCode());
//            e.printStackTrace();
//        }
//        return cacheKey + "_cache";
//
//    }
//
//    private String bytesToString(byte[] bytes){
//        StringBuffer sb = new StringBuffer();
//        for (int i  = 0; i < bytes.length; i++){
//            String hex = Integer.toHexString(0XFF & bytes[i]);
//            if(hex.length() == 1){
//                sb.append('0');
//            }
//            sb.append(hex);
//        }
//        return sb.toString();
//    }

    private class WebViewClientCache extends WebViewClient {


        private Context mContext;


        WebViewClientCache(Context context) {
            mContext = context;
        }





        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, request);
        }

//sdk21_api
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

//            Log.e("MainActivity", "shouldInterceptRequest(request)");

                String url = request.getUrl().toString();
            if( isImageUrl(url) ){
                WebResourceResponse response=  getCache(url);
                if(response != null) {
                    return response;
                }
//                 WebResourceResponse response=  getCache(url, 1);
//                InputStream in = getCache(url, 1);
//                if (in != null);
//                    return  new WebResourceResponse(getMinetype(url), "UTF-8", in);unrecoverably
            }


            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            Log.e("MainActivity", "shouldInterceptRequest(url)");
//            Toast.makeText(MainActivity.this, "shouldInterceptRequest(url)",Toast.LENGTH_SHORT).show();



            if( isImageUrl(url) ){
                 WebResourceResponse response=  getCache(url);
                if(response != null) {
                    return response;
                }
//                Log.e("isImageUrl", ""+isImageUrl(url));
//                InputStream in = getCache(url);
//                if (in != null);
//                    return  new WebResourceResponse(getMinetype(url), "UTF-8", in);
            }


            return super.shouldInterceptRequest(view, url);
        }

        private WebResourceResponse getCache(String url) {
            String key = hashKeyFromUrl(url);
//            try {
//                InputStream is = getApplicationContext().getAssets().open("logo.png");
//                return new WebResourceResponse(getMinetype(url), "UTF-8", is);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            try {
                DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
                                Log.e("mDiskLruCache", "snapshot");

                //有缓存
                if (snapshot != null) {
                    Log.e("mDiskLruCache", "snapshot != null)");
                    InputStream in = snapshot.getInputStream(0);
                    Log.e("mDiskLruCache", "return in");
//                    return in;
                    return new WebResourceResponse(getMinetype(url), "UTF-8", in);
                }

                //无缓存
                else {
                    //没网络不下载
                    if( SettingUtils.isNetworkConnected(ActivityTutorialWebView.this) ){
                        //后台开线程下载保存
                        new MyRunnbale(url).run();
                    }

                   //没下载好, 先浏览器自己加载去
                    return null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

//
        private class MyRunnbale implements  Runnable{
            private String url = null;

            MyRunnbale(String url){
                this.url = url;
            }

            @Override
            public void run() {
                String key = hashKeyFromUrl(url);
                DiskLruCache.Editor editor = null;
                try {
                    editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(DISK_CACHE_INDEX);
                        if (downLoadUrlToStream(url, outputStream)) {
                            editor.commit();//提交
                        } else {
                            editor.abort();//重复操作
                        }
                        mDiskLruCache.flush();//刷新
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }

        private boolean downLoadUrlToStream(String urlString, OutputStream outputStream) {

            HttpURLConnection urlConnection = null;
            BufferedOutputStream bos = null;
            BufferedInputStream bis = null;
            try {
                final URL url   = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                bis = new BufferedInputStream(urlConnection.getInputStream());
                bos = new BufferedOutputStream(outputStream);
                int b ;
                while((b = bis.read())!= -1){
                    bos.write(b);
                }
                return  true;
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }

                closeStream(bis);
                closeStream(bos);

            }
            return   false;
        }

    }



//    //优先外部缓存
//    public File getDiskCacheDir(Context context, String uniqueName) {
//        String cachePath;
////        Log.e("cachePath", cachePath);
//        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
//                || !Environment.isExternalStorageRemovable()) {
////            Log.e("cachePath", cachePath);
//            cachePath = context.getExternalCacheDir().getPath();
//        }else {
//            cachePath = context.getCacheDir().getPath();
//        }
//
//
//        File cacheDir = new File(cachePath + File.separator + uniqueName);
//        if (!cacheDir.exists()) {
//            cacheDir.mkdirs();
//        }
//        Log.e("getCacheDir", cacheDir.getAbsolutePath());
//        return cacheDir;
//    }


//    //外部缓存
//    public String getExternalCacheDir(Context context, String uniqueName) {
//        String cachePath = null;
////        Log.e("cachePath", cachePath);
//        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
//                || !Environment.isExternalStorageRemovable()) {
////            Log.e("cachePath", cachePath);
//            cachePath = context.getExternalCacheDir().getPath();
//        }
//
//
//        if( cachePath == null) {
//            Log.e("cachePath", "cachePath == null");
//            return null;
//        }
//        File cacheDir = new File(cachePath + File.separator + uniqueName);
//        if (!cacheDir.exists()) {
//            cacheDir.mkdirs();
//        }
//        Log.e("getExternalCacheDir","cacheDir.getAbsolutePath()"
//        );
//        return cacheDir.getAbsolutePath();
//    }


    //hash code
    public String hashKeyFromUrl(String url) {
        String cacheKey;

        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            cacheKey = bytesToString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
            e.printStackTrace();
        }
        return cacheKey + "_cache";

    }

    private String bytesToString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0XFF & bytes[i]); //得到十六进制字符串
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }




    private boolean isImageUrl(String url){
        if (url.endsWith(".jpg")
                || url.endsWith(".JPG")
                || url.endsWith(".jpeg")
                || url.endsWith(".bmp")
                || url.endsWith(".gif")
                || url.endsWith(".png")) {
            return true;
        }
        return false;
    }



    private String getMinetype(String url){
//        MIME 类型
//        image/bmp	    bmp
//        image/gif	    gif
//        image/jpeg	jpg
//        image/jpeg	jpeg
//        image/png      png

        if (url.endsWith(".jpg")) {
            return "image/jpeg";
        }
        if ( url.endsWith(".JPG")) {
            return "image/jpeg";
        }
        if ( url.endsWith(".jpeg")) {
            return "image/jpeg";
        }
        if (url.endsWith(".bmp")) {
            return "image/bmp";
        }
        if ( url.endsWith(".gif")) {
            return "image/gif";
        }
        if (url.endsWith(".png")) {
            return "image/png";
        }


        return "image/png";
    }

    /**
     * 关闭流
     */
    private void closeStream(Closeable closeable){
        if (closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void initActionBar() {
        ImageView imageViewMenu = (ImageView) findViewById(R.id.imageViewToolbar_menu);
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView titleBar = (TextView) findViewById(R.id.titleBar);
        titleBar.setText(tutorial_name);
//        setSupportActionBar(toolbar);
        ImageView imageViewRefresh_menu = (ImageView) findViewById(R.id.imageViewRefresh_menu);
        imageViewRefresh_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebview.reload();
            }
        });
    }
}
