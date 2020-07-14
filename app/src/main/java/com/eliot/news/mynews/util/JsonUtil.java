package com.eliot.news.mynews.util;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.util
 * @ClassName: JsonUtil
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/9 10:09
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 10:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class JsonUtil {
    static Gson mGson;

    public static <T> T parseJson(String json, Class<T> tClass)
    {
        if (mGson == null)
            mGson = new Gson();
        if(TextUtils.isEmpty(json))
            return null;
        return mGson.fromJson(json, tClass);
    }
}
