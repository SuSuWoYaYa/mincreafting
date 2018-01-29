package com.cuisanzhang.mincreafting;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

/**
 * Created by hesuxiang on 17/8/12.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public int getPageCount() {
        return pageCount;
    }

    private int pageCount = 0;
    private String tabTitles[] = new String[]{
            "wiki.教程",

            "合成表大全",
            "生物.物品.方块",
//            "教程"
    };
    private Context context;

    private String language;
    private boolean is_language_of_traditional_chinese  = false;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

        language = LanguageUtil.getLocaleLanguage(context);
        if (language.equals(LanguageUtil.TRADITIONAL_CHINESE)) {
            is_language_of_traditional_chinese = true;
        }

        tabTitles[0] = "Wiki.教程";
        tabTitles[1] = "合成表大全";

        if (is_language_of_traditional_chinese){
            tabTitles[2] = "生物.物品.方塊";
        }
        else {

            tabTitles[2] = "生物.物品.方块";

        }

        pageCount = tabTitles.length;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
//        Toast.makeText(context, "getItem" +
//                " position =" + position, Toast.LENGTH_SHORT).show();
        switch (position){
            case 0:
                return  FragmentTutorial.newInstance(0);
            case 1:
                return  FragmentCreating.newInstance(0);
            case 2:
                return  FragmentMobsAndItemAndBlocks.newInstance(0);

            default:
//                Toast.makeText(context, "Error position=" + position, Toast.LENGTH_SHORT).show();
                break;
        }

//        Toast.makeText(context, "Unknow Error position=" + position, Toast.LENGTH_SHORT).show();
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