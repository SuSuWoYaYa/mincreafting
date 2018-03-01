package com.cuisanzhang.mincreafting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class FragmentMobsAndItemAndBlocks extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

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
//
//    public static String DATA_BASE_CATEGORYS[] = {
//            "Boss",
//            "攻击型生物",
//            "中立型生物",
//            "被动型生物",
//            "可驯服生物",
//            "其他的生物",
//            "效用型生物",
//    };

    public static FragmentMobsAndItemAndBlocks newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FragmentMobsAndItemAndBlocks pageFragment = new FragmentMobsAndItemAndBlocks();
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

        View view = inflater.inflate(R.layout.fragment_mobs_and_items_and_blocks, container, false);

        LinearLayout layout_btn_boss = (LinearLayout) view.findViewById(R.id.layout_btn_boss);
        LinearLayout layout_btn_hostile = (LinearLayout) view.findViewById(R.id.layout_btn_hostile);
        LinearLayout layout_btn_neutral = (LinearLayout) view.findViewById(R.id.layout_btn_neutral);
        LinearLayout layout_btn_passive = (LinearLayout) view.findViewById(R.id.layout_btn_passive);
        LinearLayout layout_btn_tameable = (LinearLayout) view.findViewById(R.id.layout_btn_tameable);
        LinearLayout layout_btn_utility = (LinearLayout) view.findViewById(R.id.layout_btn_utility);
        LinearLayout layout_btn_other_mobs = (LinearLayout) view.findViewById(R.id.layout_btn_other_mobs);

        LinearLayout layout_btn_food = (LinearLayout) view.findViewById(R.id.layout_btn_food);
        LinearLayout layout_btn_plants = (LinearLayout) view.findViewById(R.id.layout_btn_plants);
        LinearLayout layout_btn_materials = (LinearLayout) view.findViewById(R.id.layout_btn_materials);
        LinearLayout layout_btn_natural = (LinearLayout) view.findViewById(R.id.layout_btn_natural);
        LinearLayout layout_btn_structures = (LinearLayout) view.findViewById(R.id.layout_btn_structures);
        LinearLayout layout_btn_commands = (LinearLayout) view.findViewById(R.id.layout_btn_commands);
        LinearLayout layout_btn_other_block = (LinearLayout) view.findViewById(R.id.layout_btn_other_block);

        setTextForLanguageToAllTextView(view);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tableName ;
                String category ;
                int loading;
                boolean isCreating = false;

                String language = LanguageUtil.getLocaleLanguage(getContext());

                switch (v.getId()) {
                    case R.id.layout_btn_boss:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_BOSS;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_BOSS;
                        }
                        category = "BOSS";
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_hostile:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_HOSTILE;
                            category =  "攻擊型生物";
                        }else {
                            tableName =  MyDatabaseHelper.TABLE_MOBS_HOSTILE;
                            category =  "攻击型生物";
                        }
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_neutral:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_NEUTRAL;
                            category =  "中立型生物";
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_NEUTRAL;
                            category =  "中立型生物";
                        }
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_passive:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_PASSIVE;
                            category =  "被動型生物";
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_PASSIVE;
                            category =  "被动型生物";
                        }
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_tameable:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_TAMEABLE;
                            category =  "可馴服生物";
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_TAMEABLE;
                            category =  "可驯服生物";
                        }
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_utility:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_UTILITY;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_UTILITY;
                        }
                        category = "效用型生物";
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_other_mobs:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_UNUSE;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_UNUSE;
                        }
                        category = "其他的生物";
                        loading= R.drawable.loading_of_mobs;
                        break;


                    case R.id.layout_btn_food:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_FOOD;
                            category =  "食物類物品";
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_FOOD;
                            category =  "食物类物品";
                        }
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_plants:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_PLANTS;
                            category =  "植物類物品";
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_PLANTS;
                            category =  "植物类物品";
                        }
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_materials:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_MATERIALS;
                            category =  "材料類物品";
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_MATERIALS;
                            category =  "材料类物品";
                        }
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_natural:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_NATURAL;
                            category =  "自然生成方塊";
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_NATURAL;
                            category =  "自然生成方块";
                        }
                        loading= R.drawable.loading_of_wuping;
                        break;
                    case R.id.layout_btn_structures:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_STRUCTURES;
                            category =  "結構方塊";
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_STRUCTURES;
                            category =  "结构方块";
                        }
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_commands:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_COMMANDS;
                            category =  "命令類方塊";
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_COMMANDS;
                            category =  "命令类方块";
                        }
                        loading= R.drawable.loading_of_wuping;
                        break;
                    case R.id.layout_btn_other_block:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_OTHERS;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_OTHERS;
                        }
                        category = "其他物品";
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;

                    default:
                        if (is_language_of_traditional_chinese) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_BOSS;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_BOSS;
                        }
                        category = getString(R.string.boss);
                        loading= R.drawable.loading_of_mobs;
                        break;
                }

                Intent intent = new Intent(getActivity(), ActivityListViewShowBlocks.class);
                intent.putExtra(ActivityListViewShowBlocks.EXTRA_TABLE_NAME, tableName);
                intent.putExtra(ActivityListViewShowBlocks.EXTRA_CATEGORY, category);
                intent.putExtra(ActivityListViewShowBlocks.EXTRA_LOADING, loading);
                intent.putExtra(ActivityListViewShowBlocks.EXTRA_IS_CREATING, isCreating);

//                intent.putExtra(ActivityListViewShowBlocks.EXTRA_LAYLOUT, R.layout.layout_listview_item_block);
                startActivity(intent);


            }
        };

        layout_btn_boss.setOnClickListener(onClickListener);
        layout_btn_hostile.setOnClickListener(onClickListener);
        layout_btn_neutral.setOnClickListener(onClickListener);
        layout_btn_passive.setOnClickListener(onClickListener);
        layout_btn_tameable.setOnClickListener(onClickListener);
        layout_btn_utility.setOnClickListener(onClickListener);
        layout_btn_other_mobs.setOnClickListener(onClickListener);


        layout_btn_food.setOnClickListener(onClickListener);
        layout_btn_plants.setOnClickListener(onClickListener);
        layout_btn_materials.setOnClickListener(onClickListener);
        layout_btn_natural.setOnClickListener(onClickListener);
        layout_btn_structures.setOnClickListener(onClickListener);
        layout_btn_commands.setOnClickListener(onClickListener);
        layout_btn_other_block.setOnClickListener(onClickListener);

        return  view;
    }
    public void setTextForLanguageToAllTextView(View mainView) {

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

            textViewItem2.setText("食物類");
            textViewItem3.setText("攻擊型");
            textViewItem4.setText("植物類");
            textViewItem5.setText("中立型");
            textViewItem6.setText("材料類");
            textViewItem7.setText("被動型");
            textViewItem8.setText("自然生成");
            textViewItem9.setText("可馴服");
            textViewItem10.setText("結構方塊");
            textViewItem11.setText("效用型");
            textViewItem12.setText("命令方塊");
            textViewItem13.setText("其他生物");
            textViewItem14.setText("其他物品");
        }else {
            textViewItem2.setText("食物类");
            textViewItem3.setText("攻击型");
            textViewItem4.setText("植物类");
            textViewItem5.setText("中立型");
            textViewItem6.setText("材料类");
            textViewItem7.setText("被动型");
            textViewItem8.setText("自然生成");
            textViewItem9.setText("可驯服");
            textViewItem10.setText("结构方块");
            textViewItem11.setText("效用型");
            textViewItem12.setText("命令方块");
            textViewItem13.setText("其他生物");
            textViewItem14.setText("其他物品");
        }

    }
}