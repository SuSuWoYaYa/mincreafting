package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Created by hesuxiang on 17/10/4.
 */

public class FragmentTutorial extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;


//    public static String DATA_BASE_tutorialCategaryS[] = {
//            "新手指南",
//            "环境介绍",
//            "进阶指南",
//            "建筑教程",
//            "种植教程",
//            "刷怪教程",
//            "采矿技术",
//            "附魔烧炼",
//            "初级红石",
//            "红石进阶",
//            "高级技术",
//            "更多挑战",
//            "网易教程",
//            "网络教程",
//    };

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

                String[] tutorialNames;
                String[] tutorialFiles;
                
                String tutorialCategary;

                switch (v.getId()) {
                    case R.id.layout_btn_newplay:
                        tutorialNames = TutorialListData.tutorial_xinshoujiaochen_names;
                        tutorialFiles = TutorialListData.tutorial_xinshoujiaochen_files;
                        tutorialCategary = getString(R.string.xingshouzhinan);
                        break;
                    case R.id.layout_btn_huanjingjieshao:
                        tutorialNames = TutorialListData.tutorial_huangjingjieshao_names;
                        tutorialFiles = TutorialListData.tutorial_huangjingjieshao_files;
                        tutorialCategary = getString(R.string.huangjinjieshao);
                        break;
                    case R.id.layout_btn_jinjiezhinan:
                        tutorialNames = TutorialListData.tutorial_jingjiezhinan_names;
                        tutorialFiles = TutorialListData.tutorial_jingjiezhinan_files;
                        tutorialCategary = getString(R.string.jingjiezhinan);
                        break;
                    case R.id.layout_btn_building:
                        tutorialNames = TutorialListData.tutorial_build_names;
                        tutorialFiles = TutorialListData.tutorial_build_files;
                        tutorialCategary = getString(R.string.jianzhujiaocheng);
                        break;
                    case R.id.layout_btn_zhongzhi:
                        tutorialNames = TutorialListData.tutorial_zhongzhijiaocheng_names;
                        tutorialFiles = TutorialListData.tutorial_zhongzhijiaocheng_files;
                        tutorialCategary = getString(R.string.zhongzhijiaocheng);
                        break;
                    case R.id.layout_btn_shuaguai:
                        tutorialNames = TutorialListData.tutorial_shuaguaijiaocheng_names;
                        tutorialFiles = TutorialListData.tutorial_shuaguaijiaocheng_files;
                        tutorialCategary = getString(R.string.shuaguaijiaocheng);
                        break;
                    case R.id.layout_btn_caikuaijishu:
                        tutorialNames = TutorialListData.tutorial_caikuangjishu_names;
                        tutorialFiles = TutorialListData.tutorial_caikuangjishu_files;
                        tutorialCategary = getString(R.string.caikuanjishu);
                        break;
                    case R.id.layout_btn_fumoshaolian:
                        tutorialNames = TutorialListData.tutorial_fumoheshaolian_names;
                        tutorialFiles = TutorialListData.tutorial_fumoheshaolian_files;
                        tutorialCategary = getString(R.string.fumoshaoliao);
                        break;
                    case R.id.layout_btn_chujihongshi:
                        tutorialNames = TutorialListData.tutorial_chujihongshi_names;
                        tutorialFiles = TutorialListData.tutorial_chujihongshi_files;
                        tutorialCategary = getString(R.string.chujichongshi);
                        break;
                    case R.id.layout_btn_hongshijinjie:
                        tutorialNames = TutorialListData.tutorial_hongshijingjie_names;
                        tutorialFiles = TutorialListData.tutorial_hongshijingjie_files;
                        tutorialCategary = getString(R.string.hongshigaoji);
                        break;
                    case R.id.layout_btn_gaojijishu:
                        tutorialNames = TutorialListData.tutorial_gaojijishu_names;
                        tutorialFiles = TutorialListData.tutorial_gaojijishu_files;
                        tutorialCategary = getString(R.string.gaojijishu);
                        break;
                    case R.id.layout_btn_gengduotiaozhan:
                        tutorialNames = TutorialListData.tutorial_tiaozhan_names;
                        tutorialFiles = TutorialListData.tutorial_tiaozhan_files;
                        tutorialCategary = getString(R.string.gengduotiaozhang);
                        break;
                    case R.id.layout_btn_mc163:
                        tutorialNames = TutorialListData.tutorial_mc163_names;
                        tutorialFiles = TutorialListData.tutorial_mc163_files;
                        tutorialCategary = getString(R.string.wangyijiaocheng);
                        break;
                    case R.id.layout_btn_wangluojiaocheng:
                        tutorialNames = TutorialListData.tutorial_wangluojiaocheng_names;
                        tutorialFiles = TutorialListData.tutorial_wangluojiaocheng_files;
                        tutorialCategary = getString(R.string.wangluojiaocheng);
                        break;
                    default:
                        tutorialNames = TutorialListData.tutorial_wangluojiaocheng_names;
                        tutorialFiles = TutorialListData.tutorial_wangluojiaocheng_files;
                        tutorialCategary = getString(R.string.xingshouzhinan);
                        break;
                }


                Intent intent = new Intent(getActivity(), ActivityTutorialList.class);
                intent.putExtra(ActivityTutorialList.EXTRA_TUTORIAL_NAMES, tutorialNames);
                intent.putExtra(ActivityTutorialList.EXTRA_TUTORIAL_FILES, tutorialFiles);
                intent.putExtra(ActivityTutorialList.EXTRA_TUTORIAL_CATEGARY, tutorialCategary);
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


}