package com.eliot.news.mynews.util;

import android.text.TextUtils;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.util
 * @ClassName: HttpRespon
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 11:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 11:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class HttpRespon<T>
{
    Class<T> t;

    public HttpRespon(Class<T> t)
    {
        this.t = t;
    }

    public abstract void  onError(String msg);

    public abstract void  onSuccess(T t);

    public void parse(String json)
    {
        if(TextUtils.isEmpty(json)){
            //请求失败
            onError("连接网络失败");
            return;
        }
        //如果我们需要的是JSON的原字符串，直接返回
        if(t == String.class){
            onSuccess((T)json);
            return;
        }
        //否则，解析成需要的类型
        //尝试转化json->需要的类型
        T result = JsonUtil.parseJson(json,t);
        //转化成功
        if(result!=null){
            onSuccess(result);
        }else{
            onError("json解析失败");
        }
    }
}
