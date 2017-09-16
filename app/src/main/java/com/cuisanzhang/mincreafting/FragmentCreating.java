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


    public static String DATA_BASE_CATEGORYS[] = {
            "建筑类",
            "装饰类",
            "染料类",
            "食物类",
            "照明类",
            "矿石类",
            "红石类",
            "运输类",
            "工具类",
            "武器类",
            "其他类",
            "烧炼类",
            "药水类",
            "附魔类",
    };

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

                switch (v.getId()) {
                    case R.id.layout_btn_building:
                        tableName = MyDatabaseHelper.TABLE_BUILDING;
                        category = "建筑类合成";
                        break;
                    case R.id.layout_btn_decoration:
                        tableName = MyDatabaseHelper.TABLE_DECORATION;
                        category = "装饰类合成";
                        break;
                    case R.id.layout_btn_dye:
                        tableName =  MyDatabaseHelper.TABLE_DYE;
                        category = "染料类合成";
                        break;
                    case R.id.layout_btn_food:
                        tableName =  MyDatabaseHelper.TABLE_FOOD;
                        category = "食物类合成";
                        break;
                    case R.id.layout_btn_lighting:
                        tableName =  MyDatabaseHelper.TABLE_LIGHTING;
                        category = "照明类合成";
                        break;
                    case R.id.layout_btn_ore:
                        tableName =  MyDatabaseHelper.TABLE_ORE;
                        category = "矿石类合成";
                        break;
                    case R.id.layout_btn_redstone:
                        tableName =  MyDatabaseHelper.TABLE_REDSTONE;
                        category = "红石和装置类合成";
                        break;
                    case R.id.layout_btn_tannsport:
                        tableName =  MyDatabaseHelper.TABLE_TANNSPORT;
                        category = "运输类合成";
                        break;
                    case R.id.layout_btn_tools:
                        tableName =  MyDatabaseHelper.TABLE_TOOLS;
                        category = "工具类合成";
                        break;
                    case R.id.layout_btn_weapon:
                        tableName =  MyDatabaseHelper.TABLE_WEAPON;
                        category = "武器类合成";
                        break;
                    case R.id.layout_btn_others:
                        tableName =  MyDatabaseHelper.TABLE_OTHERS;
                        category = "其他类合成";
                        break;
                    case R.id.layout_btn_semlting:
                        tableName =  MyDatabaseHelper.TABLE_SMELTING;
                        category = "烧炼类";
                        loding =R.drawable.loading_of_smelting;
//                        isCreating = false;
//                        layout= R.layout.layout_listview_item_smelting;
                        break;
                    case R.id.layout_btn_brewing:
                        tableName =  MyDatabaseHelper.TABLE_BREWING;
                        category = "药水类";
                        loding = R.drawable.loading_of_brewing;
//                        isCreating = false;
//                        layout= R.layout.layout_listview_item_brewing;
                        break;
                    case R.id.layout_btn_enchant:
                        tableName =  MyDatabaseHelper.TABLE_ENCHANT;
                        category = "附魔类";
                        loding = R.drawable.loading_of_enchant;
                        isCreating = false;
                        break;
                    default:
                        tableName = MyDatabaseHelper.TABLE_BUILDING;
                        category = "建筑类";
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