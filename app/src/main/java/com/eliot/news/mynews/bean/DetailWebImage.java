package com.eliot.news.mynews.bean;

import java.io.Serializable;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.bean
 * @ClassName: DetailWebImage
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/14 14:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/14 14:54
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DetailWebImage implements Serializable
{
    private String alt;
    private String pixel;
    private String ref;
    private String src;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
