package com.cuisanzhang.mincreafting;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    ArrayList<Map<String, Object>> data_list;

    String name[] = {"常用物品", "武器", "常用物品", "武器", "常用物品", "武器", "红石", "生活用品", "建筑方块", "铁路",
            "装饰", "食物"};
    int icon[] = {
            R.drawable.diamond,
            R.drawable.diamond,
            R.drawable.diamond,
            R.drawable.diamond,
            R.drawable.diamond,
            R.drawable.diamond,
            R.drawable.diamond,
            R.drawable.diamond,
            R.drawable.diamond,
            R.drawable.diamond,
            R.drawable.diamond,
            R.drawable.diamond,};

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = ChangeTheme.getTheme(getApplicationContext());
        setTheme(theme);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        initActionBar();


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Toast.makeText(getApplicationContext(), "change theme", Toast.LENGTH_SHORT).show();
                switch (item.getItemId()) {
                    case R.id.menu_changeTheme:
                        initPopupWindow();
                        break;

                    case R.id.menu_feedback:
                        Intent intent = new Intent(getApplicationContext(), ActivityWebViewFeedback.class );
                        startActivity(intent);
                        break;
                    case R.id.menu_about:
                        showAboutDialog();
                        break;
                }

//                Toast.makeText(getApplicationContext(), "change theme", Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();

                return true;
            }
        });
        //显示他本身的颜色
        mNavigationView.setItemIconTintList(null);


        LinearLayout layoutBtn1 = (LinearLayout) findViewById(R.id.layout_btn_1);
        layoutBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ActivityListViewShowDetailDatas.class);
                startActivity(intent);
            }
        });


//            GridView gridView = (GridView) findViewById(R.id.gridview);
//            data_list = new ArrayList<Map<String, Object>>();
//
//            for (int i = 0; i < icon.length; i++) {
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("image", icon[i]);
//                map.put("text", name[i]);
//                data_list.add(map);
//            }
//            String[] from = { "image", "text" };
//
//            int[] to = { R.id.image, R.id.text };
//
//            SimpleAdapter adapter = new SimpleAdapter(this, data_list,
//                                                      R.layout.gridview_item, from, to);
//            gridView.setAdapter(adapter);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }


    public void initActionBar() {
        ImageView imageViewMenu = (ImageView)findViewById(R.id.imageViewToolbar_menu);
        ImageView imageViewSaerch = (ImageView)findViewById(R.id.imageViewToolbar_search);
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
//        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
////        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mDrawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//
//        toolbar.inflateMenu(R.menu.toolbar_menu);//设置右上角的填充菜单
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int menuItemId = item.getItemId();
//                if (menuItemId == R.id.menu_search) {
//                    Toast.makeText(MainActivity.this, "menu1", Toast.LENGTH_SHORT).show();
//
//                }
//                return true;
//            }
//        });
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int menuItemId = item.getItemId();
//        if (menuItemId == R.id.menu_search) {
//            Toast.makeText(MainActivity.this, "menu1", Toast.LENGTH_SHORT).show();
//
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public static void ReStartActivity(Activity activity) {


        Intent intent = activity.getIntent();
//        overridePendingTransition(0, 0);//不设置进入退出动画
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.finish();
//        overridePendingTransition(0, 0);
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


        TextView textViewGray = (TextView) layout.findViewById(R.id.textViewPopChangeToGray);
        TextView textViewPink = (TextView) layout.findViewById(R.id.textViewPopChangeToPink);
        TextView textViewBlown = (TextView) layout.findViewById(R.id.textViewPopChangeToBlown);
        TextView textViewOrange = (TextView) layout.findViewById(R.id.textViewPopChangeToOrange);
        TextView textViewBlue = (TextView) layout.findViewById(R.id.textViewPopChangeToBlue);
        TextView textViewPurple = (TextView) layout.findViewById(R.id.textViewPopChangeToPurple);
        TextView textViewRed = (TextView) layout.findViewById(R.id.textViewPopChangeToRed);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int theme;
                switch (v.getId()) {
                    case R.id.textViewPopChangeToGray:
                        theme = ChangeTheme.THEME_GRAY;
                        break;
                    case R.id.textViewPopChangeToPink:
                        theme = ChangeTheme.THEME_PINK;
                        break;
                    case R.id.textViewPopChangeToRed:
                        theme = ChangeTheme.THEME_RED;
                        break;
                    case R.id.textViewPopChangeToBlown:
                        theme = ChangeTheme.THEME_BROWN;
                        break;
                    case R.id.textViewPopChangeToBlue:
                        theme = ChangeTheme.THEME_BLUE;
                        break;
                    case R.id.textViewPopChangeToOrange:
                        theme = ChangeTheme.THEME_ORANGE;
                        break;
                    case R.id.textViewPopChangeToPurple:
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
        textViewGray.setOnClickListener(onClickListener);
        textViewPink.setOnClickListener(onClickListener);
        textViewRed.setOnClickListener(onClickListener);
        textViewBlue.setOnClickListener(onClickListener);
        textViewBlown.setOnClickListener(onClickListener);
        textViewOrange.setOnClickListener(onClickListener);
        textViewPurple.setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialog);
        builder.setTitle("关于 Mincreafting");
        builder.setMessage("这是一个Mincreaft合成表的APP, 所有内容来自于Mincratft 中文WIKI");
//        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", null);
        builder.show();
    }
}

