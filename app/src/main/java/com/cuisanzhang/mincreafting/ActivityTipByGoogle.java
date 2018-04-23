package com.cuisanzhang.mincreafting;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cuisanzhang.mincreafting.util.IabBroadcastReceiver;
import com.cuisanzhang.mincreafting.util.IabHelper;
import com.cuisanzhang.mincreafting.util.IabResult;
import com.cuisanzhang.mincreafting.util.Inventory;
import com.cuisanzhang.mincreafting.util.Purchase;
import com.luhuiguo.chinese.ChineseUtils;

import java.util.ArrayList;
import java.util.List;

public class ActivityTipByGoogle extends AppCompatActivity {

    private static String TAG = "ActivityTipByGoogle";


    private String language;
    private boolean is_simplified_chinese = true;

    private static String SKU_099 = "buy_099";
    private static final int RC_REQUEST = 1207;
    private String buy99payload = "buy_pay_load";


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
        } else {
            is_simplified_chinese = false;
        }

        initActionBar();
        //base64
//    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnLTicW2d576FfBGy3ctQIiHMjsfVj17TqGbA7e6OCRhW1t75bO6RbDAQHiibRo08f7oxA2Spy4KJWJiOsN+GbJONKD1egaLSIbGBnXqnSaDZ0fCyEXEiQ1uyR5LE16Z/ZikOSve6xaO96PHzWnFV9zNuvnys8A+xdZ+i1u451kSM/7JV6hyPKjjXHPWhxdXBO4xz2CNVwmHEVLdK8rDPedcNFcBJ0ahKgVUxwzYOo2XRG2QBUkFQzKFzn7oaDFFRcwhzgr6p4DnMHBLTexnf1Ic0kgVLzRMtu6/NtErZN2TZjUvLQEx6OqLGGRNqmTwkdFxf2kZtpBFOskv0/4TyuQIDAQAB

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnLTicW2d576FfBGy3ctQIiHMjsfVj17TqGbA7e6OCRhW1t75bO6RbDAQHiibRo08f7oxA2Spy4KJWJiOsN";


        // Create the helper, passing it our context and the public key to verify signatures with
        Log.e(TAG, "Creating IAB helper.");
        mHelper = new IabHelper(this, base64EncodedPublicKey);

        // enable debug logging (for a production application, you should set this to false).
        mHelper.enableDebugLogging(true);

        // Start setup. This is asynchronous and the specified listener
        // will be called once setup completes.
        Log.e(TAG, "Starting setup.");

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                Log.e(TAG, "Setup finished.");

                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    complain("Setup 發生錯誤");

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
//                mBroadcastReceiver = new IabBroadcastReceiver(ActivityTipByGoogle.this);
//                IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
//                registerReceiver(mBroadcastReceiver, broadcastFilter);

                // IAB is fully set up. Now, let's get an inventory of stuff we own.
                Log.e(TAG, "Setup successful. Querying inventory.");

                //产品id列表
                List<String> productList = new ArrayList<String>();
                productList.add(SKU_099);

                try {
                    //检索产品详细信息
//                    mHelper.queryInventoryAsync(mGotInventoryListener);
                    mHelper.queryInventoryAsync(true, productList, mGotInventoryListener);

                } catch (Exception e) {
                    complain("查詢失敗");
                }
            }
        });

        if (SettingUtils.ChangeTheme.getVipState(ActivityTipByGoogle.this)) {
            updateUi();
        }

        Button btnBuy = findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHelper.launchPurchaseFlow(ActivityTipByGoogle.this, SKU_099, RC_REQUEST, mPurchaseFinishedListener, buy99payload + 1207 + "_" + "n" + "o" + "w");
            }
        });


    }


    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {

            Log.e(TAG, "Query inventory finished.");

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            // Is it a failure?
            if (result.isFailure()) {
                Log.e(TAG, "Failed to query inventory: " + result);
                return;
            }

            Log.e(TAG, "Query inventory was successful.");

            // Do we have the premium upgrade?
            if (!inventory.hasPurchase(SKU_099)) {
                Log.e(TAG, "Query inventory was don't hasPurchase.");
                return;
            }
            Purchase premiumPurchase = inventory.getPurchase(SKU_099);
            if (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase)) {
                Log.e(TAG, "Query inventory was verifyDeveloperPayload.");
//                Log.d(TAG, "We hasPurchase. Consuming it.");
//                mHelper.consumeAsync(inventory.getPurchase(SKU_099), mConsumeFinishedListener);

                SettingUtils.ChangeTheme.setVipState(ActivityTipByGoogle.this, true);
                alert("你已經升級為PRO版了");
                updateUi();
            }
//            mIsPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));
//            Log.e(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));
        }
    };

    // Callback for when a purchase is finished
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            Log.e(TAG, "Purchase finished: " + result + ", purchase: " + purchase);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            if (result.isFailure()) {
                complain("支付失敗: " + result);
//                setWaitScreen(false);
                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                complain("支付錯誤, 檢查失敗");
//                setWaitScreen(false);
                return;
            }

            Log.e(TAG, "Purchase successful.");

            if (purchase.getSku().equals(SKU_099)) {
                // bought 1/4 tank of gas. So consume it.
                Log.e(TAG, "Purchase is gas. Starting gas consumption.");
//                mHelper.consumeAsync(purchase, mConsumeFinishedListener);
            } else {
                return;
            }

            if (verifyDeveloperPayload(purchase)) {
                Log.e(TAG, "OnIabPurchaseFinishedListener was verifyDeveloperPayload.");

//                mHelper.consumeAsync(purchase, mConsumeFinishedListener);

                SettingUtils.ChangeTheme.setVipState(ActivityTipByGoogle.this, true);
                alert("你已經升級為PRO版了");
                updateUi();
            }
//            else if (purchase.getSku().equals(SKU_PREMIUM)) {
//                // bought the premium upgrade!
//                Log.e(TAG, "Purchase is premium upgrade. Congratulating user.");
//                alert("Thank you for upgrading to premium!");
//                mIsPremium = true;
//                updateUi();
//                setWaitScreen(false);
//            }
//            else if (purchase.getSku().equals(SKU_INFINITE_GAS)) {
//                // bought the infinite gas subscription
//                Log.e(TAG, "Infinite gas subscription purchased.");
//                alert("Thank you for subscribing to infinite gas!");
////                mSubscribedToInfiniteGas = true;
////                mTank = TANK_MAX;
////                updateUi();
////                setWaitScreen(false);
//            }
        }
    };


    // Called when consumption is complete
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            Log.d(TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            // We know this is the "gas" sku because it's the only one we consume,
            // so we don't check which sku was consumed. If you have more than one
            // sku, you probably should check...
            if (result.isSuccess()) {
                // successfully consumed, so we apply the effects of the item in our
                // game world's logic, which in our case means filling the gas tank a bit
                Log.e(TAG, "Consumption successful. Provisioning.");
//                complain("支付失敗: " + result);
//                mTank = mTank == TANK_MAX ? TANK_MAX : mTank + 1;
//                saveData();
//                alert("You filled 1/4 tank. Your tank is now " + String.valueOf(mTank) + "/4 full!");
            } else {
                Log.d(TAG, "發生錯誤: " + result);
            }

//            setWaitScreen(false);
            Log.e(TAG, "End consumption flow.");
        }
    };

//    @Override
//    public void receivedBroadcast() {
//        // Received a broadcast notification that the inventory of items has changed
//        Log.e(TAG, "Received broadcast notification. Querying inventory.");
//        try {
//            mHelper.queryInventoryAsync(mGotInventoryListener);
//        } catch (Exception e) {
//            complain("Error querying inventory. Another async operation in progress.");
//        }
//    }


    boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();

        if (payload.endsWith("load")) {
            Log.e(TAG, "payload IS " + payload);
            return false;
        }

        if (!payload.equals(buy99payload + 1207 + "_" + "n" + "o" + "w")) {
            Log.e(TAG, "payload IS " + payload);
            return false;
        }

        if (p.getSku().equals(SKU_099)) {
            // bought 1/4 tank of gas. So consume it.
            Log.e(TAG, "Purchase is gas. Starting gas consumption.");
//                mHelper.consumeAsync(purchase, mConsumeFinishedListener);
        } else {
            return false;
        }

        /*
         * TODO: verify that the developer payload of the purchase is correct. It will be
         * the same one that you sent when initiating the purchase.
         *
         * WARNING: Locally generating a random string when starting a purchase and
         * verifying it here might seem like a good approach, but this will fail in the
         * case where the user purchases an item on one device and then uses your app on
         * a different device, because on the other device you will not have access to the
         * random string you originally generated.
         *
         * So a good developer payload has these characteristics:
         *
         * 1. If two different users purchase an item, the payload is different between them,
         *    so that one user's purchase can't be replayed to another user.
         *
         * 2. The payload must be such that you can verify it even when the app wasn't the
         *    one who initiated the purchase flow (so that items purchased by the user on
         *    one device work on other devices owned by the user).
         *
         * Using your own server to store and verify developer payloads across app
         * installations is recommended.
         */

        Log.e(TAG, "PACKAGENAME IS " + getPackageName());

        if (!getPackageName().equals("com.cuisanzhang.mincreafting")) {
            Log.e(TAG, "getPackageName not equals.");
            return false;
        }


        //效应购买成功, 消费掉订单
        Log.d(TAG, "We hasPurchase. Consuming it.");
        mHelper.consumeAsync(p, mConsumeFinishedListener);

        Log.e(TAG, "verifyDeveloperPayload was true.");
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);
        if (mHelper == null) return;

        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd
            // perform any handling of activity results not related to in-app
            // billing...
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.e(TAG, "onActivityResult handled by IABUtil.");
        }
    }

    void updateUi() {
        TextView tipTextView = findViewById(R.id.textView2);
        tipTextView.setText("你已經升級為PRO了\n所有廣告都已去除\n\ncuisanzhang@163.com");
        Button btnbuy = findViewById(R.id.btn_buy);
        btnbuy.setText("繼續支持作者");
//        btnbuy.setClickable(false);
    }


    void complain(String message) {
        Log.e(TAG, "**** Mincreafting complain ****: " + message);
        alert("Error: " + message);
    }

    void alert(String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        Log.e(TAG, "Showing alert dialog: " + message);
        bld.create().show();
    }


    public void initActionBar() {
        TextView title = findViewById(R.id.title);
        if (!is_simplified_chinese) {
            title.setText(ChineseUtils.toTraditional("Minecraft合成表大全"));
        }

        ImageView imageViewMenu = (ImageView) findViewById(R.id.imageViewToolbar_menu);
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView otherpay = (ImageView) findViewById(R.id.other_pay);
        otherpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTipByGoogle.this, ActivityTip.class);
                startActivity(intent);
            }
        });

    }

    public void onDestroy() {
        super.onDestroy();

        // very important:
        Log.e(TAG, "Destroying helper.");
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }


}
