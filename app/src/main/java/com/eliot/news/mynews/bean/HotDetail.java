package com.eliot.news.mynews.bean;

import java.util.List;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.bean
 * @ClassName: HotDetail
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 11:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 11:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HotDetail
{
    List<Banner> ads;
    String imgsrc;
    String title;
    String source;
    int replyCount;
    String specialID;
    String docid;

    public List<Banner> getAds() {
        return ads;
    }

    public void setAds(List<Banner> ads) {
        this.ads = ads;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getSpecialID() {
        return specialID;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    @Override
    public String toString() {
        return "HotDetail{" +
                "ads=" + ads +
                ", imgsrc='" + imgsrc + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", replyCount=" + replyCount +
                ", specialID='" + specialID + '\'' +
                '}';
    }
}
