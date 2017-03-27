package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class ActivityListViewShowEnchants extends AppCompatActivity {

    public static String TAG = "getView";

    List<Enchant> enchants = null;
    // checkbox状态
    List<Boolean> checkBoxStateList = null;
    MyAdapter adapter = null;
    String table_name = null;
    int layout_of_item = 0;
//    int loading_of_background = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        int theme = ChangeTheme.getTheme(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview_of_blocks);
        initActionBar();

        checkBoxStateList = new ArrayList<Boolean>();

        DbManage dbManage = new DbManage(ActivityListViewShowEnchants.this);

        table_name = getIntent().getStringExtra(MainActivity.EXTRA_TABLE_NAME);
        String category = getIntent().getStringExtra(MainActivity.EXTRA_CATEGORY);
//
//        if (table_name.equals(MyDatabaseHelper.TABLE_BREWING)) {
//            layout_of_item = R.layout.layout_listview_item_enchants;
//            loading_of_background = R.drawable.loading_of_brewing;
//        }
//        else {
//            layout_of_item = R.layout.layout_listview_item_block;
//            loading_of_background = R.drawable.loading_of_blocks;
//        }
        TextView listViewTitle = (TextView) findViewById(R.id.listViewTitle);
        listViewTitle.setText(category);

        enchants = dbManage.getEnchantsFormTable(table_name);
        dbManage.closeDatabase();

        if (enchants == null) {
            System.out.println("getDatabase is null");
        } else {
            System.out.println("enchants.size=" + enchants.size());
        }

        ListView listView = (ListView) findViewById(R.id.listView1);
        adapter = new MyAdapter(getApplicationContext());
        listView.setAdapter(adapter);
//		if (enchants != null) {
//
//			 System.out.println("enchants.size()="+enchants.size());
//		} else {
//			// System.out.println("blocks == null");
//			//
//		}

    }

    public class MyAdapter extends BaseAdapter {

            public TextView textViewName;
            public TextView textViewHighLevel;
            public LinearLayout layoutMainView;
            public LinearLayout layoutSubView;
            public ImageView imageViewMain;
            public ImageView imageViewSub;
            public TextView textViewUse;
            public TextView textViewShowBlockDetail;
            public CheckBox checkBox;

//        public final class ViewHolder {
//            public TextView textViewName;
//            public TextView textViewHighLevel;
//            public LinearLayout layoutMainView;
//            public LinearLayout layoutSubView;
//            public ImageView imageViewMain;
//            public ImageView imageViewSub;
//            public TextView textViewUse;
//            public TextView textViewShowBlockDetail;
//            public CheckBox checkBox;
//        }

        private LayoutInflater mInflater = getLayoutInflater();
//        private ViewHolder holder = null;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return enchants.size();
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



                convertView = mInflater.inflate(R.layout.layout_listview_item_enchants, null);
                textViewName = (TextView) convertView
                        .findViewById(R.id.name);
                textViewHighLevel = (TextView) convertView
                        .findViewById(R.id.textViewHighLevel);
                imageViewMain = (ImageView) convertView
                        .findViewById(R.id.imageViewMain);
                imageViewSub = (ImageView) convertView
                        .findViewById(R.id.imageViewsub);
                layoutMainView = (LinearLayout)convertView.findViewById(R.id.layoutMainView);
                layoutSubView = (LinearLayout)convertView.findViewById(R.id.layoutSubView);
                textViewUse = (TextView) convertView
                        .findViewById(R.id.use);
                textViewShowBlockDetail = (TextView) convertView
                        .findViewById(R.id.textViewShowBlockDetail);
                checkBox = (CheckBox) convertView
                        .findViewById(R.id.checkBox1);
            Enchant enchant = enchants.get(position);
            textViewName.setText(enchant.getName());

            if (enchant.getHighLevel().equals(" ")) {
                textViewHighLevel.setVisibility(View.GONE);
            }else {
                textViewHighLevel.setVisibility(View.VISIBLE);
                textViewHighLevel.setText("最高等级: " + enchant.getHighLevel());
            }
//            holder.textViewHighLevel.setText("最高等级: " + enchant.getHighLevel() + " main=" + enchant.getMainFileName() + " sub=" + enchant.getSubFileName());


            if (enchant.getMainFileName().equals(" ")) {
                imageViewMain.setVisibility(View.GONE);
                layoutMainView.setVisibility(View.GONE);
            }else {
                imageViewMain.setVisibility(View.VISIBLE);
//                imageViewMain.setImageResource(R.drawable.loading_enchant);
                layoutMainView.setVisibility(View.VISIBLE);
                int MainFileResId = getResources().getIdentifier(enchant.getMainFileName(), "drawable",
                        getPackageName());
                Glide.with(ActivityListViewShowEnchants.this).load(MainFileResId).placeholder(R.drawable.loading_enchant)
                        .into(imageViewMain);
            }

            if (enchant.getSubFileName().equals(" ")) {
                imageViewSub.setVisibility(View.GONE);
                layoutSubView.setVisibility(View.GONE);
            }else {

                imageViewSub.setVisibility(View.VISIBLE);
//                imageViewSub.setImageResource(R.drawable.loading_enchant);
                layoutSubView.setVisibility(View.VISIBLE);
                int SubFileResId = getResources().getIdentifier(enchant.getSubFileName(), "drawable",
                        getPackageName());
                Glide.with(ActivityListViewShowEnchants.this).load(SubFileResId).placeholder(R.drawable.loading_enchant)
                        .into(imageViewSub);
            }

            textViewUse.setText(enchant.getUse());
            textViewShowBlockDetail.setText(enchant.getDetail());

            // 防止数组越界
            if (checkBoxStateList.size() <= position) {
                // 保存每个checkBoxState, 用来动态更新
                checkBoxStateList.add(false);
            }

            checkBox
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
            checkBox.setChecked(ischecked);
            if (ischecked) {
//				textViewDetail.setVisibility(View.VISIBLE);
                textViewShowBlockDetail.setVisibility(View.VISIBLE);
            } else {
//				textViewDetail.setVisibility(View.GONE);
                textViewShowBlockDetail.setVisibility(View.GONE);
            }

            return convertView;
        }

//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // TODO Auto-generated method stub
//            final int pos = position;
//
//            if (convertView == null) {
//
//                holder = new ViewHolder();
//
//
//                convertView = mInflater.inflate(R.layout.layout_listview_item_enchants, null);
//                holder.textViewName = (TextView) convertView
//                        .findViewById(R.id.name);
//                holder.textViewHighLevel = (TextView) convertView
//                        .findViewById(R.id.textViewHighLevel);
//                holder.imageViewMain = (ImageView) convertView
//                        .findViewById(R.id.imageViewMain);
//                holder.imageViewSub = (ImageView) convertView
//                        .findViewById(R.id.imageViewsub);
//                holder.layoutMainView = (LinearLayout)convertView.findViewById(R.id.layoutMainView);
//                holder.layoutSubView = (LinearLayout)convertView.findViewById(R.id.layoutSubView);
//                holder.textViewUse = (TextView) convertView
//                        .findViewById(R.id.use);
//                holder.textViewShowBlockDetail = (TextView) convertView
//                        .findViewById(R.id.textViewShowBlockDetail);
//                holder.checkBox = (CheckBox) convertView
//                        .findViewById(R.id.checkBox1);
//                convertView.setTag(holder);
//
//            } else {
//
//                holder = (ViewHolder) convertView.getTag();
//            }
//            Enchant enchant = enchants.get(position);
//            holder.textViewName.setText(enchant.getName());
//
//            if (enchant.getHighLevel().equals(" ")) {
//                holder.textViewHighLevel.setVisibility(View.GONE);
//            }else {
//                holder.textViewHighLevel.setVisibility(View.VISIBLE);
//                holder.textViewHighLevel.setText("最高等级: " + enchant.getHighLevel());
//            }
////            holder.textViewHighLevel.setText("最高等级: " + enchant.getHighLevel() + " main=" + enchant.getMainFileName() + " sub=" + enchant.getSubFileName());
//
//
//            if (enchant.getMainFileName().equals(" ")) {
//                holder.imageViewMain.setVisibility(View.GONE);
//                holder.layoutMainView.setVisibility(View.GONE);
//            }else {
//                holder.imageViewMain.setVisibility(View.VISIBLE);
//                holder.imageViewMain.setImageResource(R.drawable.loading_enchant);
//                holder.layoutMainView.setVisibility(View.VISIBLE);
//                int MainFileResId = getResources().getIdentifier(enchant.getMainFileName(), "drawable",
//                        getPackageName());
//                Glide.with(ActivityListViewShowEnchants.this).load(MainFileResId).placeholder(R.drawable.loading_enchant)
//                        .into(holder.imageViewMain);
//            }
//
//            if (enchant.getSubFileName().equals(" ")) {
//                holder.imageViewSub.setVisibility(View.GONE);
//                holder.layoutSubView.setVisibility(View.GONE);
//            }else {
//
//                holder.imageViewSub.setVisibility(View.VISIBLE);
//                holder.imageViewSub.setImageResource(R.drawable.loading_enchant);
//                holder.layoutSubView.setVisibility(View.VISIBLE);
//                int SubFileResId = getResources().getIdentifier(enchant.getSubFileName(), "drawable",
//                        getPackageName());
//                Glide.with(ActivityListViewShowEnchants.this).load(SubFileResId).placeholder(R.drawable.loading_enchant)
//                        .into(holder.imageViewSub);
//            }
//
//            holder.textViewUse.setText(enchant.getUse());
//            holder.textViewShowBlockDetail.setText(enchant.getDetail());
//
//            // 防止数组越界
//            if (checkBoxStateList.size() <= position) {
//                // 保存每个checkBoxState, 用来动态更新
//                checkBoxStateList.add(false);
//            }
//
//            holder.checkBox
//                    .setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//                        @Override
//                        public void onCheckedChanged(CompoundButton buttonView,
//                                                     boolean isChecked) {
//                            // TODO Auto-generated method stub
//                            // 按照列表位置更新checkbox状态
//                            checkBoxStateList.set(pos, isChecked);
//                            adapter.notifyDataSetChanged();
//                        }
//                    });
//
//            boolean ischecked = checkBoxStateList.get(position);
//            holder.checkBox.setChecked(ischecked);
//            if (ischecked) {
////				holder.textViewDetail.setVisibility(View.VISIBLE);
//                holder.textViewShowBlockDetail.setVisibility(View.VISIBLE);
//            } else {
////				holder.textViewDetail.setVisibility(View.GONE);
//                holder.textViewShowBlockDetail.setVisibility(View.GONE);
//            }
//
//            return convertView;
//        }
    }

    public void initActionBar() {
        ImageView imageViewMenu = (ImageView) findViewById(R.id.imageViewToolbar_menu);
//        ImageView imageViewSaerch = (ImageView) findViewById(R.id.imageViewToolbar_search);
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
