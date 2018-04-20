package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

//import com.luhuiguo.chinese.ChineseUtils;

import com.luhuiguo.chinese.ChineseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ActivityTutorialList extends AppCompatActivity {



//    public static String EXTRA_TUTORIAL_IS_ONLINE = "isOnline";
//    public static String EXTRA_TUTORIAL_NAMES = "tutorialNames";
//    public static String EXTRA_TUTORIAL_FILES = "tutorialFiles";
    public static String EXTRA_TUTORIAL_CATEGARY = "tutorialCategary";
    public static String EXTRA_TUTORIAL_CODE = "tutorialCode";

    private boolean is_simplified_chinese = true;
    List<String> tutorialNames;
    List<String> tutorialFiles;
    String tutorialCategary;
    int  tutorialCode;

    List<Tutorial> tutorials = new ArrayList<Tutorial>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        int theme = SettingUtils.ChangeTheme.getTheme(ActivityTutorialList.this);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tutorial_list_layout);
        initActionBar();


        String language = LanguageUtil.getLocaleLanguage(ActivityTutorialList.this);
        if (language.equals(LanguageUtil.SIMPLIFIED_CHINESE)) {
            is_simplified_chinese = true;
        }else {
            is_simplified_chinese = false;
        }

        Intent intent = getIntent();

//        isOnline =  intent.getBooleanExtra(EXTRA_TUTORIAL_IS_ONLINE,false);

//        if(!isOnline) {
//            tutorialNames = intent.getStringArrayExtra(EXTRA_TUTORIAL_NAMES);
//            tutorialFiles = intent.getStringArrayExtra(EXTRA_TUTORIAL_FILES);
            tutorialCategary = intent.getStringExtra(EXTRA_TUTORIAL_CATEGARY);
            tutorialCode = intent.getIntExtra(EXTRA_TUTORIAL_CODE, 0);
            tutorialNames = TutorialListData.getTutorialNamesByCode(tutorialCode);
            tutorialFiles = TutorialListData.getTutorialFilesByCode(tutorialCode);
//        }else {
//            String TutorialString  = DownTutorialJson.DownTutorialJson(ActivityTutorialList.this);
//            tutorials = ReadJsonData.ReadTutorialsformJsonString(ActivityTutorialList.this, TutorialString);
//        }

        TextView textTitle = (TextView) findViewById(R.id.title);
        textTitle.setText(tutorialCategary);
         ListView listView;
         MyAdapter adapter;

        listView= (ListView) findViewById(R.id.listView);
         adapter = new MyAdapter(getApplicationContext());
//        Log.e("ActivityTutorialL", "getApplicationContext=" + getApplicationContext());
//        Log.e("ActivityTutorialL", "TutorialList.mc163_names.length=" + TutorialList.mc163_names.length);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityTutorialWebView.class);

//                if (!isOnline) {
                    intent.putExtra(ActivityTutorialWebView.EXTRA_URI, tutorialFiles.get(position));
                    intent.putExtra(ActivityTutorialWebView.EXTRA_TUTORIAL_NAME, tutorialNames.get(position));
//                }else {
//                    intent.putExtra(ActivityTutorialWebView.EXTRA_URI, tutorials.get(position).getTutorial_url());
//                    intent.putExtra(ActivityTutorialWebView.EXTRA_TUTORIAL_NAME, tutorials.get(position).getTutorial_name());
//
//                }
                startActivity(intent);
            }
        });

        checkFirstTimeOpen();

//        Log.e("ActivityTutorialList", "tutorialNames.size()=" + tutorialNames.size());
//        Log.e("ActivityTutorialList", "tutorialFiles.size()=" + tutorialFiles.size());

//        AssetManager assetManager   = getAssets();
//        Log.e("AssetManager", "AssetManager=" + assetManager);
//
//            assetManager.list();
//        try {
//            InputStream inputStream  = assetManager.open("chinese_utils/simp.txt");
//            Log.e("AssetManager", "inputStream=" + inputStream);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    private class MyAdapter extends BaseAdapter {

        private final class ViewHolder {
            private TextView textView;
        }


        private ViewHolder holder = null;
        private LayoutInflater mInflater = LayoutInflater.from(getApplicationContext());

        private MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {

//            if (isOnline) {
//                return tutorials.size();
//            }else {
                return tutorialNames.size();
//            }
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
            if (convertView == null) {
                holder = new MyAdapter.ViewHolder();

                convertView = mInflater.inflate(R.layout.fragment_tutorial_listview_of_item_layout, null);
                holder.textView = (TextView) convertView
                        .findViewById(R.id.textView);
                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();

            }


            if (is_simplified_chinese) {
                holder.textView.setText(position + 1 + " " + tutorialNames.get(position));
            }
            else {
                holder.textView.setText(position + 1 + " " + ChineseUtils.toTraditional(tutorialNames.get(position)));
            }
            return convertView;
        }
    }

    public void initActionBar() {
        TextView title = findViewById(R.id.title);
        if(!is_simplified_chinese){
            title.setText(ChineseUtils.toTraditional("我的世界合成表大全"));
        }

        ImageView imageViewMenu = (ImageView)findViewById(R.id.imageViewToolbar_menu);
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        Toolbar toolbar = (Toolbar) findViewById(R.id.webView_toolbar);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
    }

    private void checkFirstTimeOpen() {
        boolean isFirstTimeOpenTutorial = SettingUtils.isFirstTimeOpenTutorial(getApplicationContext());
        if(isFirstTimeOpenTutorial){
            showAboutImageDialog();
        }
    }

    private void showAboutImageDialog() {
//        Log.e("ActivityTutorialList", "getApplicationContext="+getApplicationContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityTutorialList.this, R.style.AlertDialog);

       if(LanguageUtil.getLocaleLanguage(ActivityTutorialList.this).equals(LanguageUtil.TRADITIONAL_CHINESE)){
//           builder.setTitle("關於教程圖片");
//           builder.setMessage("教程是離線的\n但是圖片是在線的\n請注意流量\n\n默認Wifi下開啓圖片緩存\n再次瀏覽不耗流量");
//           builder.setPositiveButton("確定", null);
           builder.setTitle(ChineseUtils.toTraditional("关于教程图片"));
           builder.setMessage(ChineseUtils.toTraditional("教程是离线的\n但是图片是在线的\n请注意流量\n\n默认Wifi下开启图片缓存\n再次浏览不耗流量"));
           builder.setPositiveButton(ChineseUtils.toTraditional("确定"), null);
       }else {
           builder.setTitle("关于教程图片");
           builder.setMessage("教程是离线的\n但是图片是在线的\n请注意流量\n\n默认Wifi下开启图片缓存\n再次浏览不耗流量");
           builder.setPositiveButton("确定", null);
       }

        builder.show();
    }

}
