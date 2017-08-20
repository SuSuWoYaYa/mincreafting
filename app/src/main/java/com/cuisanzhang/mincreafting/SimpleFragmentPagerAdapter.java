package com.cuisanzhang.mincreafting;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by hesuxiang on 17/8/12.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public int getPageCount() {
        return pageCount;
    }

    private int pageCount = 0;
    private String tabTitles[] = new String[]{
//            "物品",

            "合成表大全",
            "生物.物品.方块",
//            "教程"
    };
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        pageCount = tabTitles.length;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 1:
                return  FragmentMobsAndItemAndBlocks.newInstance(0);


            default:
                break;
        }
        return  FragmentCreating.newInstance(0);
    }

    //    @Override
//    public Fragment getItem(int position) {
//        return PageFragment.newInstance(position + 1);
//    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}