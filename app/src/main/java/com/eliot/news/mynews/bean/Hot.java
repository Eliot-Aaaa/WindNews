package com.eliot.news.mynews.bean;

import java.util.List;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.bean
 * @ClassName: Hot
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 11:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 11:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Hot
{
    List<HotDetail> T1348647909107;

    public List<HotDetail> getT1348647909107() {
        return T1348647909107;
    }

    public void setT1348647909107(List<HotDetail> t1348647909107) {
        T1348647909107 = t1348647909107;
    }

    @Override
    public String toString() {
        return "Hot{" +
                "T1348647909107=" + T1348647909107 +
                '}';
    }
}
