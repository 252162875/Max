package com.mym.max.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Sp封装
 */
public class PrefUtils {

    public static void putBoolean(String key, boolean value) {
        SharedPreferences sp = UiUtils.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = UiUtils.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void putString(String key, String value) {
        SharedPreferences sp = UiUtils.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(String key, String defValue) {
        SharedPreferences sp = UiUtils.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences sp = UiUtils.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(String key, int defValue) {
        SharedPreferences sp = UiUtils.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    // 清除sharedpreferences的数据
    public static void removeSharedPreference(String[] keys) {
        SharedPreferences sp = UiUtils.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        for (int i = 0; i < keys.length; i++) {
            editor.remove(keys[i]);
        }
        editor.commit();// 提交修改
    }

}
