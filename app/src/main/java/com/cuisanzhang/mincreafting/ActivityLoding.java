package com.cuisanzhang.mincreafting;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityLoding extends AppCompatActivity {

    private String TAG = "ActivityLoding";

    private int startLogos[] = {
//            R.drawable.start_logo0,
//            R.drawable.start_logo1,
//            R.drawable.start_logo2,
//            R.drawable.start_logo3,
            R.drawable.start_logo4,
            R.drawable.start_logo5,
            R.drawable.start_logo6,
//            R.drawable.start_logo7,
//            R.drawable.start_logo8
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);


        int background = new Random().nextInt(startLogos.length);
        ImageView imageViewLoading = (ImageView) findViewById(R.id.imageViewLoading);
        imageViewLoading.setImageResource(startLogos[background]);

        final long current = SystemClock.currentThreadTimeMillis();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(ActivityLoding.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        };
        timer.schedule(timerTask, 2000);
    }
}
