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
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentMainActivity extends AppCompatActivity {

//    id
//    ca-app-pub-1353370194949670~3914579262
//    banner
//    ca-app-pub-1353370194949670/1876052366
//    kaiping
//    ca-app-pub-1353370194949670/4442505957
//    native
//    ca-app-pub-1353370194949670/3859179886
    //7320    "C5EF7D96DFF2F2C3E5CD1CA16D57D71F"

    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    public static String EXTRA_TABLE_NAME = "table_name";
    public static String EXTRA_CATEGORY = "category";

    private static String SAVE_FILE = "save.txt";
    private static String  DB_VERSION = "db_version";

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
        setContentView(R.layout.fragment_main_layout);
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);

        //设置选中和缓存
        int pageCount = pagerAdapter.getPageCount();
        viewPager.setOffscreenPageLimit(pageCount);
        viewPager.setCurrentItem(0);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);




        initActionBar();



        preferences = getSharedPreferences(SAVE_FILE,
                Context.MODE_APPEND);



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;

                switch (item.getItemId()) {
                    case R.id.menu_changeTheme:
                        initPopupWindow();
                        break;
                    case R.id.menu_feedback:
                        intent = new Intent(getApplicationContext(), ActivityWebViewFeedback.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_tip:
                        intent = new Intent(getApplicationContext(), ActivityTip.class);
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


        int  db_version = preferences.getInt(DB_VERSION, 0);
        //System.out.println("db_version = " + db_version);
        if (db_version != MyDatabaseHelper.DB_VERSION) {
            initDatabase();
        }
    }
    public void initActionBar() {
        ImageView imageViewMenu = (ImageView) findViewById(R.id.imageViewToolbar_menu);
        ImageView imageViewSaerch = (ImageView) findViewById(R.id.imageViewToolbar_search);
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        imageViewSaerch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentMainActivity.this, ActivitySearch.class);
                startActivity(intent );
            }
        });
    }

    //更新主题用
    public static void ReStartActivity(Activity activity) {


        Intent intent = activity.getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.finish();
  //      Toast.makeText(FragmentMainActivity.this, "ReStartActivity",Toast.LENGTH_SHORT).show;
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
                ReStartActivity(FragmentMainActivity.this);

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
        builder.setMessage("这是一个Minecraft合成表的APP\n所有内容来自于Minecraft 中文WIKI");
        builder.setPositiveButton("确定", null);
        builder.show();
    }

    //第一次启动插入数据库
    private void initDatabase() {
        initProgressDialog();

        //  实现handleMessage()方法，用于接收Message，刷新UI
        mHandler = new FragmentMainActivity.MyHandler();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

//                System.out.println("Start dbManage.insertDataToTable");
                DbManage dbManage = new DbManage(FragmentMainActivity.this);

                for (int i = 0; i < MyDatabaseHelper.jsons.length; i++) {
                    Message message = mHandler.obtainMessage();
                    message.what = 0;
                    message.obj = i;
                    mHandler.sendMessage(message);
                    dbManage.insertBlocksToTable(MyDatabaseHelper.TABLE_NAMES[i],
                            MyDatabaseHelper.jsons[i]);
//                    System.out.println("Start dbManage.insertDataToTable " + MyDatabaseHelper.TABLE_NAMES[i]);

                }

                //发送完成消息
                Message message = mHandler.obtainMessage();
                message.what = 1;
                mHandler.sendMessage(message);


                ////附魔取消单独一个表
////                System.out.println("Start dbManage.insertDataToTable" + MyDatabaseHelper.TABLE_ENCHANT);
//
//                dbManage.insertEnchantsToTable(MyDatabaseHelper.TABLE_ENCHANT, DbManage.jsonEnchant);

                dbManage.closeDatabase();
            }
        };
        timer.schedule(timerTask,0);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(DB_VERSION, MyDatabaseHelper.DB_VERSION);
        editor.commit();
        editor.apply();
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(FragmentMainActivity.this);
        progressDialog.setMax(MyDatabaseHelper.DATA_BASE_CATEGORYS.length);
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
                    progressDialog.setMessage("正在初始化数据库\n" + MyDatabaseHelper.DATA_BASE_CATEGORYS[progress]);
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