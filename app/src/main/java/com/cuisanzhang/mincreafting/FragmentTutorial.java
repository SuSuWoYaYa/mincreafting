package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hesuxiang on 17/10/4.
 */

public class FragmentTutorial extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private List<Map<String, String>> data;


    public static FragmentTutorial newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentTutorial pageFragment = new FragmentTutorial();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

        data = new ArrayList<Map<String, String>>();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tutorial_layout, container, false);


        ListView listView = (ListView) view.findViewById(R.id.listView);
//        listView.setAdapter(new SimpleAdapter(getContext(),
//                getData(),
//                R.layout.fragment_listview_of_item_tutorial_layout,
//                new String[]{"name"},
//                new int[]{R.id.textView}
//        ));

 MyAdapter adapter = new MyAdapter(getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ActivityTutorial.class);
                intent.putExtra(ActivityTutorial.EXTRA_URI, TutorialList.TuorialFile[position]);
                startActivity(intent);
            }
        });
        return view;
    }

//    private List<Map<String, String>> getData() {
//
//        for (int i = 0; i < TutorialList.TuorialName.length; i++) {
//            Map<String, String> item = new HashMap<String, String>();
//            item.put("name", TutorialList.TuorialName[i]);
//
//            data.add(item);
//
//        }
//        return data;
//    }

    private class MyAdapter extends BaseAdapter {

        private final class ViewHolder {
            private TextView textView;
        }


        private ViewHolder holder = null;
        private LayoutInflater mInflater = getActivity().getLayoutInflater();

        private MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return TutorialList.TuorialName.length;
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

                convertView = mInflater.inflate(R.layout.fragment_listview_of_item_tutorial_layout, null);
                holder.textView = (TextView) convertView
                        .findViewById(R.id.textView);
                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();

            }


            holder.textView.setText(TutorialList.TuorialName[position]);
            return convertView;
        }



    }
}
