package com.cuisanzhang.mincreafting;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

public class ActivityListViewShowBlocks extends AppCompatActivity {

    public static String TAG = "getView";

    public static String EXTRA_TABLE_NAME = "table_name";
    public static String EXTRA_CATEGORY = "category";
    public static String EXTRA_LOADING = "loading";
    public static String EXTRA_IS_CREATING = "isCreating";
//    public static String EXTRA_LAYLOUT = "layout";

    List<Block> blocks = null;
    // checkbox状态
    List<Boolean> checkBoxStateList = null;
    MyAdapter adapter = null;
    List<String> Material = null;
    String table_name = null;
    String category = null;
    boolean isCreating = false;
    List<String> searchList;
    //    int layout_of_giveView = 0;
    int loading_of_background = 0;

    //    for admob
//    private AdRequest adRequest;
    boolean isNetworkConnected = false;
    boolean isVip;

    //for change title color
    int selectColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
        int color = SettingUtils.ChangeTheme.getTitleColor(getApplicationContext());
        setTheme(theme);
        selectColor = ContextCompat.getColor(ActivityListViewShowBlocks.this, color);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview_of_blocks);
        initActionBar();

        checkBoxStateList = new ArrayList<Boolean>();
        Material = new ArrayList<String>();
        searchList = new ArrayList<String>();

        DbManage dbManage = new DbManage(getApplicationContext());

        //按传递的数据加载数据库,标题,背景,布局
        //Toast.makeText(ActivityListViewShowBlocks.this, "", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        table_name = intent.getStringExtra(EXTRA_TABLE_NAME);
        category = intent.getStringExtra(EXTRA_CATEGORY);
        loading_of_background = intent.getIntExtra(EXTRA_LOADING, R.drawable.loading_of_blocks);
        isCreating = intent.getBooleanExtra(EXTRA_IS_CREATING, false);
//        layout_of_giveView = intent.getIntExtra(EXTRA_LAYLOUT, R.layout.layout_listview_item_block);

        //为了适应药水,烧炼和附魔的图片
//        if (table_name.equals(MyDatabaseHelper.TABLE_BREWING)) {
//            layout_of_giveView = R.layout.layout_listview_item_brewing;
//            loading_of_background = R.drawable.loading_of_brewing;
//        } else if (table_name.equals(MyDatabaseHelper.TABLE_SMELTING)) {
//            layout_of_giveView = R.layout.layout_listview_item_smelting;
//            loading_of_background = R.drawable.loading_of_smelting;
//        } else if (table_name.equals(MyDatabaseHelper.TABLE_ENCHANT)) {
//            layout_of_giveView = R.layout.layout_listview_item_block;
//            loading_of_background = R.drawable.loading_of_enchant;
//        }else {
//            layout_of_giveView = R.layout.layout_listview_item_block;
//            loading_of_background = R.drawable.loading_of_blocks;
//        }
        TextView listViewTitle = (TextView) findViewById(R.id.listViewTitle);
        listViewTitle.setText(category);

        blocks = dbManage.getBlocksFormTable(table_name);
        dbManage.closeDatabase();


        ListView listView = (ListView) findViewById(R.id.listView1);

        isVip = SettingUtils.ChangeTheme.getVipState(ActivityListViewShowBlocks.this);

        isNetworkConnected = SettingUtils.isNetworkConnected(ActivityListViewShowBlocks.this);

        if (!isVip) {
//        google admob
            MobileAds.initialize(this, getString(R.string.admob_uni_id));

            //add admob in listView
            LinearLayout tipEndViw = (LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.admob_native_layout, null);
            NativeExpressAdView nativeExpressAdView = (NativeExpressAdView) tipEndViw.findViewById(R.id.nativeExpressAdView);


            if (!isNetworkConnected) {
                nativeExpressAdView.setVisibility(View.GONE);
            } else {
                nativeExpressAdView.loadAd(new AdRequest.Builder().build());
            }
            listView.addFooterView(tipEndViw);
        }

        adapter = new MyAdapter(getApplicationContext());


        listView.setAdapter(adapter);
//		if (blocks != null) {
//
//			 System.out.println("blocks.size()="+blocks.size());
//		} else {
//			// System.out.println("blocks == null");
//			//
//		}

    }

    private class MyAdapter extends BaseAdapter {

        private final class ViewHolder {
            private ImageView imageView;
            private String FileName;
            private TextView textViewName;
            private TextView textViewMaterial;
            //            private ImageView imageViewHideMore;
            private TextView textViewUse;

            private TextView textViewShowBlockDetail;
            private CheckBox checkBox;
            private AdView mAdView;


        }

        private LayoutInflater mInflater = getLayoutInflater();
        private ViewHolder holder = null;

        private MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return blocks.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final int pos = position;

            //  防止内存回收后返回到这个界面崩溃
            if ((blocks ==null) || (blocks.size() <=0)){
                finish();
            }
            Block block = blocks.get(position);
            String filename = block.getFileName();


            if (convertView == null) {

                holder = new ViewHolder();


                convertView = mInflater.inflate(R.layout.layout_listview_item_block, null);

                holder.FileName = "";

                holder.textViewName = (TextView) convertView
                        .findViewById(R.id.name);
                holder.textViewMaterial = (TextView) convertView
                        .findViewById(R.id.material);
                holder.imageView = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                holder.textViewUse = (TextView) convertView
                        .findViewById(R.id.use);
                holder.textViewShowBlockDetail = (TextView) convertView
                        .findViewById(R.id.textViewShowBlockDetail);
//                holder.imageViewHideMore = (ImageView) convertView.findViewById(R.id.imageViewHideMore);
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);


                holder.mAdView = (AdView) convertView.findViewById(R.id.adView);


//                holder.textViewName.setBackgroundColor(getResources().getColor(R.color.colorPrimary_brown);
                holder.textViewName.setBackgroundColor(selectColor);

                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();

            }


            holder.textViewName.setText(block.getName());
//            TextView textViewBuilding = (TextView) findViewById(R.id.textViewBuilding);

            holder.textViewMaterial.setText(block.getMaterial());

//            holder.textViewMaterial.setText(block.getMaterial() + block.getFileName());
//            boolean isgif = block.isgif();

//            int resId = getResources().getIdentifier(block.getFileName(), "drawable",
//                    getPackageName());
//            Glide.with(ActivityListViewShowBlocks.this).load(resId).placeholder(loading_of_background)
//                        .into(holder.imageView);
//            if (holder.imageView == null) {
//                Log.e(TAG, "holder.imageView == nullholder.imageView == nullholder.imageView == nullholder.imageView == nullholder.imageView == null");
//            }

            if (Material.size() <= position) {
                // 保存每个合成原料, 用来分割查询
                Material.add(block.getMaterial());
            }

            if (isCreating) {

                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = Material.get(pos);

                        str = SettingUtils.filterString(str);

                        String[] searchNames = str.split("\\s+");
//

//                        Debug
//                        String temp = "'";
//                        for (String search : searchNames) {
//                            temp += search + "";
////                            System.out.println("searchNames=" +str);
//                        }
//                        temp += "'";
//                        Toast.makeText(ActivityListViewShowBlocks.this, "搜索" + str + "|" + searchNames.length + temp, Toast.LENGTH_SHORT).show();

                        //没有原料的时候分割出来的数组为0大小
                        if (searchNames.length == 0 || searchNames[0].equals(""))
                            return;

                        Intent intent = new Intent(ActivityListViewShowBlocks.this, ActivitySearch.class);
                        intent.putExtra(ActivitySearch.EXTRA_ARRAY_LIST, searchNames);
                        startActivity(intent);


                    }
//
                });
            }

            //刷新的时候图片没变就不更新数据
            if (!holder.FileName.equals(filename)) {
                holder.FileName = filename;
                String path = "android.resource://com.cuisanzhang.mincreafting/drawable/" + filename;
                Glide.with(ActivityListViewShowBlocks.this).load(path)
                        .placeholder(loading_of_background)
                        .into(holder.imageView);
            }

            holder.textViewUse.setText(block.getUse());
            holder.textViewShowBlockDetail.setText(block.getDetail());
            // 防止数组越界
            if (checkBoxStateList.size() <= position) {
                // 保存每个checkBoxState, 用来动态更新
                checkBoxStateList.add(false);
            }

//            holder.imageViewHideMore.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // 按照列表位置更新checkbox状态
//                    checkBoxStateList.set(pos, false);
//                    adapter.notifyDataSetChanged();
//
//
//                }
//            });

            holder.checkBox
                    .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            // TODO Auto-generated method stub
                            // 按照列表位置更新checkbox状态
                            checkBoxStateList.set(pos, isChecked);
                            adapter.notifyDataSetChanged();
                        }
                    });

            boolean ischecked = checkBoxStateList.get(position);
            holder.checkBox.setChecked(ischecked);

            if (ischecked) {
//				holder.textViewDetail.setVisibility(View.VISIBLE);
                holder.textViewShowBlockDetail.setVisibility(View.VISIBLE);
//                holder.imageViewHideMore.setVisibility(View.VISIBLE);
//                if(holder.mAdView != null){
                if (!isVip && isNetworkConnected) {
                    holder.mAdView.setVisibility(View.VISIBLE);
//                    if (holder.mAdView.)
                    holder.mAdView.loadAd(new AdRequest.Builder().build());
                }

//                }
//

            } else {
//				holder.textViewDetail.setVisibility(View.GONE);
                holder.textViewShowBlockDetail.setVisibility(View.GONE);
//                holder.imageViewHideMore.setVisibility(View.GONE);
//                if(holder.mAdView != null){
                holder.mAdView.setVisibility(View.GONE);
//                }

            }


            return convertView;
        }

    }

    public void initActionBar() {
        ImageView imageViewMenu = (ImageView) findViewById(R.id.imageViewToolbar_menu);
        ImageView imageViewSaerch = (ImageView) findViewById(R.id.imageViewToolbar_search);
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageViewSaerch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityListViewShowBlocks.this, ActivitySearch.class);
                startActivity(intent);
            }
        });


    }


}
