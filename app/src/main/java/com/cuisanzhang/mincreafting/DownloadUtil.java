package com.cuisanzhang.mincreafting;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by hesuxiang on 17/10/9.
 */

public class DownloadUtil {

    private static DownloadUtil downloadUtil;
    private final OkHttpClient okHttpClient;
    private Activity mActivity;
    private static String TAG = "DownloadUtil";

    public static DownloadUtil get(Activity activity) {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil(activity);
        }
        return downloadUtil;
    }

    private DownloadUtil(Activity activity) {
        okHttpClient = new OkHttpClient();
        mActivity = activity;
    }

    /**
     * @param url      下载连接
     * @param saveDir  储存下载文件的SDCard目录
     * @param listener 下载监听
     */
    public void download(final String url, final String saveDir, final OnDownloadListener listener) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                String savePath = isExistDir(saveDir);

                if (savePath == null) {
                    Log.e(TAG, "savePath = null");
                    return;
                }
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(savePath, getNameFromUrl(url));
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        listener.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    listener.onDownloadSuccess();
                } catch (Exception e) {
                    listener.onDownloadFailed();
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * @param saveDir
     * @return
     * @throws IOException 判断下载目录是否存在
     */
    private String isExistDir(String saveDir) throws IOException {
        // 下载到缓存目录
//        Log.e("DownloadUtil", "context.getCacheDir().getAbsolutePath() = " +context.getCacheDir().getAbsolutePath());

       if (mActivity == null ){
           Log.e(TAG, "context = null");
           return null;
       }
        File downloadFile = new File(mActivity.getExternalFilesDir(null), saveDir);
        if (downloadFile == null) {
            Log.e(TAG, "downloadFile = null");
            return  null;
        }
//        Log.e("DownloadUtil", "context.getExternalFilesDir(null).getAbsolutePath() = " + context.getExternalFilesDir(null).getAbsolutePath());


        if (!downloadFile.mkdirs()) {
            Log.e(TAG, "downloadFile.mkdirs() == false");
            return  null;
//            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
                Log.e("DownloadUtil", "downloadFile.getAbsolutePath() = " + downloadFile.getAbsolutePath());

        return savePath;
    }

    /**
     * @param url
     * @return 从下载连接中解析出文件名
     */
    @NonNull
    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }


}

//作者：Obadiah
//        链接：http://www.jianshu.com/p/3b269082cbbb
//        來源：简书
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。