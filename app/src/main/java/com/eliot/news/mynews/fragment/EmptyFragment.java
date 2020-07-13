package com.eliot.news.mynews.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eliot.news.mynews.R;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.fragment
 * @ClassName: EmptyFragment
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/13 10:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/13 10:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EmptyFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_empty, container, false);
        return view;
    }
}
