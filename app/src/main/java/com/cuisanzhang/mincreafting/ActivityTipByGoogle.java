package com.cuisanzhang.mincreafting;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuisanzhang.mincreafting.util.IabBroadcastReceiver;
import com.cuisanzhang.mincreafting.util.IabHelper;
import com.cuisanzhang.mincreafting.util.IabResult;
import com.cuisanzhang.mincreafting.util.Inventory;
import com.luhuiguo.chinese.ChineseUtils;

public class ActivityTipByGoogle extends AppCompatActivity implements IabBroadcastReceiver.IabBroadcastListener {

    private String TAG = "ActivityTipByGoogle";

    private String language;
    private boolean is_simplified_chinese  = true;


    // The helper object
    IabHelper mHelper;

    // Provides purchase notification while this app is running
    IabBroadcastReceiver mBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
//        int color = Utils.ChangeTheme.getTitleColor(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_by_google);


        language = LanguageUtil.getLocaleLanguage(ActivityTipByGoogle.this);
        if (language.equals(LanguageUtil.SIMPLIFIED_CHINESE)) {
            is_simplified_chinese = true;
        }else {
            is_simplified_chinese =false;
        }

        initActionBar();
        //base64
//    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnLTicW2d576FfBGy3ctQIiHMjsfVj17TqGbA7e6OCRhW1t75bO6RbDAQHiibRo08f7oxA2Spy4KJWJiOsN+GbJONKD1egaLSIbGBnXqnSaDZ0fCyEXEiQ1uyR5LE16Z/ZikOSve6xaO96PHzWnFV9zNuvnys8A+xdZ+i1u451kSM/7JV6hyPKjjXHPWhxdXBO4xz2CNVwmHEVLdK8rDPedcNFcBJ0ahKgVUxwzYOo2XRG2QBUkFQzKFzn7oaDFFRcwhzgr6p4DnMHBLTexnf1Ic0kgVLzRMtu6/NtErZN2TZjUvLQEx6OqLGGRNqmTwkdFxf2kZtpBFOskv0/4TyuQIDAQAB

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnLTicW2d576FfBGy3ctQIiHMjsfVj17TqGbA7e6OCRhW1t75bO6RbDAQHiibRo08f7oxA2Spy4KJWJiOsN";


        // Create the helper, passing it our context and the public key to verify signatures with
        Log.d(TAG, "Creating IAB helper.");
        mHelper = new IabHelper(this, base64EncodedPublicKey);

        // enable debug logging (for a production application, you should set this to false).
        mHelper.enableDebugLogging(true);

        // Start setup. This is asynchronous and the specified listener
        // will be called once setup completes.
        Log.d(TAG, "Starting setup.");

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                Log.d(TAG, "Setup finished.");

                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    complain("Problem setting up in-app billing: " + result);
                    return;
                }

                // Have we been disposed of in the meantime? If so, quit.
                if (mHelper == null) return;

                // Important: Dynamically register for broadcast messages about updated purchases.
                // We register the receiver here instead of as a <receiver> in the Manifest
                // because we always call getPurchases() at startup, so therefore we can ignore
                // any broadcasts sent while the app isn't running.
                // Note: registering this listener in an Activity is a bad idea, but is done here
                // because this is a SAMPLE. Regardless, the receiver must be registered after
                // IabHelper is setup, but before first call to getPurchases().
                mBroadcastReceiver = new IabBroadcastReceiver(ActivityTipByGoogle.this);
                IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
                registerReceiver(mBroadcastReceiver, broadcastFilter);

                // IAB is fully set up. Now, let's get an inventory of stuff we own.
                Log.d(TAG, "Setup successful. Querying inventory.");
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (Exception e) {
                    complain("Error querying inventory. Another async operation in progress.");
                }
            }
        });

    }

    void complain(String message) {
        Log.e(TAG, "**** TrivialDrive Error: " + message);
        alert("Error: " + message);
    }

    void alert(String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        Log.d(TAG, "Showing alert dialog: " + message);
        bld.create().show();
    }

    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult result, Inventory inv) {

            Log.d(TAG, "Query inventory finished.");

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            // Is it a failure?
            if (result.isFailure()) {
                complain("Failed to query inventory: " + result);
                return;
            }

            Log.d(TAG, "Query inventory was successful.");

        }
    };

    @Override
    public void receivedBroadcast() {
        // Received a broadcast notification that the inventory of items has changed
        Log.d(TAG, "Received broadcast notification. Querying inventory.");
        try {
            mHelper.queryInventoryAsync(mGotInventoryListener);
        } catch (Exception e) {
            complain("Error querying inventory. Another async operation in progress.");
        }
    }


    public void initActionBar() {
        TextView title = findViewById(R.id.title);
        if(!is_simplified_chinese){
            title.setText(ChineseUtils.toTraditional("Minecraft合成表大全"));
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
