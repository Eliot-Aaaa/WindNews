package com.eliot.news.mynews.util;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.util
 * @ClassName: HttpUtil
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 11:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 11:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HttpUtil
{
    static HttpUtil util;
    static OkHttpClient client;

    private HttpUtil()
    {
        client = new OkHttpClient();
    }

    public static HttpUtil getInstance()
    {
        if(util==null)
        {
            synchronized (HttpUtil.class)
            {
                if(util==null)
                    util = new HttpUtil();
            }
        }
        return  util;
    }

    public void getDate(String url, final HttpRespon respon)
    {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
                respon.onError("连接服务器失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                if (!response.isSuccessful())
                    respon.onError("连接服务器失败");
                String date = response.body().string();

                respon.parse(date);
            }
        });
    }
}
