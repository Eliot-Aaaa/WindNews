package com.eliot.news.mynews.bean;

import androidx.fragment.app.Fragment;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.bean
 * @ClassName: FragmentInfo
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 10:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 10:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FragmentInfo
{
    Fragment mFragment;
    String title;

    public FragmentInfo(Fragment fragment, String title)
    {
        this.mFragment = fragment;
        this.title = title;
    }

    public Fragment getFragment()
    {
        return mFragment;
    }

    public void setFragment(Fragment fragment)
    {
        this.mFragment = fragment;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
