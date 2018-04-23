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
import android.widget.TextView;


/**
 * Created by hesuxiang on 17/10/4.
 */

public class FragmentTutorial extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    private TextView textViewItem1;
    private TextView textViewItem2;
    private TextView textViewItem3;
    private TextView textViewItem4;
    private TextView textViewItem5;
    private TextView textViewItem6;
    private TextView textViewItem7;
    private TextView textViewItem8;
    private TextView textViewItem9;
    private TextView textViewItem10;
    private TextView textViewItem11;
    private TextView textViewItem12;
    private TextView textViewItem13;
    private TextView textViewItem14;

    private String language;
    private boolean is_language_of_traditional_chinese  = false;

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

        language = LanguageUtil.getLocaleLanguage(getContext());
        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
            is_language_of_traditional_chinese = true;
        }

        View view = inflater.inflate(R.layout.fragment_tutorial_layout_with_icon, container, false);

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

        setTextForLanguageToAllTextView(view);

//        mian_layout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.main_background_block));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                boolean isOnline = false;
//                String[] tutorialNames;
//                String[] tutorialFiles;
                
                String tutorialCategary;
                int tutorialCode;
//                String language = LanguageUtil.getLocaleLanguage(getContext());

                switch (v.getId()) {
                    case R.id.layout_btn_newplay:
//                        if (is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_xinshoujiaochen_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_xinshoujiaochen_files_zw;
//                        } else {
//                            tutorialNames = TutorialListData.tutorial_xinshoujiaochen_names;
//                            tutorialFiles = TutorialListData.tutorial_xinshoujiaochen_files;
//                        }
                        tutorialCategary = getString(R.string.xingshouzhinan);
                        tutorialCode = TutorialListData.TUTORIAL_CODE_XINSHOUJIAOCHEN;
                        break;
                    case R.id.layout_btn_huanjingjieshao:
//                        if (is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_huangjingjieshao_names;
//                            tutorialFiles = TutorialListData.tutorial_huangjingjieshao_files;
//                            tutorialNames = TutorialListData.tutorial_huangjingjieshao_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_huangjingjieshao_files_zw;
//                            tutorialCategary = "環境介紹";
//                        } else {

//                            tutorialNames = TutorialListData.tutorial_huangjingjieshao_names;
//                            tutorialFiles = TutorialListData.tutorial_huangjingjieshao_files;
                            tutorialCategary = "环境介绍";
                            tutorialCode = TutorialListData.TUTORIAL_CODE_HUANGJINGJIAOCHEN;
//                        }

                        break;
                    case R.id.layout_btn_jinjiezhinan:
//                        if (is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_jingjiezhinan_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_jingjiezhinan_files_zw;
//                            tutorialCategary = "進階指南";
//                        } else {
//                            tutorialNames = TutorialListData.tutorial_jingjiezhinan_names;
//                            tutorialFiles = TutorialListData.tutorial_jingjiezhinan_files;
                            tutorialCategary = "进阶指南";
                            tutorialCode = TutorialListData.TUTORIAL_CODE_JINGJIEZHINAN;
//                        }


                        break;
                    case R.id.layout_btn_building:
//                        if (is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_build_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_build_files_zw;
//                            tutorialCategary = "建築教程";
//                        } else {
//                            tutorialNames = TutorialListData.tutorial_build_names;
//                            tutorialFiles = TutorialListData.tutorial_build_files;
                            tutorialCategary = "建筑教程";
                        tutorialCode = TutorialListData.TUTORIAL_CODE_BUILD;
//                        }
                        break;
                    case R.id.layout_btn_zhongzhi:
//                        if (is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_zhongzhijiaocheng_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_zhongzhijiaocheng_files_zw;
//                            tutorialCategary = "種植教程";
//                        } else {
//
//                            tutorialNames = TutorialListData.tutorial_zhongzhijiaocheng_names;
//                            tutorialFiles = TutorialListData.tutorial_zhongzhijiaocheng_files;
                            tutorialCategary = "种植教程";
                        tutorialCode = TutorialListData.TUTORIAL_CODE_ZHONGZHIJIAOCHENG;
//                        }
                        break;
                    case R.id.layout_btn_shuaguai:
//                        if (is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_shuaguaijiaocheng_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_shuaguaijiaocheng_files_zw;
//                            tutorialCategary = "刷怪教程";
//                        } else {
//                            tutorialNames = TutorialListData.tutorial_shuaguaijiaocheng_names;
//                            tutorialFiles = TutorialListData.tutorial_shuaguaijiaocheng_files;
                            tutorialCategary = "刷怪教程";
                        tutorialCode = TutorialListData.TUTORIAL_CODE_SHUAGUAIJIAOCHENG;
//                        }
                        break;
                    case R.id.layout_btn_caikuaijishu:
//                        if (is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_caikuangjishu_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_caikuangjishu_files_zw;
//                            tutorialCategary = "採礦技術";
//                        } else {
//                            tutorialNames = TutorialListData.tutorial_caikuangjishu_names;
//                            tutorialFiles = TutorialListData.tutorial_caikuangjishu_files;
                            tutorialCategary = "采矿技术";
                        tutorialCode = TutorialListData.TUTORIAL_CODE_CAIKUANGJISHU;
//                        }
                        break;
                    case R.id.layout_btn_fumoshaolian:
//                        if (is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_fumoheshaolian_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_fumoheshaolian_files_zw;
//                            tutorialCategary = "附魔燒煉";
//                        }else {
//
//                            tutorialNames = TutorialListData.tutorial_fumoheshaolian_names;
//                            tutorialFiles = TutorialListData.tutorial_fumoheshaolian_files;
                            tutorialCategary = "附魔烧炼";
                        tutorialCode = TutorialListData.TUTORIAL_CODE_FUMOSHAOLIAN;
//                        }
                        break;
                    case R.id.layout_btn_chujihongshi:
//                        if(is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_chujihongshi_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_chujihongshi_files_zw;
//                            tutorialCategary = "初級紅石";
//                        }else {
//                            tutorialNames = TutorialListData.tutorial_chujihongshi_names;
//                            tutorialFiles = TutorialListData.tutorial_chujihongshi_files;

                            tutorialCategary = "初级红石";
                        tutorialCode = TutorialListData.TUTORIAL_CODE_CHUJIHONGSHI;
//                        }
                        break;
                    case R.id.layout_btn_hongshijinjie:
//                        if(is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_hongshijingjie_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_hongshijingjie_files_zw;
//                            tutorialCategary = "紅石高級";
//                        }else{
//                            tutorialNames = TutorialListData.tutorial_hongshijingjie_names;
//                            tutorialFiles = TutorialListData.tutorial_hongshijingjie_files;
                            tutorialCategary = "红石高级";
                        tutorialCode = TutorialListData.TUTORIAL_CODE_HONGSHIJINGJIE;
//                        }
                        break;
                    case R.id.layout_btn_gaojijishu:
//                        if(is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_gaojijishu_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_gaojijishu_files_zw;
//                            tutorialCategary = "高級技術";
//                        }else {
//                            tutorialNames = TutorialListData.tutorial_gaojijishu_names;
//                            tutorialFiles = TutorialListData.tutorial_gaojijishu_files;
                            tutorialCategary = "高级技术";
                        tutorialCode = TutorialListData.TUTORIAL_CODE_GAOJIJISHU;
//                        }
                        break;
                    case R.id.layout_btn_gengduotiaozhan:
//                        if(is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_tiaozhan_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_tiaozhan_files_zw;
//                            tutorialCategary = "更多挑戰";
//                        }else {
//                            tutorialNames = TutorialListData.tutorial_tiaozhan_names;
//                            tutorialFiles = TutorialListData.tutorial_tiaozhan_files;
                            tutorialCategary = "更多挑战";
                        tutorialCode = TutorialListData.TUTORIAL_CODE_TIAOZHAN;
//                        }
                        break;
                    case R.id.layout_btn_mc163:
//                        if(is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_mc163_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_mc163_files_zw;
//                            tutorialCategary = "網易教程";
//                        }else {
//                            tutorialNames = TutorialListData.tutorial_mc163_names;
//                            tutorialFiles = TutorialListData.tutorial_mc163_files;
                            tutorialCategary = "网易教程";
                        tutorialCode = TutorialListData.TUTORIAL_CODE_MC163;
//                        }
                        break;
                    case R.id.layout_btn_wangluojiaocheng:
//                        if(is_language_of_traditional_chinese) {
//                            tutorialNames = TutorialListData.tutorial_wangluojiaocheng_names_zw;
//                            tutorialFiles = TutorialListData.tutorial_wangluojiaocheng_files_zw;
//                            tutorialCategary = "網絡教程";
//                        }else {
//                            tutorialNames = TutorialListData.tutorial_wangluojiaocheng_names;
//                            tutorialFiles = TutorialListData.tutorial_wangluojiaocheng_files;
                            tutorialCategary = "网络教程";
                        tutorialCode = TutorialListData.TUTORIAL_CODE_INTERNET;
//                        }
                        break;
                    default:
//                        tutorialNames = TutorialListData.tutorial_wangluojiaocheng_names;
//                        tutorialFiles = TutorialListData.tutorial_wangluojiaocheng_files;
                        tutorialCategary = getString(R.string.xingshouzhinan);
                        tutorialCode = TutorialListData.TUTORIAL_CODE_XINSHOUJIAOCHEN;
                        break;
                }


                Intent intent = new Intent(getActivity(), ActivityTutorialList.class);
//                intent.putExtra(ActivityTutorialList.EXTRA_TUTORIAL_IS_ONLINE, isOnline);
//                intent.putExtra(ActivityTutorialList.EXTRA_TUTORIAL_NAMES, tutorialNames);
//                intent.putExtra(ActivityTutorialList.EXTRA_TUTORIAL_FILES, tutorialFiles);
                intent.putExtra(ActivityTutorialList.EXTRA_TUTORIAL_CATEGARY, tutorialCategary);
                intent.putExtra(ActivityTutorialList.EXTRA_TUTORIAL_CODE, tutorialCode);
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

    public void setTextForLanguageToAllTextView(View mainView) {

        textViewItem1 = (TextView) mainView.findViewById(R.id.textViewItem1);
        textViewItem2 = (TextView) mainView.findViewById(R.id.textViewItem2);
        textViewItem3 = (TextView) mainView.findViewById(R.id.textViewItem3);
        textViewItem4 = (TextView) mainView.findViewById(R.id.textViewItem4);
        textViewItem5 = (TextView) mainView.findViewById(R.id.textViewItem5);
        textViewItem6 = (TextView) mainView.findViewById(R.id.textViewItem6);
        textViewItem7 = (TextView) mainView.findViewById(R.id.textViewItem7);
        textViewItem8 = (TextView) mainView.findViewById(R.id.textViewItem8);
        textViewItem9 = (TextView) mainView.findViewById(R.id.textViewItem9);
        textViewItem10 = (TextView) mainView.findViewById(R.id.textViewItem10);
        textViewItem11 = (TextView) mainView.findViewById(R.id.textViewItem11);
        textViewItem12 = (TextView) mainView.findViewById(R.id.textViewItem12);
        textViewItem13 = (TextView) mainView.findViewById(R.id.textViewItem13);
        textViewItem14 = (TextView) mainView.findViewById(R.id.textViewItem14);

        if (is_language_of_traditional_chinese){
            textViewItem1.setText("新手");
            textViewItem2.setText("環境");
            textViewItem3.setText("進階");
            textViewItem4.setText("建築");
            textViewItem5.setText("種植");
            textViewItem6.setText("刷怪");
            textViewItem7.setText("採礦");
            textViewItem8.setText("挑戰");
            textViewItem9.setText("初級紅石");
            textViewItem10.setText("紅石進階");
            textViewItem11.setText("附魔燒煉");
            textViewItem12.setText("高級技術");
            textViewItem13.setText("網易教程");
            textViewItem14.setText("網絡教程");
        }else {
            textViewItem1.setText("新手");
            textViewItem2.setText("环境");
            textViewItem3.setText("进阶");
            textViewItem4.setText("建筑");
            textViewItem5.setText("种植");
            textViewItem6.setText("刷怪");
            textViewItem7.setText("采矿");
            textViewItem8.setText("挑战");
            textViewItem9.setText("初级红石");
            textViewItem10.setText("红石进阶");
            textViewItem11.setText("附魔烧炼");
            textViewItem12.setText("高级技术");
            textViewItem13.setText("网易教程");
            textViewItem14.setText("网络教程");
        }

    }
}