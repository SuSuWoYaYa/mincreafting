package com.cuisanzhang.mincreafting;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {


    ArrayList<Map<String, Object>> data_list;

    public static String EXTRA_TABLE_NAME = "table_name";
    public static String EXTRA_CATEGORY = "category";

    private static String SAVE_FILE = "save.txt";
    private static String HASDADABASE = "hasDatabase";
    public static String DATA_BASE_CATEGORYS[] = {
            "建筑类",
            "日常类",
            "装饰类",
            "染料类",
            "食物类",
            "照明类",
            "矿石类",
            "红石类",
            "运输类",
            "工具类",
            "武器类",
            "其他类",
            "药水类",
            "附魔类",
    };

    public int MESSAGE_PROGRESS_CHANGE = 0;
    public int MESSAGE_PROGRESS_COMPLATE = 1;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private PopupWindow mPopupWindow;
    private ProgressDialog progressDialog;
    private Handler mHandler;

    private static SharedPreferences preferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = ChangeTheme.getTheme(getApplicationContext());
        setTheme(theme);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        initActionBar();


        preferences = getSharedPreferences(SAVE_FILE,
                Context.MODE_APPEND);

        boolean hasDatabase = preferences.getBoolean(HASDADABASE, false);
        System.out.println("hasDatabase = " + hasDatabase);
        if (hasDatabase == false) {
            initDatabase();
        }


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_changeTheme:
                        initPopupWindow();
                        break;

                    case R.id.menu_feedback:
                        Intent intent = new Intent(getApplicationContext(), ActivityWebViewFeedback.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_about:
                        showAboutDialog();
                        break;
                }

                mDrawerLayout.closeDrawers();

                return true;
            }
        });
        //显示他本身的颜色
        mNavigationView.setItemIconTintList(null);


        LinearLayout layout_btn_building = (LinearLayout) findViewById(R.id.layout_btn_building);
        LinearLayout layout_btn_daily = (LinearLayout) findViewById(R.id.layout_btn_daily);
        LinearLayout layout_btn_decoration = (LinearLayout) findViewById(R.id.layout_btn_decoration);
        LinearLayout layout_btn_dye = (LinearLayout) findViewById(R.id.layout_btn_dye);
        LinearLayout layout_btn_food = (LinearLayout) findViewById(R.id.layout_btn_food);
        LinearLayout layout_btn_lighting = (LinearLayout) findViewById(R.id.layout_btn_lighting);
        LinearLayout layout_btn_ore = (LinearLayout) findViewById(R.id.layout_btn_ore);
        LinearLayout layout_btn_redstone = (LinearLayout) findViewById(R.id.layout_btn_redstone);
        LinearLayout layout_btn_tannsport = (LinearLayout) findViewById(R.id.layout_btn_tannsport);
        LinearLayout layout_btn_tools = (LinearLayout) findViewById(R.id.layout_btn_tools);
        LinearLayout layout_btn_weapon = (LinearLayout) findViewById(R.id.layout_btn_weapon);
        LinearLayout layout_btn_others = (LinearLayout) findViewById(R.id.layout_btn_others);
        LinearLayout layout_btn_brewing = (LinearLayout) findViewById(R.id.layout_btn_brewing);
        LinearLayout layout_btn_enchant = (LinearLayout) findViewById(R.id.layout_btn_enchant);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityListViewShowBlocks.class);

                switch (v.getId()) {
                    case R.id.layout_btn_building:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_BUILDING);
                        intent.putExtra(EXTRA_CATEGORY, "建筑类");
                        break;
                    case R.id.layout_btn_daily:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_DAILY);
                        intent.putExtra(EXTRA_CATEGORY, "日常类");
                        break;
                    case R.id.layout_btn_decoration:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_DECORATION);
                        intent.putExtra(EXTRA_CATEGORY, "装饰类");
                        break;
                    case R.id.layout_btn_dye:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_DYE);
                        intent.putExtra(EXTRA_CATEGORY, "染料类");
                        break;
                    case R.id.layout_btn_food:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_FOOD);
                        intent.putExtra(EXTRA_CATEGORY, "食物类");
                        break;
                    case R.id.layout_btn_lighting:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_LIGHTING);
                        intent.putExtra(EXTRA_CATEGORY, "照明类");
                        break;
                    case R.id.layout_btn_ore:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_ORE);
                        intent.putExtra(EXTRA_CATEGORY, "矿石类");
                        break;
                    case R.id.layout_btn_redstone:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_REDSTONE);
                        intent.putExtra(EXTRA_CATEGORY, "红石和装置类");
                        break;
                    case R.id.layout_btn_tannsport:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_TANNSPORT);
                        intent.putExtra(EXTRA_CATEGORY, "运输类");
                        break;
                    case R.id.layout_btn_tools:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_TOOLS);
                        intent.putExtra(EXTRA_CATEGORY, "工具类");
                        break;
                    case R.id.layout_btn_weapon:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_WEAPON);
                        intent.putExtra(EXTRA_CATEGORY, "武器类");
                        break;
                    case R.id.layout_btn_others:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_OTHERS);
                        intent.putExtra(EXTRA_CATEGORY, "其他类");
                        break;
                    case R.id.layout_btn_brewing:
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_BREWING);
                        intent.putExtra(EXTRA_CATEGORY, "药水类");
                        break;
                    case R.id.layout_btn_enchant:
                        intent = new Intent(getApplicationContext(), ActivityListViewShowEnchants.class);
                        intent.putExtra(EXTRA_TABLE_NAME, MyDatabaseHelper.TABLE_ENCHANT);
                        intent.putExtra(EXTRA_CATEGORY, "附魔类");
                        break;
                    default:
                        intent = new Intent(getApplicationContext(), ActivityListViewShowBlocks.class);
                        break;
                }
                startActivity(intent);
            }
        };
        layout_btn_building.setOnClickListener(onClickListener);
        layout_btn_daily.setOnClickListener(onClickListener);
        layout_btn_decoration.setOnClickListener(onClickListener);
        layout_btn_dye.setOnClickListener(onClickListener);
        layout_btn_food.setOnClickListener(onClickListener);
        layout_btn_lighting.setOnClickListener(onClickListener);
        layout_btn_ore.setOnClickListener(onClickListener);
        layout_btn_redstone.setOnClickListener(onClickListener);
        layout_btn_tannsport.setOnClickListener(onClickListener);
        layout_btn_tools.setOnClickListener(onClickListener);
        layout_btn_weapon.setOnClickListener(onClickListener);
        layout_btn_others.setOnClickListener(onClickListener);
        layout_btn_brewing.setOnClickListener(onClickListener);
        layout_btn_enchant.setOnClickListener(onClickListener);


    }

    public void initActionBar() {
        ImageView imageViewMenu = (ImageView) findViewById(R.id.imageViewToolbar_menu);
//        ImageView imageViewSaerch = (ImageView) findViewById(R.id.imageViewToolbar_search);
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //更新主题用
    public static void ReStartActivity(Activity activity) {


        Intent intent = activity.getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.finish();
        activity.startActivity(intent);
    }


    protected void initPopupWindow() {

        if (mPopupWindow != null && mPopupWindow.isShowing())
            return;

        View layout = getLayoutInflater().inflate(R.layout.layout_pop_changetheme, null);
        //内容，高度，宽度
        mPopupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.PopupwindowStyle);

        //自动退出
        mPopupWindow.setBackgroundDrawable(new PaintDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);

        mPopupWindow.showAtLocation(findViewById(R.id.drawer_layout), Gravity.BOTTOM, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });


        LinearLayout layoutGray = (LinearLayout) layout.findViewById(R.id.layoutPopChangeToGray);
        LinearLayout layoutPink = (LinearLayout) layout.findViewById(R.id.layoutPopChangeToPink);
        LinearLayout layoutBlown = (LinearLayout) layout.findViewById(R.id.layoutPopChangeToBlown);
        LinearLayout layoutOrange = (LinearLayout) layout.findViewById(R.id.layoutPopChangeToOrange);
        LinearLayout layoutBlue = (LinearLayout) layout.findViewById(R.id.layoutPopChangeToBlue);
        LinearLayout layoutPurple = (LinearLayout) layout.findViewById(R.id.layoutPopChangeToPurple);
        LinearLayout layoutRed = (LinearLayout) layout.findViewById(R.id.layoutPopChangeToRed);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int theme;
                switch (v.getId()) {
                    case R.id.layoutPopChangeToGray:
                        theme = ChangeTheme.THEME_GRAY;
                        break;
                    case R.id.layoutPopChangeToPink:
                        theme = ChangeTheme.THEME_PINK;
                        break;
                    case R.id.layoutPopChangeToRed:
                        theme = ChangeTheme.THEME_RED;
                        break;
                    case R.id.layoutPopChangeToBlown:
                        theme = ChangeTheme.THEME_BROWN;
                        break;
                    case R.id.layoutPopChangeToBlue:
                        theme = ChangeTheme.THEME_BLUE;
                        break;
                    case R.id.layoutPopChangeToOrange:
                        theme = ChangeTheme.THEME_ORANGE;
                        break;
                    case R.id.layoutPopChangeToPurple:
                        theme = ChangeTheme.THEME_PURPLE;
                        break;
                    default:
                        theme = ChangeTheme.THEME_GRAY;
                }
                ChangeTheme.setTheme(getApplicationContext(), theme);
                mPopupWindow.dismiss();
                ReStartActivity(MainActivity.this);

            }
        };
        layoutGray.setOnClickListener(onClickListener);
        layoutPink.setOnClickListener(onClickListener);
        layoutRed.setOnClickListener(onClickListener);
        layoutBlue.setOnClickListener(onClickListener);
        layoutBlown.setOnClickListener(onClickListener);
        layoutOrange.setOnClickListener(onClickListener);
        layoutPurple.setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder.setTitle("关于 Mincreafting");
        builder.setMessage("这是一个Mincreaft合成表的APP\n所有内容来自于Mincratft 中文WIKI");
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    //第一次启动插入数据库
    private void initDatabase() {
        initProgressDialog();

        //  实现handleMessage()方法，用于接收Message，刷新UI
        mHandler = new MyHandler();

        new Runnable() {
            @Override
            public void run() {
//                System.out.println("Start dbManage.insertDataToTable");
                DbManage dbManage = new DbManage(MainActivity.this);

                for (int i = 0; i < DbManage.josns.length; i++) {
                    Message message = mHandler.obtainMessage();
                    message.what = 0;
                    message.obj = i;
                    mHandler.sendMessage(message);
                    dbManage.insertBlocksToTable(MyDatabaseHelper.TABLE_NAMES[i],
                            DbManage.josns[i]);
//                    System.out.println("Start dbManage.insertDataToTable " + MyDatabaseHelper.TABLE_NAMES[i]);

                }
                Message message = mHandler.obtainMessage();
                message.what = 1;
                mHandler.sendMessage(message);
//                System.out.println("Start dbManage.insertDataToTable" + MyDatabaseHelper.TABLE_ENCHANT);

                dbManage.insertEnchantsToTable(MyDatabaseHelper.TABLE_ENCHANT, DbManage.josnEnchant);

                dbManage.closeDatabase();
            }
        }.run();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(HASDADABASE, true);
        editor.apply();
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMax(14);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("请稍等");
        progressDialog.setMessage("" +
                "正在初始化数据库 ");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    int progress = (int) msg.obj;
//                        progressDialog.getProgress();
//                        progress +=1;
                    progressDialog.setProgress(progress);
                    progressDialog.setMessage("正在初始化数据库 " + DATA_BASE_CATEGORYS[progress]);
                    break;
                case 1:
                    progressDialog.dismiss();
                    break;
                default:
                    progressDialog.setMessage("出现未知错误");
                    break;
            }
            super.handleMessage(msg);
        }
    }

}

