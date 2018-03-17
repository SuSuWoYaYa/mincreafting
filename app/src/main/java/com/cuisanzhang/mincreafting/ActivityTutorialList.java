package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActivityTutorialList extends AppCompatActivity {



    public static String EXTRA_TUTORIAL_IS_ONLINE = "isOnline";
    public static String EXTRA_TUTORIAL_NAMES = "tutorialNames";
    public static String EXTRA_TUTORIAL_FILES = "tutorialFiles";
    public static String EXTRA_TUTORIAL_CATEGARY = "tutorialCategary";

//    boolean isOnline = false;
    String[] tutorialNames;
    String[] tutorialFiles;
    String tutorialCategary;
    List<Tutorial> tutorials = new ArrayList<Tutorial>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tutorial_list_layout);
        initActionBar();

        Intent intent = getIntent();

//        isOnline =  intent.getBooleanExtra(EXTRA_TUTORIAL_IS_ONLINE,false);

//        if(!isOnline) {
            tutorialNames = intent.getStringArrayExtra(EXTRA_TUTORIAL_NAMES);
            tutorialFiles = intent.getStringArrayExtra(EXTRA_TUTORIAL_FILES);
            tutorialCategary = intent.getStringExtra(EXTRA_TUTORIAL_CATEGARY);
//        }else {
//            String TutorialString  = DownTutorialJson.DownTutorialJson(ActivityTutorialList.this);
//            tutorials = ReadJsonData.ReadTutorialsformJsonString(ActivityTutorialList.this, TutorialString);
//        }

        TextView textTitle = (TextView) findViewById(R.id.textTitle);
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
                    intent.putExtra(ActivityTutorialWebView.EXTRA_URI, tutorialFiles[position]);
                    intent.putExtra(ActivityTutorialWebView.EXTRA_TUTORIAL_NAME, tutorialNames[position]);
//                }else {
//                    intent.putExtra(ActivityTutorialWebView.EXTRA_URI, tutorials.get(position).getTutorial_url());
//                    intent.putExtra(ActivityTutorialWebView.EXTRA_TUTORIAL_NAME, tutorials.get(position).getTutorial_name());
//
//                }
                startActivity(intent);
            }
        });

        checkFirstTimeOpen();



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
                return tutorialNames.length;
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


//            if (isOnline) {
//                holder.textView.setText(position + 1 + " " + tutorials.get(position).getTutorial_name());
//            }
//            else {
                holder.textView.setText(position + 1 + " " + tutorialNames[position]);
//            }
            return convertView;
        }
    }

    public void initActionBar() {
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
           builder.setTitle("關於教程圖片");
           builder.setMessage("教程是離線的\n但是圖片是在線的\n請在Wifi下瀏覽\n\n默認開啓圖片緩存\n再次瀏覽不耗流量\n每個頁面初次加載需要一點時間緩存");
           builder.setPositiveButton("確定", null);
       }else {
           builder.setTitle("关于教程图片");
           builder.setMessage("教程是离线的\n但是图片是在线的\n请在Wifi下浏览\n\n默认开启图片缓存\n再次浏览不耗流量\n每个页面初次加载需要一点时间缓存");
           builder.setPositiveButton("确定", null);
       }

        builder.show();
    }

}
