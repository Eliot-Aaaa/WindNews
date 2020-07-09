package com.eliot.news.mynews.splash.bean;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.splash.bean
 * @ClassName: AdsDetail
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/9 9:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 9:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AdsDetail implements Serializable {
    Action action_params;
    List<String> res_url;

    public Action getAction_params()
    {
        return action_params;
    }

    public void setAction_params(Action action_params)
    {
        this.action_params = action_params;
    }

    public List<String> getRes_url()
    {
        return res_url;
    }

    public void setRes_url(List<String> res_url)
    {
        this.res_url = res_url;
    }

    @NonNull
    @Override
    public String toString() {
        return "AdsDetail{"+"action_params="+action_params+",res_url="+res_url+"}";
    }
}
