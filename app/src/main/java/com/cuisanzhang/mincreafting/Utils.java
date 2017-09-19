package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络工具类
 * Created by hesuxiang on 17/9/20.
 */

public class Utils {

        public static boolean isNetworkConnected(Context context) {
            if (context != null) {
                // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                // 获取NetworkInfo对象
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();
                //判断NetworkInfo对象是否为空
                if (networkInfo != null)
                    return networkInfo.isAvailable();
            }
            return false;
        }

//        作者：AlicFeng
//        链接：http://www.jianshu.com/p/10ed9ae02775
//        來源：简书
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



    public static String filterString(String str) {
        String[] des = {","
                , "+"
//                ,"("
//                ,")"
//                ,"(基础"
//                ,"还原)"
                , "或"
                , "和"
                , "任何"
                , "对应的"
                , "损坏的"

//                ,"红色"
//                ,"白色"
        };

        for (String item : des) {
            str = str.replace(item, " ");
        }

//        Toast.makeText(ActivityListViewShowBlocks.this, str,Toast.LENGTH_LONG).show();
        str = str.trim();
        return str;
    }
}
