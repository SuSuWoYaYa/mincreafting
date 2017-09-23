package com.cuisanzhang.mincreafting;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentMainActivity extends AppCompatActivity {

//    id
//    ca-app-pub-1353370194949670~3914579262
//    banner
//    ca-app-pub-1353370194949670/1876052366
//    native
//    ca-app-pub-1353370194949670/3859179886
    //7320    "C5EF7D96DFF2F2C3E5CD1CA16D57D71F"

    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    public static String EXTRA_TABLE_NAME = "table_name";
    public static String EXTRA_CATEGORY = "category";

    private static String SAVE_FILE = "save.txt";
    private static String DB_VERSION = "db_version";

    public int MESSAGE_PROGRESS_CHANGE = 0;
    public int MESSAGE_PROGRESS_COMPLATE = 1;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private PopupWindow mPopupWindow;
    private ProgressDialog progressDialog;
    private Handler mHandler;


    private String userName;
    private EditText editText_settingName;
    private TextView textUserName;
    private static SharedPreferences preferences = null;


    //for change Theme
    private int mainBackgroup = Utils.ChangeTheme.THEME_DEEPGRAY;
    private int titleBackgroup = Utils.ChangeTheme.THEME_DEEPGRAY;
    private int theme = 0;
    private int selectColor = 0;
    private TextView textViewSelectColor = null;
    private boolean changeMainBackgroup = true;
    private boolean changeTitleBackgroup = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int theme = Utils.ChangeTheme.getTheme(getApplicationContext());
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


        View headerView = mNavigationView.getHeaderView(0);
        textUserName = (TextView) headerView.findViewById(R.id.textUserName);
        textUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSettingUserNameDialog();
//                    textUserName.setText(userName);
//
////                    if (mPopupWindow != null && mPopupWindow.isShowing())
////                        mPopupWindow.dismiss();
//
//                    return;
//                }
            }


        });
        textUserName.setText(Utils.ChangeTheme.getUserName(FragmentMainActivity.this));


        int db_version = preferences.getInt(DB_VERSION, 0);
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
                startActivity(intent);
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


        textViewSelectColor = (TextView) layout.findViewById(R.id.textViewSelectColor);

        CheckBox checkBoxChangeMainColor = (CheckBox) layout.findViewById(R.id.checkBoxChangeMainColor);
        checkBoxChangeMainColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    changeMainBackgroup = true;
                } else {
                    changeMainBackgroup = false;
                }
            }
        });

        CheckBox checkBoxChangeTitleColor = (CheckBox) layout.findViewById(R.id.checkBoxChangeTitleColor);
        checkBoxChangeTitleColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    changeTitleBackgroup = true;

                } else {
                    changeTitleBackgroup = false;
                }
            }
        });

        Button button_changeColor = (Button) layout.findViewById(R.id.button_changeColor);
        button_changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectColor != 0) {
                    if (changeMainBackgroup) {
                        Utils.ChangeTheme.setTheme(getApplicationContext(), theme);
                    }
                    if (changeTitleBackgroup) {
                        Utils.ChangeTheme.setTitleColor(getApplicationContext(), selectColor);
                    }
                }

//                if (changeMainBackgroup) {
                mPopupWindow.dismiss();
                ReStartActivity(FragmentMainActivity.this);
//                }

            }
        });


        TextView textViewGreen = (TextView) layout.findViewById(R.id.layoutPopChangeToGreen);
        TextView textViewDeepDrakGreen = (TextView) layout.findViewById(R.id.layoutPopChangeToDrakGreen);
        TextView textViewBlue = (TextView) layout.findViewById(R.id.layoutPopChangeToBlue);
        TextView textViewDeepBlue = (TextView) layout.findViewById(R.id.layoutPopChangeToDeepBlue);
        TextView textViewBlown = (TextView) layout.findViewById(R.id.layoutPopChangeToBlown);
        TextView textViewDeepSaddleBrown = (TextView) layout.findViewById(R.id.layoutPopChangeToSaddleBrown);
        TextView textViewHotPink = (TextView) layout.findViewById(R.id.layoutPopChangeToHotPink);
        TextView textViewPink = (TextView) layout.findViewById(R.id.layoutPopChangeToPink);

        TextView textViewDeepGray = (TextView) layout.findViewById(R.id.layoutPopChangeToDeepGray);
        TextView textViewGray = (TextView) layout.findViewById(R.id.layoutPopChangeToGray);
        TextView textViewLightGray = (TextView) layout.findViewById(R.id.layoutPopChangeToLightGray);
        TextView textViewOrangeRed = (TextView) layout.findViewById(R.id.layoutPopChangeToOrangeRed);
        TextView textViewOrange = (TextView) layout.findViewById(R.id.layoutPopChangeToOrange);
//        TextView textViewGold = (TextView) layout.findViewById(R.id.layoutPopChangeToGold);
//        TextView textViewYellow = (TextView) layout.findViewById(R.id.layoutPopChangeToBlueYellow);
        TextView textViewBluePurple = (TextView) layout.findViewById(R.id.layoutPopChangeToBluePurple);
        TextView textViewPurple = (TextView) layout.findViewById(R.id.layoutPopChangeToPurple);
        TextView textViewRed = (TextView) layout.findViewById(R.id.layoutPopChangeToRed);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (v.getId()) {

                    case R.id.layoutPopChangeToGreen:
                        theme = Utils.ChangeTheme.THEME_GREEN;
                        selectColor = R.color.colorPrimary_green;
                        break;
                    case R.id.layoutPopChangeToDrakGreen:
                        theme = Utils.ChangeTheme.THEME_DRAKGREEN;

                        selectColor = R.color.colorPrimary_darkgreen;
                        break;
                    case R.id.layoutPopChangeToBlue:
                        theme = Utils.ChangeTheme.THEME_BLUE;

                        selectColor = R.color.colorPrimary_blue;
                        break;
                    case R.id.layoutPopChangeToDeepBlue:
                        theme = Utils.ChangeTheme.THEME_DEEP_BLUE;

                        selectColor = R.color.colorPrimary_deep_blue;
                        break;
                    case R.id.layoutPopChangeToBlown:
                        theme = Utils.ChangeTheme.THEME_BROWN;

                        selectColor = R.color.colorPrimary_brown;
                        break;
                    case R.id.layoutPopChangeToSaddleBrown:
                        theme = Utils.ChangeTheme.THEME_SADDLEBROWN;

                        selectColor = R.color.colorPrimary_saddlebrown;
                        break;
                    case R.id.layoutPopChangeToHotPink:
                        theme = Utils.ChangeTheme.THEME_HOTPINK;

                        selectColor = R.color.colorPrimary_hotpink;
                        break;
                    case R.id.layoutPopChangeToPink:
                        theme = Utils.ChangeTheme.THEME_PINK;

                        selectColor = R.color.colorPrimary_pink;
                        break;

                    case R.id.layoutPopChangeToDeepGray:
                        theme = Utils.ChangeTheme.THEME_DEEPGRAY;

                        selectColor = R.color.colorPrimary_deep_gray;
                        break;
                    case R.id.layoutPopChangeToGray:
                        theme = Utils.ChangeTheme.THEME_GRAY;

                        selectColor = R.color.colorPrimary_gray;
                        break;
                    case R.id.layoutPopChangeToLightGray:
                        theme = Utils.ChangeTheme.THEME_LIGHTGRAY;

                        selectColor = R.color.colorPrimary_light_gray;
                        break;
                    case R.id.layoutPopChangeToOrangeRed:
                        theme = Utils.ChangeTheme.THEME_ORANGE_RED;

                        selectColor = R.color.colorPrimary_orange_red;
                        break;
                    case R.id.layoutPopChangeToOrange:
                        theme = Utils.ChangeTheme.THEME_ORANGE;

                        selectColor = R.color.colorPrimary_orange;
                        break;
//                    case R.id.layoutPopChangeToGold:
//                        theme = Utils.ChangeTheme.THEME_GOLD;
//                        break;
//                    case R.id.layoutPopChangeToBlueYellow:
//                        theme = Utils.ChangeTheme.THEME_YELLOW;
//                        break;
                    case R.id.layoutPopChangeToBluePurple:
                        theme = Utils.ChangeTheme.THEME_BLUE_PURPLE;

                        selectColor = R.color.colorPrimary_blue_purple;
                        break;
                    case R.id.layoutPopChangeToPurple:
                        theme = Utils.ChangeTheme.THEME_PURPLE;
                        selectColor = R.color.colorPrimary_purple;
                        break;
                    case R.id.layoutPopChangeToRed:
                        theme = Utils.ChangeTheme.THEME_RED;
                        selectColor = R.color.colorPrimary_red;
                        break;
                    default:
                        theme = Utils.ChangeTheme.THEME_DEEPGRAY;
                        selectColor = R.color.colorPrimary_deep_gray;
                }
                textViewSelectColor.setBackgroundColor(ContextCompat.getColor(FragmentMainActivity.this, selectColor));


            }
        };
        textViewGreen.setOnClickListener(onClickListener);
        textViewDeepDrakGreen.setOnClickListener(onClickListener);
        textViewBlue.setOnClickListener(onClickListener);
        textViewDeepBlue.setOnClickListener(onClickListener);
        textViewBlown.setOnClickListener(onClickListener);
        textViewDeepSaddleBrown.setOnClickListener(onClickListener);
        textViewHotPink.setOnClickListener(onClickListener);
        textViewPink.setOnClickListener(onClickListener);


        textViewDeepGray.setOnClickListener(onClickListener);
        textViewGray.setOnClickListener(onClickListener);
        textViewLightGray.setOnClickListener(onClickListener);
        textViewOrangeRed.setOnClickListener(onClickListener);
        textViewOrange.setOnClickListener(onClickListener);
//          textViewGold .setOnClickListener(onClickListener);
//          textViewYellow.setOnClickListener(onClickListener);
        textViewBluePurple.setOnClickListener(onClickListener);
        textViewPurple.setOnClickListener(onClickListener);
        textViewRed.setOnClickListener(onClickListener);
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


    private boolean showSettingUserNameDialog() {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View settingNameView = inflater.inflate(R.layout.layout_setting_name, null);
        editText_settingName = (EditText) settingNameView.findViewById(R.id.editText_settingName);


        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder.setTitle("请输入你的昵称");
        builder.setView(settingNameView);
//        builder.setMessage("这是一个Minecraft合成表的APP\n所有内容来自于Minecraft 中文WIKI");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userName = editText_settingName.getText().toString();
                Utils.ChangeTheme.setUserName(FragmentMainActivity.this, userName);
                textUserName.setText(userName);
            }
        });
        builder.show();
        return true;
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
        timer.schedule(timerTask, 0);

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