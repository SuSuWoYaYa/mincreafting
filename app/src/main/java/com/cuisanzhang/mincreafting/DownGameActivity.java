package com.cuisanzhang.mincreafting;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import static com.cuisanzhang.mincreafting.R.id.btnCheckVip;
import static com.cuisanzhang.mincreafting.R.id.btn_zhifubao;

public class DownGameActivity extends AppCompatActivity {

    String mc163 = "https://mc.163.com/m/pe/index.html";
    //String  mc163 = "https://mc.163.com/download/";
    String googleplay = "https://play.google.com/store/apps/details?id=com.mojang.minecraftpe";
    String other = "https://raw.githubusercontent.com/wiki/hesuxiang/mincreafting/wiki/apk/apk.txt";


    Button btn_down_game_mc163;

    Button btn_down_game_googleplay;

    LinearLayout linearlayout_other;
    Button btn_down_game_other;
    Button btn_other_version;


    Uri content_url;
    URL url;

    String userName;
    boolean isVip;


    private String language;
    private boolean is_language_of_traditional_chinese  = false;

    private String geting;

    private String notVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
//        int color = Utils.ChangeTheme.getTitleColor(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.down_game_layout);
        initActionBar();

        language = LanguageUtil.getLocaleLanguage(DownGameActivity.this);
        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
            is_language_of_traditional_chinese = true;
        }


        btn_down_game_mc163 = (Button) findViewById(R.id.btn_down_game_mc163);

        btn_down_game_googleplay = (Button) findViewById(R.id.btn_down_game_googleplay);

        btn_down_game_mc163.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       Intent intent = new Intent();
//Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                                                       intent.setAction("android.intent.action.VIEW");
                                                       content_url = Uri.parse(mc163);
                                                       intent.setData(content_url);
                                                       startActivity(intent);
                                                   }
                                               }
        );
        btn_down_game_googleplay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent();
//Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        intent.setAction("android.intent.action.VIEW");
                        content_url = Uri.parse(googleplay);
                        intent.setData(content_url);
                        startActivity(intent);
                    }
                }
        );


        linearlayout_other = (LinearLayout) findViewById(R.id.linearlayout_other);
        linearlayout_other.setVisibility(View.GONE);

        btn_down_game_other = (Button) findViewById(R.id.btn_down_game_other);
        btn_down_game_other.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DownGameActivity.this, geting, Toast.LENGTH_SHORT).show();

                        openother();


                    }

                    ;
                });
        btn_other_version = (Button) findViewById(R.id.btn_other_version);
        btn_other_version.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                userName = SettingUtils.ChangeTheme.getUserName(DownGameActivity.this);


                if (userName.equals(getString(R.string.tip_back_door))) {
                    SettingUtils.ChangeTheme.setVipState(DownGameActivity.this, true);
                }

                isVip = SettingUtils.ChangeTheme.getVipState(DownGameActivity.this);

                if (isVip) {
                    linearlayout_other.setVisibility(View.VISIBLE);
                    btn_other_version.setVisibility(View.GONE);
                } else {
                    Toast.makeText(DownGameActivity.this, notVip, Toast.LENGTH_SHORT).show();
                }
            }
        });


        TextView textViewMc163 = (TextView) findViewById(R.id.textViewMc163);
        TextView textViewGoogleplay = (TextView) findViewById(R.id.textViewGoogleplay);
        TextView textViewOther = (TextView) findViewById(R.id.textViewOther);

        if (is_language_of_traditional_chinese)
        {
            geting = "獲取中";
            notVip = "打賞後可見";
            btn_down_game_mc163.setText("下載");
            btn_down_game_googleplay.setText("下載");
            textViewMc163.setText("我的世界-網易版 (免費)");
            textViewGoogleplay.setText("我的世界-GooglePlay (收費)");
            textViewOther.setText("我的世界(國際版)");
            btn_other_version.setText("我的世界(國際版)");

        }else {
            geting = "获取中";
            notVip = "打赏后可见";
            btn_down_game_mc163.setText("下载");
            btn_down_game_googleplay.setText("下载");
            textViewMc163.setText("我的世界-网易版 (免费)");
            textViewGoogleplay.setText("我的世界-GooglePlay (收费)");
            textViewOther.setText("我的世界(国际版)");
            btn_other_version.setText("我的世界(国际版)");
        }


    }


    private void openother() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try {
                    URL url = new URL(other);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setConnectTimeout(60 * 1000);
                    urlConnection.setReadTimeout(600 * 1000);
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    line = bufferedReader.readLine();

                    while (bufferedReader.readLine() != null) {

                    }
                    other = line;
                } catch (IOException e) {
                    Toast.makeText(DownGameActivity.this, "出错了", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


                Intent intent = new Intent();
//Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setAction("android.intent.action.VIEW");
                content_url = Uri.parse(other);
                intent.setData(content_url);
                startActivity(intent);
            }
        };
        timer.schedule(timerTask, 0);

    }

    public void initActionBar() {
        ImageView imageViewMenu = (ImageView) findViewById(R.id.imageViewToolbar_menu);
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
