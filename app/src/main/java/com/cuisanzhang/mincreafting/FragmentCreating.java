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


//        mian_layout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.main_background_block));

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tableName ;
                String category ;
                boolean isCreating = true;
//                int layout = R.layout.layout_listview_item_block;
                int loding = R.drawable.loading_of_blocks;


                String language = LanguageUtil.getLocaleLanguage(getContext());

                switch (v.getId()) {
                    case R.id.layout_btn_building:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_BUILDING;
                        }else{
                            tableName = MyDatabaseHelper.TABLE_BUILDING;
                        }
                        category = getString(R.string.jianzhuleihecheng);
                        break;
                    case R.id.layout_btn_decoration:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_DECORATION;
                        }else{
                            tableName = MyDatabaseHelper.TABLE_DECORATION;
                        }
                        category = getString(R.string.zhuangshileihecheng);
                        break;
                    case R.id.layout_btn_dye:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_DYE;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_DYE;
                        }
                        category = getString(R.string.rangliaoleihecheng);
                        break;
                    case R.id.layout_btn_food:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_FOOD;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_FOOD;
                        }
                        category = getString(R.string.shiwuleihecheng);
                        break;
                    case R.id.layout_btn_lighting:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_LIGHTING;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_LIGHTING;
                        }
                        category = getString(R.string.zhaomingleihecheng);
                        break;
                    case R.id.layout_btn_ore:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ORE;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_ORE;
                        }
                        category = getString(R.string.kuangshileihecheng);
                        break;
                    case R.id.layout_btn_redstone:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_REDSTONE;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_REDSTONE;
                        }
                        category = getString(R.string.redstonehezhuanzhileihechen);
                        break;
                    case R.id.layout_btn_tannsport:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_TANNSPORT;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_TANNSPORT;
                        }
                        category = getString(R.string.yunshuleihecheng);
                        break;
                    case R.id.layout_btn_tools:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_TOOLS;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_TOOLS;
                        }
                        category = getString(R.string.gongjuleihecheng);
                        break;
                    case R.id.layout_btn_weapon:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_WEAPON;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_WEAPON;
                        }
                        category = getString(R.string.wuqileihecheng);
                        break;
                    case R.id.layout_btn_others:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_OTHERS;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_OTHERS;
                        }
                        category = getString(R.string.qitaleihecheng);
                        break;
                    case R.id.layout_btn_semlting:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_SMELTING;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_SMELTING;
                        }
                        category = getString(R.string.shaolianlei);
                        loding =R.drawable.loading_of_smelting;
//                        isCreating = false;
//                        layout= R.layout.layout_listview_item_smelting;
                        break;
                    case R.id.layout_btn_brewing:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_BREWING;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_BREWING;
                        }
                        category = getString(R.string.yaoshuilei);
                        loding = R.drawable.loading_of_brewing;
//                        isCreating = false;
//                        layout= R.layout.layout_listview_item_brewing;
                        break;
                    case R.id.layout_btn_enchant:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_BUILDING;
                        }else{
                            tableName =  MyDatabaseHelper.TABLE_ENCHANT;
                        }
                        category = getString(R.string.fumolei);
                        loding = R.drawable.loading_of_enchant;
                        isCreating = false;
                        break;
                    default:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_BUILDING;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_BUILDING;
                        }
                        category = getString(R.string.jianzhuleihecheng);
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
}