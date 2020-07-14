package com.eliot.news.mynews.bean;

import java.util.List;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.bean
 * @ClassName: DetailWeb
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/14 14:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/14 14:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DetailWeb
{
    String body;
    List<DetailWebImage> img;
    String title;
    String source;
    String ptime;
    int replyCount;

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<DetailWebImage> getImg() {
        return img;
    }

    public void setImg(List<DetailWebImage> img) {
        this.img = img;
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

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    @Override
    public String toString() {
        return "DetailWeb{" +
                "body='" + body + '\'' +
                ", img=" + img +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", ptime='" + ptime + '\'' +
                '}';
    }
}
