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

                switch (v.getId()) {
                    case R.id.layout_btn_boss:
                        tableName = MyDatabaseHelper.TABLE_MOBS_BOSS;
                        category = "BOSS";
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_hostile:
                        tableName =  MyDatabaseHelper.TABLE_MOBS_HOSTILE;
                        category =  "攻击型生物";
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_neutral:
                        tableName = MyDatabaseHelper.TABLE_MOBS_NEUTRAL;
                        category =  "中立型生物";
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_passive:
                        tableName = MyDatabaseHelper.TABLE_MOBS_PASSIVE;
                        category = "被动型生物";
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_tameable:
                        tableName = MyDatabaseHelper.TABLE_MOBS_TAMEABLE;
                        category =  "可驯服生物";
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_utility:
                        tableName = MyDatabaseHelper.TABLE_MOBS_UTILITY;
                        category = "效用型生物";
                        loading= R.drawable.loading_of_mobs;
                        break;
                    case R.id.layout_btn_other_mobs:
                        tableName = MyDatabaseHelper.TABLE_MOBS_UNUSE;
                        category = "其他的生物";
                        loading= R.drawable.loading_of_mobs;
                        break;


                    case R.id.layout_btn_food:
                        tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_FOOD;
                        category = "食物类物品";
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_plants:
                        tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_PLANTS;
                        category = "植物类物品";
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_materials:
                        tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_MATERIALS;
                        category = "材料类物品";
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_natural:
                        tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_NATURAL;
                        category = "自然生成方块";
                        loading= R.drawable.loading_of_wuping;
                        break;
                    case R.id.layout_btn_structures:
                        tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_STRUCTURES;
                        category = "结构方块";
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;
                    case R.id.layout_btn_commands:
                        tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_COMMANDS;
                        category = "命令类方块";
                        loading= R.drawable.loading_of_wuping;
                        break;
                    case R.id.layout_btn_other_block:
                        tableName = MyDatabaseHelper.TABLE_ITEM_BLOCK_OTHERS;
                        category = "其他物品";
                        loading= R.drawable.loading_of_wuping;
                        isCreating = true;
                        break;

                    default:
                        tableName = MyDatabaseHelper.TABLE_MOBS_BOSS;
                        category = "BOSS";
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