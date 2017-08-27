package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.common.util.StringUtils;

public class ActivitySearch extends AppCompatActivity {



    private String TAG = "ActivitySearch";
    private String HISTORY = "history.txt";

    private static SharedPreferences preferences = null;
    private List<String> historyList;
    private ArrayAdapter<String> autoCompleteAdapter;
    private AutoCompleteTextView autoCompleteTextView;
    private ImageView imageViewMenu;
    private ImageView imageViewSaerch;
    private ListView listView;
    private MyAdapter listviewAdapter;
    private LinearLayout emptyView;
    private TextView textViewEmpty;
    private List<Block> searchResults;

    // checkbox状态
    List<Boolean> checkBoxStateList = null;

    private InputMethodManager mInputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        imageViewMenu = (ImageView) findViewById(R.id.imageViewToolbar_menu);
        imageViewSaerch = (ImageView) findViewById(R.id.imageViewToolbar_search);
        listView = (ListView) findViewById(R.id.listView);
        emptyView = (LinearLayout) findViewById(R.id.emptyView);
        listView.setEmptyView(emptyView);
//        listView.setDivider();
//        listView.setDividerHeight(2);


        textViewEmpty = (TextView) findViewById(R.id.textViewEmpty);



        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        preferences = getSharedPreferences(HISTORY,
                Context.MODE_PRIVATE);

        historyList = new ArrayList<String>();


        checkBoxStateList = new ArrayList<Boolean>();

        getHistory();

        initActionBar();


        setAdapter();

    }

    private void getHistory() {


        for (int i = 0; i < 30; i++) {
            String string = preferences.getString("history" + i, "nothing");
            if (string.equals("nothing")) {
//                Log.e(TAG, "preferences.getString  " + string);
                continue;

            }
            historyList.add(string);
//            Log.e(TAG, "preferences.getString  " + "history" + i + " " + string);
        }

    }


    private void saveHistory() {
        for(int i = 1; i< historyList.size(); i++){
            if (historyList.get(0).equals(historyList.get(i))){
                historyList.remove(i);
                break;
            }
        }

        SharedPreferences.Editor editor = preferences.edit();


        for (int i = 0; i < historyList.size(); i++) {
            editor.putString("history" + i, historyList.get(i));
//            Log.e(TAG, "editor.putString " + "history" + i + historyList.get(i));
        }
        editor.apply();

        return;
    }

    public void initActionBar() {
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                AutoCompleteTextView view = (AutoCompleteTextView) v;
                if (hasFocus) {
                    view.showDropDown();
                }
            }
        });

        //监听输入法完成按钮,模拟点击
        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                boolean isOK = true;
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
//                        Toast.makeText(ActivitySearch.this, "点击-->IME_ACTION_DONE", Toast.LENGTH_SHORT).show();
                        imageViewSaerch.performClick();
                        break;

                    default:
//                        isOK = false;
                        break;

                }
                return  false;
            }
        });

        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageViewSaerch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = autoCompleteTextView.getText().toString();
                if (!StringUtils.isBlank(string)) {
                    historyList.add(0, string);
                    searchString(string);
                    saveHistory();
                    setAdapter();
                }
                autoCompleteTextView.getText().clear();
//                autoCompleteTextView.setFocusable(false);
                if (mInputMethodManager.isActive()) {
                    mInputMethodManager.hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(), 0);// 隐藏输入法
                }
            }

        });
    }

    private void setAdapter() {
        autoCompleteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, historyList);
        autoCompleteTextView.setAdapter(autoCompleteAdapter);
    }


    private void searchString(String search) {

        DbManage dbManage = new DbManage(ActivitySearch.this);
        searchResults = dbManage.seachString(search);
//        Log.e(TAG, "searchString get result count for " + searchResults.size());
        if (searchResults != null && searchResults.size() > 0) {

            //搜索后清空详情状态
            checkBoxStateList = new ArrayList<Boolean>();

//            Log.e(TAG, "listView.setAdapter(listviewAdapter);");
            listviewAdapter = new MyAdapter(ActivitySearch.this);
            listView.setAdapter(listviewAdapter);
        } else {

//            Log.e(TAG, "listView.setAdapter(null); ");
            listView.setAdapter(null);
            textViewEmpty.setText("没有搜索到结果, 请试试其他关键字");
        }

        dbManage.closeDatabase();
    }

    private class MyAdapter extends BaseAdapter {

        public final class ViewHolder {
            public ImageView imageView;
            public String FileName;
            public TextView textViewName;
            public TextView textViewMaterial;
            public TextView textViewUse;

            public TextView textViewShowBlockDetail;
            public CheckBox checkBox;
        }

        private LayoutInflater mInflater = getLayoutInflater();
        private ViewHolder holder = null;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return searchResults.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//
//            if (convertView == null) {
//                holder = new ViewHolder();
//                convertView = mInflater.inflate(R.layout.activity_search_listview_item, null);
//
//                holder.FileName = "";
//                holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
//                holder.textViewName = (TextView) convertView.findViewById(R.id.textViewName);
//                holder.textViewCategory = (TextView) convertView.findViewById(R.id.textViewCategory);
//                holder.textViewMaterial = (TextView) convertView.findViewById(R.id.textViewMaterial);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//
//            Block item = searchResults.get(position);
//
//            //不再使用反射获得id,直接生成Uri
////            int resId = getResources().getIdentifier(item.getFileName(), "drawable",
////                    getPackageName());
////            holder.imageView.setImageURI(resId);
//
//            String filename = item.getFileName();
//            Uri uri = Uri.parse( "android.resource://com.cuisanzhang.mincreafting/drawable/" + filename);
////            holder.imageView.setImageURI(uri);
//            //刷新的时候图片没变就不更新数据
//            if (!holder.FileName.equals(filename)) {
//                holder.FileName = filename;
//                String path = "android.resource://com.cuisanzhang.mincreafting/drawable/" + filename;
//                Glide.with(ActivitySearch.this).load(uri).placeholder(R.drawable.loading_of_blocks)
//                        .into(holder.imageView);
//            }
//
//            holder.textViewName.setText(item.getName());
////            holder.textViewCategory.setText(item.getCategory());
//            holder.textViewMaterial.setText(item.getMaterial());
//            return convertView;

            // TODO Auto-generated method stub
            final int pos = position;

            Block block = searchResults.get(position);
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
                holder.checkBox = (CheckBox) convertView
                        .findViewById(R.id.checkBox1);
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

            //刷新的时候图片没变就不更新数据
            if (!holder.FileName.equals(filename)) {
                holder.FileName = filename;
                String path = "android.resource://com.cuisanzhang.mincreafting/drawable/" + filename;
                Glide.with(ActivitySearch.this).load(path).placeholder(R.drawable.loading_of_search)
                        .into(holder.imageView);
            }

            holder.textViewUse.setText(block.getUse());
            holder.textViewShowBlockDetail.setText(block.getDetail());
//            Log.e(TAG, block.getDetail());
            // 防止数组越界
            if (checkBoxStateList.size() <= position) {
                // 保存每个checkBoxState, 用来动态更新
                checkBoxStateList.add(false);
            }

            holder.checkBox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            // TODO Auto-generated method stub
                            // 按照列表位置更新checkbox状态
                            checkBoxStateList.set(pos, isChecked);
                            listviewAdapter.notifyDataSetChanged();
//                            Log.e(TAG, "" +pos +" "  +isChecked);
                        }
                    });

            boolean ischecked = checkBoxStateList.get(position);
            holder.checkBox.setChecked(ischecked);
            if (ischecked) {
////				holder.textViewDetail.setVisibility(View.VISIBLE);
                holder.textViewShowBlockDetail.setVisibility(View.VISIBLE);
            } else {
//				holder.textViewDetail.setVisibility(View.GONE);
                holder.textViewShowBlockDetail.setVisibility(View.GONE);
            }

            return convertView;
        }
    }
}
