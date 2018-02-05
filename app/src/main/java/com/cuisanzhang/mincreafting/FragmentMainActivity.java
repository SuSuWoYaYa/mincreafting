package com.cuisanzhang.mincreafting;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;
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
    private int mainBackgroup = SettingUtils.ChangeTheme.THEME_DEEPGRAY;
    private int titleBackgroup = SettingUtils.ChangeTheme.THEME_DEEPGRAY;
    private int theme = 0;
    private int selectColor = 0;
    private TextView textViewSelectColor = null;
    private boolean changeMainBackgroup = true;
    private boolean changeTitleBackgroup = true;

    private RadioGroup radioGroup;
    private RadioButton radio_btn_zh;
    private RadioButton radio_btn_zw;

    private String language;
    private boolean is_language_of_traditional_chinese = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
        setTheme(theme);

        language = LanguageUtil.getLocaleLanguage(getApplicationContext());
        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
            is_language_of_traditional_chinese = true;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_layout);
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);

        //设置选中和缓存
        int pageCount = pagerAdapter.getPageCount();
//        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(1);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);


        initActionBar();


        preferences = getSharedPreferences(SAVE_FILE,
                Context.MODE_APPEND);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        Menu menuNav = mNavigationView.getMenu();
        MenuItem menu_changeTheme = menuNav.findItem(R.id.menu_changeTheme);
        MenuItem menu_feedback = menuNav.findItem(R.id.menu_feedback);
        MenuItem menu_tip = menuNav.findItem(R.id.menu_tip);
        MenuItem menu_about = menuNav.findItem(R.id.menu_about);
        MenuItem menu_changelanguage = menuNav.findItem(R.id.menu_changelanguage);
        MenuItem menu_downgame = menuNav.findItem(R.id.menu_downgame);

        if (is_language_of_traditional_chinese) {
            menu_changeTheme.setTitle("切換主題");
            menu_feedback.setTitle("意見反饋");
            menu_tip.setTitle("打賞作者");
            menu_about.setTitle("關於");
            menu_changelanguage.setTitle("切換語言");
            menu_downgame.setTitle("遊戲下載");

        }else {
            menu_changeTheme.setTitle("切换主题");
            menu_feedback.setTitle("意见反馈");
            menu_tip.setTitle("打赏作者");
            menu_about.setTitle("关于");
            menu_changelanguage.setTitle("切换语言");
            menu_downgame.setTitle("游戏下载");
        }

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
                    case R.id.menu_changelanguage:
                        showChangeLanguageDialog();
                        break;
                    case R.id.menu_downgame:
                        intent = new Intent(getApplicationContext(), DownGameActivity.class);
                        startActivity(intent);
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
        textUserName.setText(SettingUtils.ChangeTheme.getUserName(FragmentMainActivity.this));


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
                        SettingUtils.ChangeTheme.setTheme(getApplicationContext(), theme);
                    }
                    if (changeTitleBackgroup) {
                        SettingUtils.ChangeTheme.setTitleColor(getApplicationContext(), selectColor);
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
        TextView textViewSkyBlue = (TextView) layout.findViewById(R.id.layoutPopChangeToSkyBlue);
        TextView textViewDeepBlue = (TextView) layout.findViewById(R.id.layoutPopChangeToDeepBlue);
        TextView textViewBlown = (TextView) layout.findViewById(R.id.layoutPopChangeToBlown);
        TextView textViewDeepSaddleBrown = (TextView) layout.findViewById(R.id.layoutPopChangeToSaddleBrown);
        TextView textViewHotPink = (TextView) layout.findViewById(R.id.layoutPopChangeToHotPink);
        TextView textViewPink = (TextView) layout.findViewById(R.id.layoutPopChangeToPink);

        TextView textViewDeepDark = (TextView) layout.findViewById(R.id.layoutPopChangeToDeepDrak);
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



        if (is_language_of_traditional_chinese) {
            checkBoxChangeMainColor.setText("改變主標題欄背景顏色");
            checkBoxChangeTitleColor.setText("改變物品名稱背景顏色");
            button_changeColor.setText("確定");

            textViewGreen.setText("綠色");
            textViewDeepDrakGreen.setText("深綠");
            textViewBlue.setText("淺藍");
            textViewSkyBlue.setText("深藍");
            textViewDeepBlue.setText("藍色");
            textViewBlown.setText("棕色");
            textViewDeepSaddleBrown.setText("褐色");
            textViewHotPink.setText("粉色");
            textViewPink.setText("粉紅");

            textViewDeepDark.setText("深黑");
            textViewDeepGray.setText("深灰");
            textViewGray.setText("灰色");
            textViewLightGray.setText("亮灰");
            textViewOrangeRed.setText("紅色");
            textViewOrange.setText("橙色");
//        textViewGold = (TextView) layout.findViewById(R.id.layoutPopChangeToGold);
//        textViewYellow = (TextView) layout.findViewById(R.id.layoutPopChangeToBlueYellow);
            textViewBluePurple.setText("紫藍");
            textViewPurple.setText("紫色");
            textViewRed.setText("深紅");
        }else{
            checkBoxChangeMainColor.setText("改变主标题栏背景颜色");
            checkBoxChangeTitleColor.setText("改变物品名称背景颜色");
            button_changeColor.setText("确定");

            textViewGreen.setText("绿色");
            textViewDeepDrakGreen.setText("深绿");
            textViewBlue.setText("浅蓝");
            textViewSkyBlue.setText("深蓝");
            textViewDeepBlue.setText("蓝色");
            textViewBlown.setText("棕色");
            textViewDeepSaddleBrown.setText("褐色");
            textViewHotPink.setText("粉色");
            textViewPink.setText("粉红");

            textViewDeepDark.setText("深黑");
            textViewDeepGray.setText("深灰");
            textViewGray.setText("灰色");
            textViewLightGray.setText("亮灰");
            textViewOrangeRed.setText("红色");
            textViewOrange.setText("橙色");
//        textViewGold = (TextView) layout.findViewById(R.id.layoutPopChangeToGold);
//        textViewYellow = (TextView) layout.findViewById(R.id.layoutPopChangeToBlueYellow);
            textViewBluePurple.setText("紫蓝");
            textViewPurple.setText("紫色");
            textViewRed.setText("深红");
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (v.getId()) {

                    case R.id.layoutPopChangeToGreen:
                        theme = SettingUtils.ChangeTheme.THEME_GREEN;
                        selectColor = R.color.colorPrimary_green;
                        break;
                    case R.id.layoutPopChangeToDrakGreen:
                        theme = SettingUtils.ChangeTheme.THEME_DRAKGREEN;

                        selectColor = R.color.colorPrimary_darkgreen;
                        break;
                    case R.id.layoutPopChangeToBlue:
                        theme = SettingUtils.ChangeTheme.THEME_BLUE;
                        selectColor = R.color.colorPrimary_blue;
                        break;
                    case R.id.layoutPopChangeToSkyBlue:
                        theme = SettingUtils.ChangeTheme.THEME_SKY_BLUE;
                        selectColor = R.color.colorPrimary_sky_blue;
                        break;
                    case R.id.layoutPopChangeToDeepBlue:
                        theme = SettingUtils.ChangeTheme.THEME_DEEP_BLUE;

                        selectColor = R.color.colorPrimary_deep_blue;
                        break;
                    case R.id.layoutPopChangeToBlown:
                        theme = SettingUtils.ChangeTheme.THEME_BROWN;

                        selectColor = R.color.colorPrimary_brown;
                        break;
                    case R.id.layoutPopChangeToSaddleBrown:
                        theme = SettingUtils.ChangeTheme.THEME_SADDLEBROWN;

                        selectColor = R.color.colorPrimary_saddlebrown;
                        break;
                    case R.id.layoutPopChangeToHotPink:
                        theme = SettingUtils.ChangeTheme.THEME_HOTPINK;

                        selectColor = R.color.colorPrimary_hotpink;
                        break;
                    case R.id.layoutPopChangeToPink:
                        theme = SettingUtils.ChangeTheme.THEME_PINK;

                        selectColor = R.color.colorPrimary_pink;
                        break;

                    case R.id.layoutPopChangeToDeepDrak:
                        theme = SettingUtils.ChangeTheme.THEME_DEEP_DRAK;
                        selectColor = R.color.colorPrimary_deep_drak;
                        break;
                    case R.id.layoutPopChangeToDeepGray:
                        theme = SettingUtils.ChangeTheme.THEME_DEEPGRAY;
                        selectColor = R.color.colorPrimary_deep_gray;
                        break;
                    case R.id.layoutPopChangeToGray:
                        theme = SettingUtils.ChangeTheme.THEME_GRAY;

                        selectColor = R.color.colorPrimary_gray;
                        break;
                    case R.id.layoutPopChangeToLightGray:
                        theme = SettingUtils.ChangeTheme.THEME_LIGHTGRAY;

                        selectColor = R.color.colorPrimary_light_gray;
                        break;
                    case R.id.layoutPopChangeToOrangeRed:
                        theme = SettingUtils.ChangeTheme.THEME_ORANGE_RED;

                        selectColor = R.color.colorPrimary_orange_red;
                        break;
                    case R.id.layoutPopChangeToOrange:
                        theme = SettingUtils.ChangeTheme.THEME_ORANGE;

                        selectColor = R.color.colorPrimary_orange;
                        break;
//                    case R.id.layoutPopChangeToGold:
//                        theme = Utils.ChangeTheme.THEME_GOLD;
//                        break;
//                    case R.id.layoutPopChangeToBlueYellow:
//                        theme = Utils.ChangeTheme.THEME_YELLOW;
//                        break;
                    case R.id.layoutPopChangeToBluePurple:
                        theme = SettingUtils.ChangeTheme.THEME_BLUE_PURPLE;

                        selectColor = R.color.colorPrimary_blue_purple;
                        break;
                    case R.id.layoutPopChangeToPurple:
                        theme = SettingUtils.ChangeTheme.THEME_PURPLE;
                        selectColor = R.color.colorPrimary_purple;
                        break;
                    case R.id.layoutPopChangeToRed:
                        theme = SettingUtils.ChangeTheme.THEME_RED;
                        selectColor = R.color.colorPrimary_red;
                        break;
                    default:
                        theme = SettingUtils.ChangeTheme.THEME_DEEPGRAY;
                        selectColor = R.color.colorPrimary_deep_gray;
                }
                textViewSelectColor.setBackgroundColor(ContextCompat.getColor(FragmentMainActivity.this, selectColor));


            }
        };
        textViewGreen.setOnClickListener(onClickListener);
        textViewDeepDrakGreen.setOnClickListener(onClickListener);
        textViewBlue.setOnClickListener(onClickListener);
        textViewSkyBlue.setOnClickListener(onClickListener);
        textViewDeepBlue.setOnClickListener(onClickListener);
        textViewBlown.setOnClickListener(onClickListener);
        textViewDeepSaddleBrown.setOnClickListener(onClickListener);
        textViewHotPink.setOnClickListener(onClickListener);
        textViewPink.setOnClickListener(onClickListener);


        textViewDeepDark.setOnClickListener(onClickListener);
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

        if (is_language_of_traditional_chinese) {
            builder.setTitle("關於 Mincreafting");
            builder.setMessage("這是一個Minecraft合成表的APP\n所有內容來自於\nMinecraft中文WIKI\n網易\n網絡\n以及網友的貢獻");
            builder.setPositiveButton("確定", null);
        }else {
            builder.setTitle("关于 Mincreafting");
            builder.setMessage("这是一个Minecraft合成表的APP\n所有内容来自于\nMinecraft中文WIKI\n网易\n网络\n以及网友的贡献");
            builder.setPositiveButton("确定", null);
        }
            builder.show();
    }


    private boolean showSettingUserNameDialog() {
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View settingNameView = inflater.inflate(R.layout.layout_setting_name, null);
        editText_settingName = (EditText) settingNameView.findViewById(R.id.editText_settingName);


        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);

        builder.setView(settingNameView);
//        builder.setMessage("这是一个Minecraft合成表的APP\n所有内容来自于Minecraft 中文WIKI");
        if (is_language_of_traditional_chinese) {
            builder.setTitle("我的暱稱");
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    userName = editText_settingName.getText().toString();
                    SettingUtils.ChangeTheme.setUserName(FragmentMainActivity.this, userName);
                    textUserName.setText(userName);
                }
            });
        }
        else {
            builder.setTitle("我的昵称");
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    userName = editText_settingName.getText().toString();
                    SettingUtils.ChangeTheme.setUserName(FragmentMainActivity.this, userName);
                    textUserName.setText(userName);
                }
            });
        }
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
                    dbManage.insertBlocksToTable(MyDatabaseHelper.TABLE_NAMES_ZW[i],
                            MyDatabaseHelper.jsons_zw[i]);
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

        MyDatabaseHelper.initLanguageMessage(FragmentMainActivity.this);

        progressDialog = new ProgressDialog(FragmentMainActivity.this);
        progressDialog.setMax(MyDatabaseHelper.DATA_BASE_CATEGORYS.length);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle(getString(R.string.please_wait));
        progressDialog.setMessage("" +
                getString(R.string.init_datebase));
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

                    progressDialog.setMessage(getString(R.string.init_datebaseing) + MyDatabaseHelper.DATA_BASE_CATEGORYS[progress]);
                    break;
                case 1:
                    progressDialog.dismiss();
                    break;
                default:
                    progressDialog.setMessage(getString(R.string.unknow_error));
                    break;
            }
            super.handleMessage(msg);
        }
    }

//    private  void setLanguage() {
//        Resources resources = FragmentMainActivity.this.getResources();
//        DisplayMetrics dm = resources.getDisplayMetrics();
//        Configuration config = resources.getConfiguration();
//        String locale = LanguageUtil.getLocaleLanguage(FragmentMainActivity.this);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            config.setLocale(locale);
//        } else {
//            config.locale = locale;
//        }
//        resources.updateConfiguration(config, dm);
//    }

    private void showChangeLanguageDialog() {

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View changelanguageView = inflater.inflate(R.layout.changelanguage_layout, null);

        TextView changelanguage_messageTextView = (TextView) changelanguageView.findViewById(R.id.changelanguage_messageTextView);


        radioGroup = (RadioGroup) changelanguageView.findViewById(R.id.radiogroup_changelanguage);
//        radio_btn_zh = (RadioButton) changelanguageView.findViewById(R.id.radio_btn_zh);
//        radio_btn_zw = (RadioButton) changelanguageView.findViewById(R.id.radio_btn_zw);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.radio_btn_zh:
                        LanguageUtil.setLocaleLanguage(FragmentMainActivity.this, LanguageUtil.SIMPLIFIED_CHINESE);
                        break;
                    case R.id.radio_btn_zw:
                        LanguageUtil.setLocaleLanguage(FragmentMainActivity.this, LanguageUtil.TRADITIONAL_CHINESE);
                        break;
                    default:
                        LanguageUtil.setLocaleLanguage(FragmentMainActivity.this, LanguageUtil.SIMPLIFIED_CHINESE);
                        break;
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder.setView(changelanguageView);
        if (is_language_of_traditional_chinese) {
            builder.setTitle("切換語言");
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ReStartActivity(FragmentMainActivity.this);
                }
            });
            changelanguage_messageTextView.setText("改變語言設置會影響搜索結果");
        }else {
            builder.setTitle("切换语言");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ReStartActivity(FragmentMainActivity.this);
                }
            });
            changelanguage_messageTextView.setText("改变语言设置会影响搜索结果");
        }
        builder.show();
        return ;
    }
}