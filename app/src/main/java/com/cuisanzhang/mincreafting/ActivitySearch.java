package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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


import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.common.util.StringUtils;


public class ActivitySearch extends AppCompatActivity {


    private static final String TAG = "ActivitySearch";
    private static final String HISTORY = "history.txt";

    public static String EXTRA_ARRAY_LIST = "arrayList";

    private static SharedPreferences preferences = null;
    private List<String> historyList;

    private AutoCompleteTextView autoCompleteTextView;
    private ImageView imageViewMenu;
    private ImageView imageViewSaerch;
    private ImageView imageViewCleanText;
    private ListView listView;
    private MyAdapter listviewAdapter;

    private TextView textViewEmpty;
    private List<Block> searchResults;

    // checkbox状态
    List<Boolean> checkBoxStateList = null;
    List<String> Material = null;

    private InputMethodManager mInputMethodManager;

    //    for admob
//    private AdRequest adRequest;
    boolean isNetworkConnected = false;
    boolean isVip ;

    private String[] search;


    //for change title color
    int selectColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
        int color = SettingUtils.ChangeTheme.getTitleColor(getApplicationContext());
        setTheme(theme);
        selectColor = ContextCompat.getColor(ActivitySearch.this,color);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


//        adRequest = new AdRequest.Builder()
////                .addTestDevice(getString(R.string.my_test_device_id))
//                .build();

        Intent intent = getIntent();
        search = intent.getStringArrayExtra(EXTRA_ARRAY_LIST);


        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        imageViewMenu = (ImageView) findViewById(R.id.imageViewToolbar_menu);
        imageViewCleanText = (ImageView) findViewById(R.id.imageViewToolbar_cleanText);
        imageViewSaerch = (ImageView) findViewById(R.id.imageViewToolbar_search);
        listView = (ListView) findViewById(R.id.listView);



        isVip = SettingUtils.ChangeTheme.getVipState(ActivitySearch.this);

        isNetworkConnected = SettingUtils.isNetworkConnected(ActivitySearch.this);

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


        LinearLayout emptyView;
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

        Material = new ArrayList<String>();


        getHistory();

        initActionBar();

        setAdapter();

        if (search != null) {
            //有传搜索参数就关闭输入模式,不弹出输入法
            autoCompleteTextView.setInputType(InputType.TYPE_NULL);


            searchString(search);

        }


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
        for (int i = 1; i < historyList.size(); i++) {
            if (historyList.get(0).equals(historyList.get(i))) {
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

        //有点击输入框就打开输入模式,自动弹出输入法
        autoCompleteTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                autoCompleteTextView.setInputType(InputType.TYPE_CLASS_TEXT);
                return false;
            }
        });

        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override


            public void onFocusChange(View v, boolean hasFocus) {

                AutoCompleteTextView view = (AutoCompleteTextView) v;
//                view.setText("");
                //有传搜索参数就不弹出下拉框
                if (search == null && hasFocus) {
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
                return false;
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

                    search = new String[]{string};
//                    search.add(string);
                    searchString(search);

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
        imageViewCleanText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextView.setText("");
            }
        });
    }

    private void setAdapter() {
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, historyList);
        autoCompleteTextView.setAdapter(autoCompleteAdapter);
    }


    private void searchString(String[] search) {

        String text = "";
        for (int i = 0; i < search.length; i++) {
            text += search[i] + " ";
        }
//        Toast.makeText(ActivitySearch.this, "搜索 " + text, Toast.LENGTH_SHORT).show();

        text += "的搜索结果";
        autoCompleteTextView.setText(text);

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

        search = null;
    }


    private class MyAdapter extends BaseAdapter {

        private final class ViewHolder {
            private ImageView imageView;
            private String FileName;
            private TextView textViewName;
            private TextView textViewMaterial;
            private ImageView imageViewHideMore;
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

            //  防止内存回收后返回到这个界面崩溃
            if ((searchResults ==null) || (searchResults.size() <=0)){
                finish();
            }

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
                holder.imageViewHideMore = (ImageView) convertView.findViewById(R.id.imageViewHideMore);
                holder.checkBox = (CheckBox) convertView
                        .findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.mAdView = (AdView) convertView.findViewById(R.id.adView);

                holder.textViewName.setBackgroundColor(selectColor);

            } else {

                holder = (ViewHolder) convertView.getTag();

            }


//            if(!isVip){
//                holder.mAdView.setVisibility(View.GONE);
//            }
            holder.textViewName.setText(block.getName());
//            TextView textViewBuilding = (TextView) findViewById(R.id.textViewBuilding);

            holder.textViewMaterial.setText(block.getMaterial());
//            holder.textViewMaterial.setText(block.getMaterial() + block.getFileName());
//            boolean isgif = block.isgif();

//            int resId = getResources().getIdentifier(block.getFileName(), "drawable",
//                    getPackageName());
//            Glide.with(ActivityListViewShowBlocks.this).load(resId).placeholder(loading_of_background)
//                        .into(holder.imageView);

            if (Material.size() <= position) {
                // 保存每个合成原料, 用来分割查询
                Material.add(block.getMaterial());
            }

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = Material.get(pos);

                    str = SettingUtils.filterString(str);

                    String[] searchNames = str.split("\\s+");
//                    searchString(searchNames);

                    //没有原料的时候分割出来的数组为0大小
                    if(searchNames.length == 0 || searchNames[0].equals(""))
                        return;

                    Intent intent = new Intent(ActivitySearch.this, ActivitySearch.class);
                    intent.putExtra(ActivitySearch.EXTRA_ARRAY_LIST, searchNames);
                    startActivity(intent);
                }
            });

            //刷新的时候图片没变就不更新数据
            if (!holder.FileName.equals(filename)) {
                holder.FileName = filename;
                String path = "android.resource://com.cuisanzhang.mincreafting/drawable/" + filename;

                Glide.with(ActivitySearch.this)
                        .load(path).placeholder(R.drawable.loading_of_search)
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

            holder.imageViewHideMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 按照列表位置更新checkbox状态
                    checkBoxStateList.set(pos, false);
                    listviewAdapter.notifyDataSetChanged();
                }
            });

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
               holder.imageViewHideMore.setVisibility(View.VISIBLE);

                if(!isVip && isNetworkConnected){
                    holder.mAdView.setVisibility(View.VISIBLE);
                    holder.mAdView.loadAd(new AdRequest.Builder().build());
                }
            } else {
//				holder.textViewDetail.setVisibility(View.GONE);
                holder.textViewShowBlockDetail.setVisibility(View.GONE);
                holder.imageViewHideMore.setVisibility(View.GONE);
                holder.mAdView.setVisibility(View.GONE);
            }

            return convertView;
        }
    }
}
