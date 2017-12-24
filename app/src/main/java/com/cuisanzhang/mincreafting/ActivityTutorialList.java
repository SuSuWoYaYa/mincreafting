package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.content.Intent;
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

public class ActivityTutorialList extends AppCompatActivity {



    public static String EXTRA_TUTORIAL_NAMES = "tutorialNames";
    public static String EXTRA_TUTORIAL_FILES = "tutorialFiles";
    public static String EXTRA_TUTORIAL_CATEGARY = "tutorialCategary";


    String[] tutorialNames;
    String[] tutorialFiles;
    String tutorialCategary;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        int theme = SettingUtils.ChangeTheme.getTheme(getApplicationContext());
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tutorial_list_layout);
        initActionBar();

        Intent intent = getIntent();

        tutorialNames = intent.getStringArrayExtra(EXTRA_TUTORIAL_NAMES);
        tutorialFiles = intent.getStringArrayExtra(EXTRA_TUTORIAL_FILES);
        tutorialCategary = intent.getStringExtra(EXTRA_TUTORIAL_CATEGARY);

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
                Intent intent = new Intent(getApplicationContext(), ActivityTutorial.class);
                intent.putExtra(ActivityTutorial.EXTRA_URI, tutorialFiles[position]);
                intent.putExtra(ActivityTutorial.EXTRA_TUTORIAL_NAME, tutorialNames[position]);
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
            return tutorialNames.length;
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


            holder.textView.setText(tutorialNames[position]);
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
        boolean isFirstTimeOpen = SettingUtils.isFirstTimeOpen(getApplicationContext());
        if(isFirstTimeOpen){
            showAboutImageDialog();
        }
    }

    private void showAboutImageDialog() {
//        Log.e("ActivityTutorialList", "getApplicationContext="+getApplicationContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityTutorialList.this, R.style.AlertDialog);
        builder.setTitle("关于教程图片");
        builder.setMessage("教程是离线的\n但是图片是在线的\n请注意流量\n所有的教程图片超过了500M");
        builder.setPositiveButton("确定", null);
        builder.show();
    }

}
