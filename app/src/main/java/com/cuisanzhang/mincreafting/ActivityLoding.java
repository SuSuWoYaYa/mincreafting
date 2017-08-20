package com.cuisanzhang.mincreafting;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityLoding extends AppCompatActivity {

    private String TAG = "ActivityLoding";

    private SoundPool mSoundPool = null;

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


    private int startSounds[] = {
            R.raw.zombie1,
            R.raw.zombie2,

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);

        //loading logo
        int position = new Random().nextInt(startLogos.length);
        ImageView imageViewLoading = (ImageView) findViewById(R.id.imageViewLoading);
        imageViewLoading.setImageResource(startLogos[position]);



        final long current = SystemClock.currentThreadTimeMillis();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(ActivityLoding.this, FragmentMainActivity.class);
                startActivity(intent);
                finish();
            }

        };
        timer.schedule(timerTask, 2000);


        //loading sound
        position = new Random().nextInt(startSounds.length);

        if(Build.VERSION.SDK_INT >= 21) {
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams(1);

            AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
            attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);

            builder.setAudioAttributes(attrBuilder.build());

            mSoundPool = builder.build();

        }else {
            mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        }

        int soundId = mSoundPool.load(ActivityLoding.this, startSounds[position], 1);

        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(sampleId,1, 1, 0, 0, 1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSoundPool.release();
    }
}
