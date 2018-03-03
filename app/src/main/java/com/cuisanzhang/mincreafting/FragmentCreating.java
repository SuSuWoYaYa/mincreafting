package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * <p>
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class FragmentCreating extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    private String language;
    private boolean is_language_of_traditional_chinese  = false;

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

//    public static String DATA_BASE_CATEGORYS[] = {
//            "建筑类",
//            "装饰类",
//            "染料类",
//            "食物类",
//            "照明类",
//            "矿石类",
//            "红石类",
//            "运输类",
//            "工具类",
//            "武器类",
//            "其他类",
//            "烧炼类",
//            "药水类",
//            "附魔类",
//    };

    public static FragmentCreating newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentCreating pageFragment = new FragmentCreating();
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
        View view = inflater.inflate(R.layout.fragment_creating_layout, container, false);

        LinearLayout mian_layout = (LinearLayout)  view.findViewById(R.id.mian_layout);
        LinearLayout layout_btn_building = (LinearLayout) view.findViewById(R.id.layout_btn_building);
//        LinearLayout layout_btn_daily = (LinearLayout) findViewById(R.id.layout_btn_daily);
        LinearLayout layout_btn_decoration = (LinearLayout) view.findViewById(R.id.layout_btn_decoration);
        LinearLayout layout_btn_dye = (LinearLayout) view.findViewById(R.id.layout_btn_dye);
        LinearLayout layout_btn_food = (LinearLayout) view.findViewById(R.id.layout_btn_food);
        LinearLayout layout_btn_lighting = (LinearLayout) view.findViewById(R.id.layout_btn_lighting);
        LinearLayout layout_btn_ore = (LinearLayout) view.findViewById(R.id.layout_btn_ore);
        LinearLayout layout_btn_redstone = (LinearLayout) view.findViewById(R.id.layout_btn_redstone);
        LinearLayout layout_btn_tannsport = (LinearLayout) view.findViewById(R.id.layout_btn_tannsport);
        LinearLayout layout_btn_tools = (LinearLayout) view.findViewById(R.id.layout_btn_tools);
        LinearLayout layout_btn_weapon = (LinearLayout) view.findViewById(R.id.layout_btn_weapon);
        LinearLayout layout_btn_others = (LinearLayout) view.findViewById(R.id.layout_btn_others);
        LinearLayout layout_btn_semlting = (LinearLayout) view.findViewById(R.id.layout_btn_semlting);
        LinearLayout layout_btn_brewing = (LinearLayout) view.findViewById(R.id.layout_btn_brewing);
        LinearLayout layout_btn_enchant = (LinearLayout) view.findViewById(R.id.layout_btn_enchant);


        setTextForLanguageToAllTextView(view);


//        mian_layout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.main_background_block));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tableName ;
                String category ;
                boolean isCreating = true;
//                int layout = R.layout.layout_listview_item_block;
                int loding = R.drawable.loading_of_blocks;




                switch (v.getId()) {
                    case R.id.layout_btn_building:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_BUILDING;
                            category = "建築類合成";

                        }else{
                            tableName = MyDatabaseHelper.TABLE_BUILDING;
                            category = "建筑类合成";
                        }
                        break;
                    case R.id.layout_btn_decoration:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_DECORATION;
                            category = "裝飾類合成";
                        }else{
                            tableName = MyDatabaseHelper.TABLE_DECORATION;
                            category = "装饰类合成";
                        }
                        break;
                    case R.id.layout_btn_dye:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_DYE;
                            category = "染料類合成";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_DYE;
                            category = "染料类合成";
                        }
                        break;
                    case R.id.layout_btn_food:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_FOOD;
                            category = "食物類合成";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_FOOD;
                            category = "食物类合成";
                        }
                        break;
                    case R.id.layout_btn_lighting:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_LIGHTING;
                            category = "照明類合成";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_LIGHTING;
                            category = "照明类合成";
                        }
                        break;
                    case R.id.layout_btn_ore:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ORE;
                            category = "礦石類合成";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_ORE;
                            category = "矿石类合成";
                        }
                        break;
                    case R.id.layout_btn_redstone:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_REDSTONE;
                            category = "紅石和裝置類合成";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_REDSTONE;
                            category = "红石和装置类合成";
                        }
                        break;
                    case R.id.layout_btn_tannsport:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_TANNSPORT;
                            category = "運輸類合成";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_TANNSPORT;
                            category = "运输类合成";
                        }
                        break;
                    case R.id.layout_btn_tools:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_TOOLS;
                            category = "工具類合成";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_TOOLS;
                            category = "工具类合成";
                        }
                        break;
                    case R.id.layout_btn_weapon:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_WEAPON;
                            category = "武器類合成";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_WEAPON;
                            category = "武器类合成";
                        }
                        break;
                    case R.id.layout_btn_others:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_OTHERS;
                            category = "其他類合成";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_OTHERS;
                            category = "其他类合成";
                        }
                        break;
                    case R.id.layout_btn_semlting:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_SMELTING;
                            category = "燒煉類";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_SMELTING;
                            category = "烧炼类";
                        }
                        loding =R.drawable.loading_of_smelting;
//                        isCreating = false;
//                        layout= R.layout.layout_listview_item_smelting;
                        break;
                    case R.id.layout_btn_brewing:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_BREWING;
                            category = "藥水類";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_BREWING;
                            category = "药水类";
                        }
                        loding = R.drawable.loading_of_brewing;
//                        isCreating = false;
//                        layout= R.layout.layout_listview_item_brewing;
                        break;
                    case R.id.layout_btn_enchant:
                        if (is_language_of_traditional_chinese) {
                            //9.0的表错误修正
                            tableName = MyDatabaseHelper.ZW_TABLE_ENCHANT;
                            category = "附魔類";
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_ENCHANT;
                            category = "附魔类";
                        }
                        loding = R.drawable.loading_of_enchant;
                        isCreating = false;
                        break;
                    default:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_BUILDING;
                            category = "建築類合成";
                        }else {
                            tableName = MyDatabaseHelper.TABLE_BUILDING;
                            category = "建筑类合成";
                        }
                        break;
                }


                Intent intent = new Intent(getActivity(), ActivityListViewShowBlocks.class);
                intent.putExtra(ActivityListViewShowBlocks.EXTRA_TABLE_NAME, tableName);
                intent.putExtra(ActivityListViewShowBlocks.EXTRA_CATEGORY, category);
                intent.putExtra(ActivityListViewShowBlocks.EXTRA_LOADING, loding);
                intent.putExtra(ActivityListViewShowBlocks.EXTRA_IS_CREATING, isCreating);
//                intent.putExtra(ActivityListViewShowBlocks.EXTRA_LAYLOUT, R.layout.layout_listview_item_block);
                startActivity(intent);


            }
        };

        layout_btn_building.setOnClickListener(onClickListener);
        layout_btn_semlting.setOnClickListener(onClickListener);
        layout_btn_decoration.setOnClickListener(onClickListener);
        layout_btn_dye.setOnClickListener(onClickListener);
        layout_btn_food.setOnClickListener(onClickListener);
        layout_btn_lighting.setOnClickListener(onClickListener);
        layout_btn_ore.setOnClickListener(onClickListener);
        layout_btn_redstone.setOnClickListener(onClickListener);
        layout_btn_tannsport.setOnClickListener(onClickListener);
        layout_btn_tools.setOnClickListener(onClickListener);
        layout_btn_weapon.setOnClickListener(onClickListener);
        layout_btn_others.setOnClickListener(onClickListener);
        layout_btn_brewing.setOnClickListener(onClickListener);
        layout_btn_enchant.setOnClickListener(onClickListener);

        return  view;
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
            textViewItem1.setText("建築");
            textViewItem2.setText("裝飾");
            textViewItem3.setText("染料");
            textViewItem4.setText("食物");
            textViewItem5.setText("照明");
            textViewItem6.setText("礦石");
            textViewItem7.setText("紅石");
            textViewItem8.setText("運輸");
            textViewItem9.setText("工具");
            textViewItem10.setText("武器");
            textViewItem11.setText("其他");
            textViewItem12.setText("燒煉");
            textViewItem13.setText("藥水");
            textViewItem14.setText("附魔");
        }else {
            textViewItem1.setText("建筑");
            textViewItem2.setText("装饰");
            textViewItem3.setText("染料");
            textViewItem4.setText("食物");
            textViewItem5.setText("照明");
            textViewItem6.setText("矿石");
            textViewItem7.setText("红石");
            textViewItem8.setText("运输");
            textViewItem9.setText("工具");
            textViewItem10.setText("武器");
            textViewItem11.setText("其他");
            textViewItem12.setText("烧炼");
            textViewItem13.setText("药水");
            textViewItem14.setText("附魔");
        }

    }
}