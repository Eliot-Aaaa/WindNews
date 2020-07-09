package com.eliot.news.mynews.splash.bean;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.splash.bean
 * @ClassName: Ads
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/9 9:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 9:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Ads implements Serializable {
    int next_req;
    List<AdsDetail> ads;

    public int getNext_req()
    {
        return next_req;
    }

    public void setNext_req(int next_req)
    {
        this.next_req = next_req;
    }

    public List<AdsDetail> getAds()
    {
        return ads;
    }

    public void setAds(List<AdsDetail> ads)
    {
        this.ads = ads;
    }

    @NonNull
    @Override
    public String toString() {
        return "Ads{"+"next_req="+next_req+",ads="+ads+"}";
    }
}
