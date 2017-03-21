package com.cuisanzhang.mincreafting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by hesuxiang on 17/3/19.
 */

public class ChangeTheme {
    public static int THEME_GRAY = R.style.Base_Theme_Design_gray;
    public static int THEME_PINK = R.style.Base_Theme_Design_pink;
    public static int THEME_RED = R.style.Base_Theme_Design_red;
    public static int THEME_BLUE = R.style.Base_Theme_Design_blue;
    public static int THEME_BROWN = R.style.Base_Theme_Design_brown;
    public static int THEME_ORANGE = R.style.Base_Theme_Design_orange;
    public static int THEME_PURPLE = R.style.Base_Theme_Design_purple;

    private static final String PREFTHEME = "pref_theme";
    private static final String THEME = "theme";

    public static void setTheme(Context context, int theme) {
        SharedPreferences sp = context.getSharedPreferences(PREFTHEME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(THEME, theme);
        editor.apply();
    }

    public static int getTheme(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFTHEME, Context.MODE_PRIVATE);
        return sp.getInt(THEME, THEME_GRAY);
    }
}


