package com.cuisanzhang.mincreafting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


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
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_BOSS;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_BOSS;
                        }
                        category = getString(R.string.boss);
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_hostile:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_HOSTILE;
                        }else {
                            tableName =  MyDatabaseHelper.TABLE_MOBS_HOSTILE;
                        }
                        category =  getString(R.string.gongjixingshengwu);
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_neutral:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_NEUTRAL;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_NEUTRAL;
                        }
                        category =  getString(R.string.zhonglixingshengwu);
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_passive:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_PASSIVE;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_PASSIVE;
                        }
                        category = getString(R.string.beidongxingshengwu);
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_tameable:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_TAMEABLE;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_TAMEABLE;
                        }
                        category =  getString(R.string.kexunfushengwu);
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_utility:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_UTILITY;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_UTILITY;
                        }
                        category = getString(R.string.xiaoyongxingshengwu);
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_other_mobs:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_MOBS_UNUSE;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_MOBS_UNUSE;
                        }
                        category = getString(R.string.qitashengwu);
                        loading= R.drawable.loading_of_mobs;
                        break;


                    case R.id.layout_btn_food:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_FOOD;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_FOOD;
                        }
                        category = getString(R.string.shiwuleiwuping);
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_plants:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_PLANTS;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_PLANTS;
                        }
                        category = getString(R.string.zhiwuleiwuping);
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_materials:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_MATERIALS;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_MATERIALS;
                        }
                        category = getString(R.string.cailiaoleiwuping);
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_natural:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_NATURAL;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_NATURAL;
                        }
                        category = getString(R.string.zhirangshengchengfankuai);
                        loading= R.drawable.loading_of_wuping;
                        break;
                    case R.id.layout_btn_structures:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_STRUCTURES;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_STRUCTURES;
                        }
                        category = getString(R.string.jiegoufangkuai);
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_commands:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_COMMANDS;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_COMMANDS;
                        }
                        category = getString(R.string.minglingleifankuai);
                        loading= R.drawable.loading_of_wuping;
                        break;
                    case R.id.layout_btn_other_block:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
                            tableName = MyDatabaseHelper.ZW_TABLE_ITEM_BLOCK_OTHERS;
                        }else {
                            tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_OTHERS;
                        }
                        category = getString(R.string.qitawuping);
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;

                    default:
                        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
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
}