package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hesuxiang on 18/2/8.
 */

public class DownTutorialJson {

    private static Context mContext = null;
    private static String ONLINE_TUTORIAL_URL = "https://raw.githubusercontent.com/wiki/hesuxiang/mincreafting/tutorial/tutorial.json";

    private static String StringTutorialJson = null;

    public static  String DownTutorialJson(Context context){

        mContext = context;


        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try {
                    URL url = new URL(ONLINE_TUTORIAL_URL);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setConnectTimeout(60 * 1000);
                    urlConnection.setReadTimeout(600 * 1000);
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    line = bufferedReader.readLine();

                    while (line != null) {
                        StringTutorialJson += line;
                        line = bufferedReader.readLine();
                    }

                } catch (IOException e) {
                    Toast.makeText(mContext, "获取在线教程列表失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }



            }
        };
        timer.schedule(timerTask, 0);
        return  StringTutorialJson;
    }
}
