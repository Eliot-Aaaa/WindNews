package com.eliot.news.mynews.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.eliot.news.mynews.R;
import com.eliot.news.mynews.adapter.NewAdapter;
import com.eliot.news.mynews.bean.FragmentInfo;
import com.eliot.news.mynews.news_inner.HotFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.fragment
 * @ClassName: NewsFragment
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 10:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 10:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NewsFragment extends Fragment
{
    ArrayList<FragmentInfo> pages;
    NewAdapter mNewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        pages = new ArrayList<>();
        FrameLayout layout = getActivity().findViewById(R.id.tabs);
        layout.addView(View.inflate(getActivity(), R.layout.include_tab, null));
        SmartTabLayout smartTabLayout = getActivity().findViewById(R.id.smart_tab);
        ViewPager viewPager = getActivity().findViewById(R.id.viewpager);

        String[] titles = getResources().getStringArray(R.array.news_titles);

        for (int i=0;i<titles.length;i++)
        {
            FragmentInfo info;
            if (i==0)
                info = new FragmentInfo(new HotFragment(), titles[i]);
            else
                info = new FragmentInfo(new EmptyFragment(), titles[i]);
            pages.add(info);
        }
        mNewAdapter = new NewAdapter(getFragmentManager(), pages);
        viewPager.setAdapter(mNewAdapter);

        smartTabLayout.setViewPager(viewPager);
    }
}
