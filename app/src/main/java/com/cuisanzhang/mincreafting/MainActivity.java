package com.cuisanzhang.mincreafting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        initActionBar();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                item.setChecked(true);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_menu_white_2x);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        toolbar.inflateMenu(R.menu.toolbar_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.menu_search) {
                    Toast.makeText(MainActivity.this, "menu1", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });
    }

}
