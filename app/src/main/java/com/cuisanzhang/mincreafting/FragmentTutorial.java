package com.cuisanzhang.mincreafting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by hesuxiang on 17/10/4.
 */

public class FragmentTutorial extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private List<Map<String, String>> data;
    private boolean hasTutorial = false;
    private static String TAG = "FragmentTutorial";
    private ProgressBar mProgressBar;
    private Button mbtnStart;
    private Button mbtnRestart;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    private Activity mActivity;


    private String testzipUrl = "http://cuisanzhang.u.qiniudn.com/test/test.zip";

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

        Log.e("FragmentTutorial", "context.getCacheDir().getAbsolutePath() = " + getActivity().getCacheDir().getAbsolutePath());

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (!hasTutorial) {
            View downView = inflater.inflate(R.layout.layout_down_files, container, false);

            mProgressBar = (ProgressBar) downView.findViewById(R.id.progressBar);
            mProgressBar.setMax(100);
            mProgressBar.setProgress(0);
            mbtnStart = (Button) downView.findViewById(R.id.btnStrat);
            mbtnRestart = (Button) downView.findViewById(R.id.btnRestart);


            mbtnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OkHttpUtils//
                            .get()//
                            .url(testzipUrl)//
                            .build()//
                            .execute(new FileCallBack(mActivity.getFilesDir().getAbsolutePath(), "test.zip")//
                            {

                                /**
                                 * UI Thread
                                 *
                                 * @param progress
                                 */
                                public void inProgress(float progress, long total, int id) {

                                    mProgressBar.setProgress((int) (100 * progress));
                                    Log.e(TAG, "inProgress " + progress + "progress(int) = " + (int) (100 * progress));
                                }

                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e(TAG, "onError " + e.getMessage() );

                                }

                                @Override
                                public void onResponse(File response, int id) {
                                    Log.e(TAG, "onResponse :" + response.getAbsolutePath());
                                }
                            });

                }
            });

            return downView;
        }

        View tutorialView = inflater.inflate(R.layout.fragment_tutorial_layout, container, false);


        ListView listView = (ListView) tutorialView.findViewById(R.id.listView);
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


        Button button = (Button) tutorialView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DownloadUtil.get(mActivity).download(testzipUrl, "/html", new DownloadUtil.OnDownloadListener() {

                    @Override
                    public void onDownloadSuccess() {
                        Log.e("onDownloadSuccess", "onDownloadSuccess");
                    }

                    @Override
                    public void onDownloading(int progress) {
                        Log.e("onDownloading", "onDownloading");
                    }

                    @Override
                    public void onDownloadFailed() {
                        Log.e("onDownloadFailed", "onDownloadFailed");

                    }
                });
            }
        });
        return tutorialView;


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