package com.eliot.news.mynews.splash.bean;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.splash.bean
 * @ClassName: Action
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/9 9:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 9:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Action implements Serializable {
    public String link_url;

    public String getLink_url()
    {
        return link_url;
    }

    public void setLink_url(String link_url)
    {
        this.link_url = link_url;
    }

    @NonNull
    @Override
    public String toString() {
        return "Action{"+"link_url="+link_url+"}";
    }
}
