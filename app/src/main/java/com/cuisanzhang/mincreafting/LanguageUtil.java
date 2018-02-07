package com.cuisanzhang.mincreafting;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;

/**
 * Created by hesuxiang on 18/1/27.
 */

public class LanguageUtil {

    private static final String PREF_SETTING = "pref_setting";
    private static final String LANGUAGE = "Language";
    public static final String  SIMPLIFIED_CHINESE = "simplified_chinese";
    public static final String  TRADITIONAL_CHINESE = "traditional_chinese";

    // 得到设置的语言信息
    public static String getLocaleLanguage(Context context) {
        // 读取储存的语言设置信息

        SharedPreferences sp = context.getSharedPreferences(PREF_SETTING, Context.MODE_PRIVATE);
        String Language = sp.getString(LANGUAGE, SIMPLIFIED_CHINESE);

            return Language;
    }


// 设置语言信息
    public static void  setLocaleLanguage(Context context, String language) {
        // 储存语言设置信息

        SharedPreferences sp = context.getSharedPreferences(PREF_SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(LANGUAGE, language);

        editor.apply();

    }

//    public static Context attachBaseContext(Context context, Locale language) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            return updateResources(context, language);
//        } else {
//            return context;
//        }
//    }

//
//    @TargetApi(Build.VERSION_CODES.N)
//    private static Context updateResources(Context context, String language) {
//        Resources resources = context.getResources();
//        setLocaleLanguage(context, language);
//        String locale = getLocaleLanguage(context);
//
//        Configuration configuration = resources.getConfiguration();
//        configuration.setLocale(locale);
//        configuration.setLocales(new LocaleList(locale));
//        return context.createConfigurationContext(configuration);
//    }

}
