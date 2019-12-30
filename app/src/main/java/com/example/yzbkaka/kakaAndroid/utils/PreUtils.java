package com.example.yzbkaka.kakaAndroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yzbkaka on 19-12-30.
 */


/**
 * 存储工具类
 */
public class PreUtils {

    private static String pre_name = "user_info";

    private static Context mContext;


    public static void init(Context context) {
        mContext = context;
    }


    /**
     * 存
     */
    public static void put(String key, Object value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(pre_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }
        editor.commit();
    }


    /**
     * 取
     */
    public static Object get(String key, Object defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (defaultValue instanceof String) {
            return sharedPreferences.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultValue);
        }
        return null;
    }

    public static SharedPreferences getSharedPreferences() {
        if (mContext == null) throw new IllegalStateException("SharedPreferences have not initialized");
        return mContext.getSharedPreferences(pre_name, Context.MODE_PRIVATE);
    }


    /**
     * 移除指定key
     */
    public static void remove(String removeKey) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(removeKey);
        editor.commit();
    }


    /**
     * 清空数据
     */
    public static void clearAll() {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
