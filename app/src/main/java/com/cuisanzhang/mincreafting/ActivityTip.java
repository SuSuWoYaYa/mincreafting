package com.cuisanzhang.mincreafting;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luhuiguo.chinese.ChineseUtils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityTip extends AppCompatActivity {

    //http://owpvbuvtf.bkt.clouddn.com/vip.txt
    static String URL = "http://cuisanzhang.u.qiniudn.com/vip.txt";

    ImageView TipimageView;
    TextView tip_dashang1;
    Button btnCheckVip ;
    Button btn_weixin;
    Button btn_zhifubao;
    private Handler mHandler;
    String result = "";
    String userName;

    boolean isVip ;

    boolean isNetworkConnected = false;

    private String language;
    private boolean is_simplified_chinese  = true;

    private String noAd;

    private String notVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
//        int color = Utils.ChangeTheme.getTitleColor(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        language = LanguageUtil.getLocaleLanguage(ActivityTip.this);
        if (language.equals(LanguageUtil.SIMPLIFIED_CHINESE)) {
            is_simplified_chinese = true;
        }else {
            is_simplified_chinese =false;
        }

        initActionBar();




        TipimageView = (ImageView) findViewById(R.id.ImageViewTip);
        tip_dashang1 = (TextView) findViewById(R.id.tip_dashang1);

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

        if (is_simplified_chinese)
        {
            tip_dashang1.setText("为人民服务也要吃饭\n打赏后请联系作者");
            noAd = "广告已经没有了";
            notVip = "嗯! 一定是你点错了";
            btn_zhifubao.setText("支付宝");
            btnCheckVip.setText("去除广告");
        }else {
            tip_dashang1.setText("為人民服務也要吃飯\n打賞後請聯繫作者");
            noAd = "廣告已經沒有啦";
            notVip = "嗯! 一定是你點錯了";
            btn_zhifubao.setText("支付寶");
            btnCheckVip.setText("去除廣告");
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
        TextView title = findViewById(R.id.title);
        if(!is_simplified_chinese){
            title.setText(ChineseUtils.toTraditional("我的世界合成表大全"));
        }

        ImageView imageViewMenu = (ImageView) findViewById(R.id.imageViewToolbar_menu);
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
