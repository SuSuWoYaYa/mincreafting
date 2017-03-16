package com.cuisanzhang.mincreafting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Map<String, Object>> data_list;
    
    String name[] = { "常用物品", "武器","常用物品", "武器", "常用物品", "武器", "红石", "生活用品", "建筑方块", "铁路",
    "装饰", "食物" };
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
    R.drawable.diamond, };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
///     setContentView(R.layout.activity_main);
        
         setContentView(R.layout.activity_main_gridview);


//        Intent intent = new Intent(getApplicationContext(), ActivityListViewShowDetailDatas.class);
//        startActivity(intent );

            GridView gridView = (GridView) findViewById(R.id.gridview);
            data_list = new ArrayList<Map<String, Object>>();
            
            for (int i = 0; i < icon.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("image", icon[i]);
                map.put("text", name[i]);
                data_list.add(map);
            }
            String[] from = { "image", "text" };
            
            int[] to = { R.id.image, R.id.text };
            
            SimpleAdapter adapter = new SimpleAdapter(this, data_list,
                                                      R.layout.gridview_item, from, to);
            gridView.setAdapter(adapter);
    }
}
