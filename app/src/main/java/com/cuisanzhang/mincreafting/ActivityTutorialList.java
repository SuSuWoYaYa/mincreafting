package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityTutorialList extends AppCompatActivity {




    private String[] TutorialNames = TutorialList.mc163_names;
    private String[] TutorialFiles = TutorialList.mc163_files;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tutorial_list_layout);

         ListView listView;
         MyAdapter adapter;

        listView= (ListView) findViewById(R.id.listView);
         adapter = new MyAdapter(getApplicationContext());
        Log.e("ActivityTutorialL", "getApplicationContext=" + getApplicationContext());
        Log.e("ActivityTutorialL", "TutorialList.mc163_names.length=" + TutorialList.mc163_names.length);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityTutorial.class);
                intent.putExtra(ActivityTutorial.EXTRA_URI, TutorialFiles[position]);
                startActivity(intent);
            }
        });

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
            return TutorialNames.length;
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


            holder.textView.setText(TutorialNames[position]);
            return convertView;
        }
    }

}
