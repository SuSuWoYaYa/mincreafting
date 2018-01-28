package com.cuisanzhang.mincreafting;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DownGameActivity extends AppCompatActivity {

    String  mc163 = "https://mc.163.com/m/pe/index.html";
//String  mc163 = "https://mc.163.com/download/";
    String  googleplay = "https://play.google.com/store/apps/details?id=com.mojang.minecraftpe";
    String  other = "https://raw.githubusercontent.com/wiki/hesuxiang/mincreafting/wiki/apk/matou.html";


    Button btn_down_game_mc163;

    Button btn_down_game_googleplay ;

    LinearLayout linearlayout_other;
    Button btn_down_game_other;
    Button btn_other_version;


    Uri content_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
//        int color = Utils.ChangeTheme.getTitleColor(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.down_game_layout);
        initActionBar();

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

                        Intent intent = new Intent();
//Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        intent.setAction("android.intent.action.VIEW");
                        content_url = Uri.parse(other);
                        intent.setData(content_url);
                        startActivity(intent);
                    }
                }

        );
        btn_other_version = (Button) findViewById(R.id.btn_other_version);
        btn_other_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SettingUtils.ChangeTheme.getVipState(DownGameActivity.this) == true){
                    linearlayout_other.setVisibility(View.VISIBLE);
                    btn_other_version.setVisibility(View.GONE);
                }else {
                    Toast.makeText(DownGameActivity.this, "打赏作者后可以查看", Toast.LENGTH_SHORT).show();
                }
            }
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
