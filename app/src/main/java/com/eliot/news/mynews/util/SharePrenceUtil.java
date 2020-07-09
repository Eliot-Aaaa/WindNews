package com.eliot.news.mynews.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.util
 * @ClassName: SharePrenceUtil
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/9 10:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 10:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SharePrenceUtil {
    public static final String XML_FILE_NAME = "cache";

    public static void saveString(Context context, String title, String content){
        SharedPreferences share = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putString(title, content);
        edit.apply();
    }

    public static String getString(Context context, String title){
        String content;
        SharedPreferences share = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        content = share.getString(title, "");
        return content;
    }

    public static void saveInt(Context context, String title, int content){
        SharedPreferences share = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putInt(title, content);
        edit.apply();
    }

    public static int getInt(Context context, String title){
        int content;
        SharedPreferences share = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        content = share.getInt(title, 0);
        return content;
    }

    public static void saveLong(Context context, String title, long content){
        SharedPreferences share = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putLong(title, content);
        edit.apply();
    }

    public static long getLong(Context context, String title){
        long content;
        SharedPreferences share = context.getSharedPreferences(XML_FILE_NAME, Context.MODE_PRIVATE);
        content = share.getLong(title, 0);
        return content;
    }
}
