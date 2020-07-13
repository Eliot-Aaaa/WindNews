package com.eliot.news.mynews.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.eliot.news.mynews.bean.FragmentInfo;

import java.util.ArrayList;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.adapter
 * @ClassName: NewAdapter
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 10:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 10:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NewAdapter extends FragmentStatePagerAdapter
{
    ArrayList<FragmentInfo> mFragments;

    public NewAdapter(FragmentManager fm, ArrayList<FragmentInfo> fragments)
    {
        super(fm);
        mFragments = fragments;
    }
    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        return mFragments.get(position).getFragment();
    }

    @Override
    public int getCount()
    {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return mFragments.get(position).getTitle();
    }
}
