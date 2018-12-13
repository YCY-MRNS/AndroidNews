package com.example.administrator.traing.utlis;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/8/22.
 */

public class CacheUtils {

    private static final String NEWS_KEY = "news";

    /**
     * 存入数据
     *
     * @param context
     * @param key
     * @param result
     */

    public static void putString(Context context, String key, String result) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NEWS_KEY, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, result).apply();
    }

    /**
     * 获得数据
     *
     * @param context
     * @param newsCentralUrl
     * @return
     */

    public static String getString(Context context, String newsCentralUrl) {
        SharedPreferences sp = context.getSharedPreferences(NEWS_KEY, Context.MODE_PRIVATE);
        return sp.getString(newsCentralUrl, "");
    }


}
