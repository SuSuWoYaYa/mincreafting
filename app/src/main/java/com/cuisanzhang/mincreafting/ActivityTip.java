package com.cuisanzhang.mincreafting;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityTip extends AppCompatActivity {

    //http://owpvbuvtf.bkt.clouddn.com/vip.txt
    static String URL = "http://cuisanzhang.u.qiniudn.com/vip.txt";

    ImageView TipimageView;
    Button btnCheckVip ;
    Button btn_weixin;
    Button btn_zhifubao;
    private Handler mHandler;
    String result = "";
    String userName;

    boolean isVip ;

    boolean isNetworkConnected = false;

    private String language;
    private boolean is_language_of_traditional_chinese  = false;

    private String noAd;

    private String notVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
//        int color = Utils.ChangeTheme.getTitleColor(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        initActionBar();

        language = LanguageUtil.getLocaleLanguage(ActivityTip.this);
        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
            is_language_of_traditional_chinese = true;
        }



        TipimageView = (ImageView) findViewById(R.id.ImageViewTip);

        btn_weixin = (Button) findViewById(R.id.btn_weixin);
        btn_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipimageView.setImageResource(R.drawable.weixin);
            }
        });
        btn_zhifubao =(Button)  findViewById(R.id.btn_zhifubao);
        btn_zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipimageView.setImageResource(R.drawable.zhifubao);
            }
        });


        userName = SettingUtils.ChangeTheme.getUserName(ActivityTip.this);
        isVip = SettingUtils.ChangeTheme.getVipState(ActivityTip.this);

        btnCheckVip = (Button) findViewById(R.id.btnCheckVip);

        if (is_language_of_traditional_chinese)
        {
            noAd = "廣告已經沒有啦";
            notVip = "嗯! 一定是你點錯了";
            btn_zhifubao.setText("支付寶");
            btnCheckVip.setText("去廣告");
        }else {
            noAd = "广告已经没有了";
            notVip = "嗯! 一定是你点错了";
            btn_zhifubao.setText("支付宝");
            btnCheckVip.setText("去广告");
        }


        btnCheckVip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (isVip || userName.equals(getString(R.string.tip_back_door))){
                    Toast.makeText(ActivityTip.this, noAd, Toast.LENGTH_SHORT).show();
                    SettingUtils.ChangeTheme.setVipState(ActivityTip.this, true);
                    return;
                }

                else  {
                    Toast.makeText(ActivityTip.this, notVip, Toast.LENGTH_SHORT).show();
                    return;
                }
//                isNetworkConnected = SettingUtils.isNetworkConnected(ActivityTip.this);
//
//                if (!isNetworkConnected){
//                    Toast.makeText(ActivityTip.this, R.string.tip_no_network, Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                mHandler = new ActivityTip.MyHandler();
//                Timer timer = new Timer();
//                TimerTask timerTask = new TimerTask() {
//                    @Override
//                    public void run() {
//
//                        Message message = mHandler.obtainMessage();
//                        message.what = 0;
//                        try {
//                            result  = new Vip().run(URL);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        String[] vips = result.split("\n");
//
//
//                        for (int i = 0; i < vips.length; i++){
//                            if (userName.equals(vips[i])){
//                                isVip = true;
//                                message.what = 1;
//                                break;
//                            }
//                        }
//
//
//
////                        message.obj = result;
//                        mHandler.sendMessage(message);
//                    }
//                };
//                timer.schedule(timerTask,0);




            }

        });
//        Button btn_save_image = (Button) findViewById(R.id.btn_save_image);
//        btn_save_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
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

//
//    private class MyHandler extends Handler {
//
//        @Override
//        public void handleMessage(Message msg) {
//
//            switch (msg.what) {
//                case 0:
////                    result = (String) msg.obj;
//                    Toast.makeText(ActivityTip.this, R.string.tip_you_are_not_a_vip, Toast.LENGTH_SHORT).show();
////                    Toast.makeText(ActivityTip.this, result, Toast.LENGTH_SHORT).show();
//                    break;
//                case 1:
////                    result = (String) msg.obj;
//                    Toast.makeText(ActivityTip.this, R.string.tip_you_are_vip_now, Toast.LENGTH_SHORT).show();
//
//                    SettingUtils.ChangeTheme.setVipState(ActivityTip.this, true);
//                    break;
//                default:
//                    Toast.makeText(ActivityTip.this, R.string.tip_check_vip_error, Toast.LENGTH_SHORT).show();
//                    break;
//            }
//            super.handleMessage(msg);
//
//        }
//    }
}
