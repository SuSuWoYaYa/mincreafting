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
import android.widget.LinearLayout;
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


    public static String DATA_BASE_tutorialFilesS[] = {
            "新手指南",
            "环境介绍",
            "进阶指南",
            "建筑教程",
            "种植教程",
            "刷怪教程",
            "采矿技术",
            "附魔烧炼",
            "初级红石",
            "红石进阶",
            "高级技术",
            "更多挑战",
            "网易教程",
            "网络教程",
    };

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tutorial_layout, container, false);

//        LinearLayout mian_layout = (LinearLayout) view.findViewById(R.id.mian_layout);
        LinearLayout layout_btn_newplay = (LinearLayout) view.findViewById(R.id.layout_btn_newplay);
//        LinearLayout layout_btn_daily = (LinearLayout) findViewById(R.id.layout_btn_daily);
        LinearLayout layout_btn_huanjingjieshao = (LinearLayout) view.findViewById(R.id.layout_btn_huanjingjieshao);
        LinearLayout layout_btn_jinjiezhinan = (LinearLayout) view.findViewById(R.id.layout_btn_jinjiezhinan);
        LinearLayout layout_btn_building = (LinearLayout) view.findViewById(R.id.layout_btn_building);
        LinearLayout layout_btn_zhongzhi = (LinearLayout) view.findViewById(R.id.layout_btn_zhongzhi);
        LinearLayout layout_btn_shuaguai = (LinearLayout) view.findViewById(R.id.layout_btn_shuaguai);

        LinearLayout layout_btn_caikuaijishu = (LinearLayout) view.findViewById(R.id.layout_btn_caikuaijishu);
        LinearLayout layout_btn_fumoshaolian = (LinearLayout) view.findViewById(R.id.layout_btn_fumoshaolian);
        LinearLayout layout_btn_chujihongshi = (LinearLayout) view.findViewById(R.id.layout_btn_chujihongshi);
        LinearLayout layout_btn_hongshijinjie = (LinearLayout) view.findViewById(R.id.layout_btn_hongshijinjie);
        LinearLayout layout_btn_gaojijishu = (LinearLayout) view.findViewById(R.id.layout_btn_gaojijishu);
        LinearLayout layout_btn_gengduotiaozhan = (LinearLayout) view.findViewById(R.id.layout_btn_gengduotiaozhan);
        LinearLayout layout_btn_mc163 = (LinearLayout) view.findViewById(R.id.layout_btn_mc163);
        LinearLayout layout_btn_wangluojiaocheng = (LinearLayout) view.findViewById(R.id.layout_btn_wangluojiaocheng);


//        mian_layout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.main_background_block));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tutorialNames;
                String tutorialFiles;

                switch (v.getId()) {
                    case R.id.layout_btn_newplay:
                        tutorialNames = MyDatabaseHelper.TABLE_BUILDING;
                        tutorialFiles = "新手指南";
                        break;
                    case R.id.layout_btn_huanjingjieshao:
                        tutorialNames = MyDatabaseHelper.TABLE_DECORATION;
                        tutorialFiles = "环境介绍";
                        break;
                    case R.id.layout_btn_jinjiezhinan:
                        tutorialNames = MyDatabaseHelper.TABLE_DYE;
                        tutorialFiles = "进阶指南";
                        break;
                    case R.id.layout_btn_building:
                        tutorialNames = MyDatabaseHelper.TABLE_FOOD;
                        tutorialFiles = "建筑教程";
                        break;
                    case R.id.layout_btn_zhongzhi:
                        tutorialNames = MyDatabaseHelper.TABLE_LIGHTING;
                        tutorialFiles = "种植教程";
                        break;
                    case R.id.layout_btn_shuaguai:
                        tutorialNames = MyDatabaseHelper.TABLE_ORE;
                        tutorialFiles = "刷怪教程";
                        break;
                    case R.id.layout_btn_caikuaijishu:
                        tutorialNames = MyDatabaseHelper.TABLE_REDSTONE;
                        tutorialFiles = "采矿技术";
                        break;
                    case R.id.layout_btn_fumoshaolian:
                        tutorialNames = MyDatabaseHelper.TABLE_TANNSPORT;
                        tutorialFiles = "附魔烧炼";
                        break;
                    case R.id.layout_btn_chujihongshi:
                        tutorialNames = MyDatabaseHelper.TABLE_TOOLS;
                        tutorialFiles = "初级红石";
                        break;
                    case R.id.layout_btn_hongshijinjie:
                        tutorialNames = MyDatabaseHelper.TABLE_WEAPON;
                        tutorialFiles = "红石高级";
                        break;
                    case R.id.layout_btn_gaojijishu:
                        tutorialNames = MyDatabaseHelper.TABLE_OTHERS;
                        tutorialFiles = "高级技术";
                        break;
                    case R.id.layout_btn_gengduotiaozhan:
                        tutorialNames = MyDatabaseHelper.TABLE_SMELTING;
                        tutorialFiles = "更多挑战";
                        break;
                    case R.id.layout_btn_mc163:
                        tutorialNames = TutorialList.MC_163;
                        tutorialFiles = "网易教程";
                        break;
                    case R.id.layout_btn_wangluojiaocheng:
                        tutorialNames = TutorialList.WANGLUOJIAOCHENG;
                        tutorialFiles = "网络教程";
                        break;
                    default:
                        tutorialNames = TutorialList.WANGLUOJIAOCHENG;
                        tutorialFiles = "新手指南";
                        break;
                }


                Intent intent = new Intent(getActivity(), ActivityTutorialList.class);
                intent.putExtra(TutorialList.EXTRA_TUTORIAL_NAMES, tutorialNames);
                intent.putExtra(TutorialList.EXTRA_TUTORIAL_FILES, tutorialFiles);
//                intent.putExtra(ActivityListViewShowBlocks.EXTRA_LAYLOUT, R.layout.layout_listview_item_block);
                startActivity(intent);


            }
        };

        layout_btn_newplay.setOnClickListener(onClickListener);
        layout_btn_huanjingjieshao.setOnClickListener(onClickListener);
        layout_btn_jinjiezhinan.setOnClickListener(onClickListener);
        layout_btn_building.setOnClickListener(onClickListener);
        layout_btn_zhongzhi.setOnClickListener(onClickListener);
        layout_btn_shuaguai.setOnClickListener(onClickListener);
        layout_btn_caikuaijishu.setOnClickListener(onClickListener);
        layout_btn_fumoshaolian.setOnClickListener(onClickListener);
        layout_btn_chujihongshi.setOnClickListener(onClickListener);
        layout_btn_hongshijinjie.setOnClickListener(onClickListener);
        layout_btn_gaojijishu.setOnClickListener(onClickListener);
        layout_btn_gengduotiaozhan.setOnClickListener(onClickListener);
        layout_btn_mc163.setOnClickListener(onClickListener);
        layout_btn_wangluojiaocheng.setOnClickListener(onClickListener);

        return view;
    }
//    private class MyAdapter extends BaseAdapter {
//
//        private final class ViewHolder {
//            private TextView textView;
//        }
//
//
//        private ViewHolder holder = null;
//        private LayoutInflater mInflater = getActivity().getLayoutInflater();
//
//        private MyAdapter(Context context) {
//            this.mInflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            return TutorialList.TuorialName.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                holder = new MyAdapter.ViewHolder();
//
//                convertView = mInflater.inflate(R.layout.fragment_listview_of_item_tutorial_layout, null);
//                holder.textView = (TextView) convertView
//                        .findViewById(R.id.textView);
//                convertView.setTag(holder);
//            } else {
//
//                holder = (ViewHolder) convertView.getTag();
//
//            }
//
//
//            holder.textView.setText(TutorialList.TuorialName[position]);
//            return convertView;
//        }
//
//
//    }

}